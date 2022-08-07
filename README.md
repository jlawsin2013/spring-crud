# Springboot with MongoDB
Quick Start for Springboot using mongodb without authentication

**Steps & Commands**
- [x] Clone this repository to your local machine.
- [x] Make sure you have docker installed in your local machine. To verify, execute **`docker -version`**
- [x] Execute `**docker-compose up`**


**To test the API**
- [x] Install postman or just simply type this command:

- To fetch all users
```bash
curl --location --request POST 'http://localhost:8080/users'
```

- To add user
```bash
curl --location --request POST 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id":1,
    "fname":"John",
    "lname":"Lawsin"
}'
```
