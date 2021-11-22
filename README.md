# QA_BackendAutomation
This restassured automation project created for assignment tasks

# Frameworks and tools 
- Its a maven java project and jdk 1.8
- RestAssured framework used to automate the test scenarios
- Tests are integrated with TestNG for execution
- For reporting, ExtentReports are integrated to generate nice html report

# Framworks Overview
- Required dependecies are added to the POM.xml file
- created resources to store the config and log4j proeprties file. Also to json schema file.
- [config.properties] This file has all the configuration such as token, hostname, api endpoints
- [testng.xml] This file is starting point of execution and considered as Test Suite. Along with tests classes it has listener class information
- [reports] This folder stores the extentReport i.e. index.html. It has logs folder with execution.log file which stores automation logs captured during execution.
- [Packages] - report package has the code implemented to prepare the generate the extent reports along with TestNG listeners
-   appUtils package the reuseable code specfic to api
-   models package has pojo created to deserializing response and also building request payload
-   utils package with singleton desgin for loading and reading the configuration. Random date generator method to fetch the dynamic dates
-   tests package has all the tests

# APIs Automated
- One GET API - CurrencyList API - /api/system/currency/list
- One POST API - Hotels search async - /api/enigma/search/async

    Based on understanding after getting handso-n with above APIs drived few scenarios and converted into automated tests. As i am not aware of full functionality of this APIs, few places has to make the assumption.
    
# Prerequisite
- JDK 1.8
- Maven

# Execution from command prompt
- Download the project for github
- Open command prompt
- Navigate to the location of above downloaded project
- Execute the below maven command
    mvn clean test -DsuiteXmlFile=testng.xml
- This command will clean, compile and execute all the tests mentioned in testng.xml file

# Report
- Once execution is completed. Navigate to 'reports' folder inside parent directory and open 'index.html' file
- This will show all the test cases which got executed along with step details and pass/fail status
- On left side, there is icon of graph on clicking it will show summary dashboard for tests and steps


