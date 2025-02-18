

Project Structure:

src/main/java/com/api/ – Main source code

src/main/java/com/api/config/ – Manages configuration settings

src/main/java/com/api/wrapper – API wrapper

src/main/resources/ – Project Resources

src/test/java/com/api/tests/ – API test classes

   BookingTest.java - End-to-End Test
   
   RestfulBookerAPITest.java  - Mock Test
   
pom.xml – Maven project file for dependencies and build configuration

# API Testing Framework #

This repository contains an API testing framework built using **RestAssured**, **TestNG**, **Mockito**, and **Maven** for automating the testing of RESTful APIs.

## Technologies Used

- **RestAssured**: A Java library for testing REST APIs.
- **TestNG**: A testing framework used for running and managing tests.
- **Mockito**: A framework used to mock API responses for unit testing.
- **Maven**: A build automation tool that manages dependencies and builds the project.
- **Jackson**: For parsing JSON data.

## Prerequisites

Before running the tests, ensure that the following tools are installed on your system:

- **Java 8+**: Required to run the tests.
- **Maven**: For managing project dependencies and builds.

### 1. Clone the Repository

Clone the repository using Git:

```bash
git clone https://github.com/tahaiq-qa/restful-booker-e2e-test

### 2. Navigate to the Project Directory

```bash
cd restful-booker-tests


### 3. Run Test with Maven

```bash
# To clean, compile, run tests, and install the artifacts
mvn clean install

### OR ###

# To run the tests
mvn  test


