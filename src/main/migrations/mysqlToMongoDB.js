import {MongoClient} from 'mongodb';
import * as mySql from "mysql2/promise";
import {
    MIGRATION_MYSQL_HOST,
    MIGRATION_MYSQL_USER,
    MIGRATION_MYSQL_PASSWORD,
    MIGRATION_MYSQL_DATABASE,
    MONGO_DB_URL, MONGO_DATABASE
} from './envProperties.js'
import {embedRelations, relations} from "./embedding.js";

async function migrateMysqlToMongoDB(entities, database, mongoUrl, msqlConnection) {
    // Connect to MySQL
    const mysqlConnection = await mySql.createConnection(msqlConnection);
    console.log('Connected to MySQL');

    // Connect to MongoDB
    const client = await MongoClient.connect(mongoUrl);
    console.log('Connected to MongoDB');

    const data = {};
    for(const entity of entities) {
        // Fetch MySQL data
        const [rows] = await mysqlConnection.query(`SELECT * FROM ${entity}`);
        data[entity] = rows;
        console.log("Found query result. Length:", rows.length);

        for (const row of rows) {
            for(const key in row) {
                if(key.includes("_id")){
                    row[key] = row[key].toString('hex')
                }
            }
        }
    }
    for(const entity of entities) {
        const docs = data[entity];
        if (relations[entity]) {
            data[entity] = embedRelations(docs, relations[entity], data);
        }
    }
    for(const entity of entities) {
        if(entity.includes("_")) {
            continue;
        }

        const db = client.db("hospital_db");
        const collection = db.collection(entity);
        // Insert into MongoDB
        const result = await collection.insertMany(data[entity]);
        console.log(`${entity} inserted in MongoDB:`, result.insertedCount);
    }
    //Close connections
    await client.close();
    await mysqlConnection.end();
}

await migrateMysqlToMongoDB(
    [
        "appointments",
        "diagnosis",
        "doctors",
        "hospitals",
        "hospitals_wards",
        "medications",
        "nurses",
        "patients",
        "patients_diagnosis",
        "prescriptions",
        "surgeries",
        "users",
        "wards",
    ],
    MONGO_DATABASE,
    MONGO_DB_URL,
    {
        host: MIGRATION_MYSQL_HOST,
        user: MIGRATION_MYSQL_USER,
        password: MIGRATION_MYSQL_PASSWORD,
        database: MIGRATION_MYSQL_DATABASE,
    }
)
