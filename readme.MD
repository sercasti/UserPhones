User Phones Sample 
--------------

The objective is a minimalistic approach using OOTB Spring Boot features as RAD with minimal config to get a REST app working quick and easy.

To start the application, simply type: mvn spring-boot:run

You can import the project as a maven project on any IDE and run it from the IDE using the main() method on the Application class.

Requirements:
- A mysql instance running with a schema named "userPhone". To adjust database connection settings, update the application.properties file.
- If the schema is empty, the application will create the tables automatically
- If the tables are empty (no data), the app will insert some test data
- If the schema has tables with the old structure, a liquibase script will migrate to the new model and transform the existing rows. 
 
Sample URL's:
- http://localhost:8080/userPhones/v1/phones/Danny/Cell
- http://localhost:8080/userPhones/v1/phones/Danny/

Technical specification:
- The idea was to migrate and old schema with 1 table (user) which would contain a phone field, to a new schema with n-n users <-> phones, as a bidirectional many to many relationship. 
- The new phone entity will now contain a description field, which will be used by the API for queries like this: "Get Danny’s office’s phone numbers.", which would return all phone numbers for this user, containing the word "office". To solve this, we will use the Spring Data Repositories and avoid shady custom implementations.
- We will include the Spring Boot Mobile library to detect the device from where the requests are coming, so we can pick a strategy for mobile, web and different clients (see the Device parameter on the RestController)
- The API URL will include a version number to handle API versioning and backwards compatibility
- We will include the liquibase framework to automatically migrate old databases to the new schema/model. Liquibase will detect if the database has the old model, and run a SQL script to create the new tables, and transform the old rows into the new model. 
