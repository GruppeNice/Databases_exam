# Local run of application

## Run application
1. Clone project
2. In src/main/resources, make an env.properties file and fill out the necessary information for connecting to mysql database and mongodb:
   
        JDBC_DATABASE_URL=jdbc:mysql://localhost:3306/hospital_db
        JDBC_USERNAME=
        JDBC_PASSWORD=
        
        MONGO_DB_HOST=localhost
        MONGO_DB_PORT=
        MONGO_DB=

4. In a terminal navigate to src/main in the project folder and run 'npm install' to get modules for the migration.
5. Run HospitalDbBackendApplication in src/main/java

## Run migration
1. in src/main/migrations make a envProperties.js file and fill out the necessary information for connecting to mysql database and mongodb:        

        const MIGRATION_MYSQL_HOST="localhost";
        const MIGRATION_MYSQL_USER="";
        const MIGRATION_MYSQL_PASSWORD="";
        const MIGRATION_MYSQL_DATABASE="";
        
        const MONGO_DB_URL = "mongodb://localhost:";
        const MONGO_DATABASE = "";
        
        export {
            MIGRATION_MYSQL_HOST,
            MIGRATION_MYSQL_USER,
            MIGRATION_MYSQL_PASSWORD,
            MIGRATION_MYSQL_DATABASE,
            MONGO_DATABASE,
            MONGO_DB_URL
        };
   
3. Start the application (written above)
4. In src/main/mysql_scripts, run the hospital_db_data.sql script against a mysql server with a database named hospital_db.
5. In a terminal navigate to src/main/migrations in the project folder and run node mysqlToMongoDB.js
