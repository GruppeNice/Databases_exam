# Local run of application

## Run application
1. Clone project
2. In src/main/resources, make an env.properties file and fill out the necessary information for connecting to mysql database and mongodb
3. In a terminal navigate to src/main in the project folder and run 'npm install' to get modules for the migration.
4. Run HospitalDbBackendApplication in src/main/java

## Run migration
1. Start the application (written above)
2. In src/main/mysql_scripts, run the hospital_db_data.sql script against a mysql server with a database named hospital_db.
3. In a terminal navigate src/main/migrations and run node mysqlToMongoDB.js
