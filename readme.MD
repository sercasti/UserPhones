To start the application, simply type: mvn spring-boot:run

You can import the project as a maven project on any IDE and run it from the IDE using the main() method on the Application class.

Requirements:
- A mysql instance running with a schema named "userPhone". To adjust database connection settings, update the application.properties file.
- If the schema is empty, the application will create the tables automatically
- If the schema has tables with the old structure, a liquibase script will migrate to the new model and transform the existing rows. 
 
Sample URL's:
http://localhost:8080/userPhones/v1/phones/Danny/Cell
http://localhost:8080/userPhones/v1/phones/Danny/