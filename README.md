Consumer data published to Kafka Topic, sql queries

The above application displayed as follows:
-Controller
-Model
-Repository
-Services
-Test

DEPLOYMENT:
1.Once added passwords and credentials for snowflake into application.properties 
You may need to use them  as environment variables as well   SNOWFLAKE_ACCOUNT=${SNOWFLAKE_ACCOUNT} , SNOWFLAKE_USER=${SNOWFLAKE_USER}, SNOWFLAKE_PASSWORD=${SNOWFLAKE_PASSWORD}
Example: spring.datasource.url=jdbc:snowflake://<your_account>.snowflakecomputing.com/?db=SNOWFLAKE_SAMPLE_DATA&schema=TPCDS_SF100TCL
2.Create and build application image
docker build -t kafka-app .
docker run -p port:port kafka-app
3.Decompose docker files and docker-compose up
From the root :
docker-compose up --build

TESTING:
Once opened Postman , set the Headers to application/json and chose the GET method you can try a url like the following
FOR PUBLISHING : GET http://localhost:<localport>/api/customers/fetch/{anId} 
FOR ACCESING MONGO DB : GET http://localhost:8080/api/customers/mongo
You should see a status 200 and/or json obtained as the following example 
[
  {
    "customerId": 123,
    "firstName": "Jane",
    "lastName": "Doe",
    "email": "jane.doe@example.com",
    "address": {
      "street": "Main St",
      "city": "Springfield"
    },
    "receivedAt": "2025-09-30T18:00:00Z"
  },
  ...
]


