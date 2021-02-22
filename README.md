# Test Automation Script for Amazon application using Cucumber Selenium.

About project:
        A Selenium maven project using Cucumber tool has been to test Amazon web application. Cucumber is used, as it follows a Gherkin language which would be helpful for everyone without prior test automation knowledge to understand the test case scenario. This project has followed a Page Object Model(POM) design principle, which is used to avoid duplication and for easy management of test scripts. Locators and methods are created as a separate class for each page, which is easy to maintain when the test cases grow in future. A Page Object Factory, an optimized way to create locators in POM is used.

Tools used:
* Selenium 
* Apache Maven   – 3.6.3
* Cucumber       - 6.9.1
* Chrome Driver  - 87.0.4280
Project Structure:
			

This maven project contains a pom.xml file which has Selenium, Junit, Extent report and all Cucumber related dependencies added into it. Project structure comprises of 3 main packages.
Features: It contains a feature file where the scenario to test the sorting functionality of an Amazon web application written using 4 annotations @Given, @When, @And, @Then .
Page Object: It contains 2 classes HomePage and ProductsPage, each page contains the page related locators and methods.
Runner: This runner class is the starting point of test automation execution. It has 2 annotations @RunWith which instructs to start the test execution, @CucumberOptions is used to set some properties like feature file, gluing the stepDefinition file and other properties like plugin, hooks can also be added.
StepDefintion: It converts the feature file into selenium script which performs the test automation on application. 

Report :
This project integrated Extent reports for graphical representation of reports along with log for each step that are executed. Failure scenarios are handled by capturing screenshot along with logs and attaching to the report.
		   
FailureScreenshot : Contains Failure screenshots.


Steps TO execute the project.
This project can be executed in 3 ways.
1. Using Feature File : Right click on test.feature -> Run As -> Cucumber feature.
2. Using TestRunner   :  Right click on TestRunner.java ->Run As-> Junit Test.
3. Using Maven        :   Open cmd from project directory and run the command “MVN clean test”.
Note: Inline comments are added to the code for better understanding of logic implemented. 
