# Springboot with MongoDB
Quick Start for Springboot using mongodb without authentication

**Steps & Commands**
- [x] Clone this repository to your local machine.
- [x] Make sure you have docker installed in your local machine. To verify, execute **`docker -version`**
- [x] Execute **`docker-compose up`**


**To test the API**
- [x] Install postman or just simply type this command:

- To fetch all users
```bash
curl --location --request GET 'http://localhost:8080/api/v1/users'
```

- To add user
```bash
curl --location --request POST 'http://localhost:8080/api/v1/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "fname": "John",
    "lname": "Lawsin",
    "address": "PH"
}'
```
