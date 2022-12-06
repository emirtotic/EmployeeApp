# Employee Tracker Application

### Reference Documentation

Instructions:

* In DBeaver or MySQL Workbench create employee-tracker schema
* Postman collection with sample requests is in "src/main/resources/postman" package
* [Swagger link](http://localhost:8091/swagger-ui/index.html#) -> http://localhost:8091/swagger-ui/index.html
* API Documentation is in "src/main/resources/documentation" package

### URL

Api Collection list:

* **Create Employee:** http://localhost:8091/api/employee-tracker/create
* **Find Employee ID:** http://localhost:8091/api/employee-tracker/3
* **Delete Employee by ID:** http://localhost:8091/api/employee-tracker/17
* **Update Employee:** http://localhost:8091/api/employee-tracker/update
* **Find Employees by Filter:** http://localhost:8091/api/employee-tracker/search?personalId=987654&team=Development&teamLead=Mirko
* **Find All Employees:** http://localhost:8091/api/employee-tracker/all
* **Generate PDF report:** http://localhost:8091/api/employee-tracker/report/pdf?team=Development
* **Generate Excel report:** http://localhost:8091/api/employee-tracker/report/excel