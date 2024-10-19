# Order Management System - Spring Boot Application
This project is part of the Order Management System, which was developed using Java, Spring Boot, and MySQL. The primary focus of this application is to manage table services within the system. This README provides information on the application setup, features, and how to perform CRUD operations through the terminal.

## Tech Stack
Programming Language: Java

Framework: Spring Boot

Database: MySQL

## CRUD Operations
Below are instructions for performing CRUD operations using PowerShell commands with Invoke-WebRequest:

### Create (POST)

#### To create a new table entry with the status "Available": 

Invoke-WebRequest -Uri http://localhost:8080/api/tables -Method POST -Headers @{"Content-Type"="application/json"} -Body '{"orderStatus":"Available"}'

### Read (GET)
#### To retrieve all table entries:

Invoke-WebRequest -Uri http://localhost:8080/api/tables -Method GET


### Update (PUT)
#### To update the status of a specific table (e.g., table with ID 1) to "Reserved":

Invoke-WebRequest -Uri http://localhost:8080/api/tables/1 -Method PUT -Headers @{"Content-Type"="application/json"} -Body '{"orderStatus":"Reserved"}'

### Delete (DELETE)
#### To delete a table entry with ID 1:

Invoke-WebRequest -Uri http://localhost:8080/api/tables/1 -Method DELETE

### Spring Boot Application Running

![U6A1](https://github.com/user-attachments/assets/869bba74-0946-4056-a16e-8a95288473a0)

### Data Viewed Through Browser

![U62](https://github.com/user-attachments/assets/891ca7ee-e9bd-408a-bb9f-9d43d55ba6a8)

#### This can be viewed when visiting http://localhost:8080/api/tables on the browser while the application is running




