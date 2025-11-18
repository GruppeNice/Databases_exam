import {MongoClient} from 'mongodb';
import * as mySql from "mysql2/promise";
import { MIGRATION_MYSQL_HOST, MIGRATION_MYSQL_USER, MIGRATION_MYSQL_PASSWORD, MIGRATION_MYSQL_DATABASE } from './envProperties.js'

async function migrateMysqlToMongoDB() {
    // Connect to MySQL
    const mysqlConnection = await mySql.createConnection({
        host: MIGRATION_MYSQL_HOST,
        user: MIGRATION_MYSQL_USER,
        password: MIGRATION_MYSQL_PASSWORD,
        database: MIGRATION_MYSQL_DATABASE,
    });
    console.log('Connected to MySQL');

    // Connect to MongoDB
    const client = await MongoClient.connect("mongodb://localhost:27018");
    console.log('Connected to MongoDB');

    const db = client.db("hospital_db");
    const collection = db.collection("appointments");

    // Fetch MySQL data
    const [rows] = await mysqlConnection.query("SELECT * FROM appointments");
    console.log("Found query result. Length:", rows.length);

    // Convert binary IDs to hex
    for (const row of rows) {
        row.appointment_id = row.appointment_id.toString('hex');
        if (row.doctor_doctor_id) row.doctor_doctor_id = row.doctor_doctor_id.toString('hex');
        if (row.nurse_hospital_id) row.nurse_hospital_id = row.nurse_hospital_id.toString('hex');
        if (row.patient_patient_id) row.patient_patient_id = row.patient_patient_id.toString('hex');
    }

    // Insert into MongoDB
    const result = await collection.insertMany(rows);
    console.log("Appointments inserted in MongoDB:", result.insertedCount);

    // Close connections
    await client.close();
    await mysqlConnection.end();
}

migrateMysqlToMongoDB().catch(err => {
    console.error("Migration failed:", err);
});
