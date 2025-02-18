

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

## Prerequisites

Before running the tests, ensure that the following tools are installed on your system:

- **Java 8+**: Required to run the tests.
- **Maven**: For managing project dependencies and builds.

  Verify Installation
  
After installing, check the versions again:

java -version
mvn -version

If both return valid versions, you're good to go! 🚀


### 1. Clone the Repository ###

Clone the repository using Git:

Use Git to clone the repository:
git clone https://github.com/tahaiq-qa/restful-booker-e2e-test.git

2. Navigate to the Project Directory
   
cd restful-booker-tests

3. Run Tests with Maven
   
Option 1: Full Build (Clean, Compile, Test, and Install)

mvn clean install

Option 2: Run Tests Only

mvn test


