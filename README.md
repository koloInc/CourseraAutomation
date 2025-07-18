# Coursera Automation Project

Used [coursera.org](https://coursera.org) for automation workflows.

## Features Implemented

- **Multi-browser execution** : Supports Chrome and Edge (via DriverSetup).
- **Hybrid Framework** : Combines Page Object Model (POM), Data-Driven Testing, and BDD using Cucumber.
- **Selenium Grid** : Enables test execution in remote environment.
- **Reusable components** : Core functionalities are modularized for maintainability.
- **Exception handling** : Robust try-catch blocks with meaningful error messages.
- **Synchronization** : Managed using WebDriverWait and implicit waits.
- **Locators** : Uses reliable locators like id, name, and xpath.
- **Console logging** : Outputs key data such as course details and validation messages.
- **Screenshot capture** : Captures screenshots for validation and reporting.
- **Clean code** : Follows Java conventions with comments and structured headers.

## Folder Structure

```
.settings/
features/
    ├── testCase001.feature
    ├── testCase002.feature
    ├── testCase003.feature
    ├── testCase004.feature
    ├── testCase005.feature
    ├── testCase006.feature
    ├── testCase007.feature
    ├── testCase008.feature
    ├── testCase009.feature
    ├── testCase010.feature
    ├── testCase011.feature
    └── testCase012.feature
src/
    ├── main/
    |    ├── java/
    |    └── resources/
    └── test/
        ├── java/
        |   ├── factory/
        |   |   └── BaseClass.java
        |   ├── hooks/
        |   |   └── Hooks.java
        |   ├── pageObjects/
        |   |   ├── BasePage.java
        |   |   ├── CareersPage.java
        |   |   ├── CourseContent.java
        |   |   ├── CoursePage.java
        |   |   ├── ForBusiness.java
        |   |   ├── ForFooter.java
        |   |   ├── ForGovernment.java
        |   |   ├── HomePage.java
        |   |   ├── OnlineDegrees.java
        |   |   └── PartnersPage.java
        |   ├── stepsDefination/
        |   |   ├── CommonSteps.java
        |   |   ├── TestCase001.java
        |   |   ├── TestCase002.java
        |   |   ├── TestCase003.java
        |   |   ├── TestCase004.java
        |   |   ├── TestCase005.java
        |   |   ├── TestCase006.java
        |   |   ├── TestCase007.java
        |   |   ├── TestCase008.java
        |   |   ├── TestCase009.java
        |   |   ├── TestCase010.java
        |   |   ├── TestCase011.java
        |   |   └── TestCase012.java
        |   ├── testRunner/
        |   |   ├── TestRunner_JUnit.java
        |   |   └── TestRunner_TestNG.java
        |   ├── utilities/
        |   |   ├── Constants.java
        |   |   ├── ExcelUtils.java
        |   |   ├── ReportGenerator.java
        |   |   ├── TextFileUtils.java
        |   |   ├── WaitUtils.java
        |   |   └── XMLUtils.java
        |   └── .gitkeep
        └── resources/
            ├── Validation_ScreenShot/
            |   ├── Android.png
            |   └── Apple.png
            ├── config.properties
            ├── CourseraAutomation.xml
            ├── extent.properties
            ├── log4j2.xml
            └── PartnerData_TC6.txt
test-output/
allure-results/
node_modules/
allure-report_run.bat
maven_run.bat
pom.xml
selenium_grid.bat
testng.xml
testRunnerTestNG.xml
```

## Files Functionality
- **factory/BaseClass.java** : WebDriver setup
- **hooks/Hooks.java** : Driver Manipulation and Screenshot
- **PageObjects/** : Contains POM of all pages
- **StepDefination** : Contains Common step and testCases
- **testRunner**: Contains JUnit and TestNG runner
- **FileConstants.java** :Contains sheet details
- **features/**: Contains scenario and feature of test cases
- **ReportGenerator.java** : Manages Allure Reports
- **ExcelUtils.java** : Utility for reading/writing Excel data
- **TextFileUtils.java** : Manages text file input/output
- **XMLUtils.java**: Manages XML file

## File Resources
- **CourseraTestData.xlsx** : Contains input and expected data
- **config.properties**: contains driver name, environment,OS and website.
- **CourseraAutomation.xml**: Contains Country input in XML file
- **extent.properties**: Contains report storing location and its naming format.
- **log4j2.xml**: Contains logging functionality
- **PartnerData_TC6.txt**: Contains data output for testCase 6

## Steps Performed

1. SEARCH → WEB DEVELOPMENT (SELECT FROM SUGGESTION) → CLICK ENGLISH FROM LIST → CLICK BEGINNER FROM LIST → SCRAPE FIRST 2 → PRINT NAME | RATING | DURATION
2. LIST OF LANGUAGE → AND TOTAL NO OF EACH COURSES EACH
3. ENTERPRISE → FILL FORM → INVALID INPUT → PRINT ERROR MESSAGE
4. INDIVIDUAL → PARTNERS → INDIA → LOGO | NAME | LINK
5. ONLINE DEGREE → FILTER BY (PROGRAM LEVEL), (MULTIPLE) → PRINT TOTAL LIST (ALL NUMBERS)
6. FOR BUSINESS → FOR TEAMS → GET STARTED → NO OF USERS → ANNUALLY/QUARTERLY → CONTINUE → PURCHASE SUMMARY TABLE
7. AFTER FLOW 1 → CLICK ON FIRST RESULT → SHOW THE DETAILS OF THE COURSE (MENTOR NAME / RATING / LEVEL ETC)
8. FOR GOVERNMENT → CONTACT SALES (TOP RIGHT) → FILL DETAILS → FIRST NAME, LAST NAME, WORK EMAIL, MOBILE NO, ORG TYPE (NON GOVT), JOB TITLE (RANDOM), ORG NAME (RANDOM), ORG SIZE (3RD ONE), COUNTRY (RANDOM) → CLICK ON REQUEST INFO → PRINT THE MESSAGE ON NEXT PAGE
9. EXTRACT ALL LIST → TECHNICAL SKILLS | ANALYTICAL SKILLS | BUSINESS SKILLS | CAREER RESOURCE | COURSERA | COMMUNITY | MORE → FROM FOOTER
10. GO BOTTOM (FOOTER) → CLICK ON APPLE LOGO → TAKE SCREENSHOT → COME BACK → CLICK ON GOOGLE PLAY → TAKE SCREENSHOT (SAVE BOTH TO A FOLDER) → FOR VALIDATION THAT THESE LINKS ARE WORKING CORRECTLY
11. SCROLL TO FOOTER → CLICK ON EACH SOCIAL MEDIA ICON ONE BY ONE → OPEN EACH ONE IN A NEW WEB PAGE → EXTRACT AND PRINT TITLE AND LINK OF EACH WEB PAGE OPENED IN EXCEL
12. CLICK ON CAREERS PRESENT UNDER COURSERA IN THE BOTTOM OF THE PAGE → CLICK ON EXPLORE OPPORTUNITIES → SELECT COUNTRY AS INDIA → PRINT THE JOBS THAT APPEAR IN EXCEL

## Other Files:
- **allure-report_run.bat**: run allure report with terminal
- **maven_ruun.bat**: run all test cases using Maven
- **pom.xml** : contains requires dependencies
- **selenium_grid.bat**: run into grid environment
- **testRunnerTestNG.xml**: Run using xml file

## Tech Stack

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-FF4D4D?style=for-the-badge)
![Cucumber](https://img.shields.io/badge/Cucumber-2B6B2B?style=for-the-badge&logo=cucumber&logoColor=white)
![Apache POI](https://img.shields.io/badge/Apache_POI-1D2D50?style=for-the-badge&logo=apache&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Log4j2](https://img.shields.io/badge/Log4j2-CC3300?style=for-the-badge) 
![Excel](https://img.shields.io/badge/Excel-217346?style=for-the-badge&logo=microsoft-excel&logoColor=white)
![XML File](https://img.shields.io/badge/XML_File-FF6600?style=for-the-badge&logo=xml&logoColor=white)
![TXT File](https://img.shields.io/badge/TXT_File-4479A1?style=for-the-badge&logo=textedit&logoColor=white)
![Properties File](https://img.shields.io/badge/Properties_File-6D4C41?style=for-the-badge)
![Google Chrome](https://img.shields.io/badge/Chrome-4285F4?style=for-the-badge&logo=googlechrome&logoColor=white)
![Microsoft Edge](https://img.shields.io/badge/Edge-0078D7?style=for-the-badge&logo=microsoft-edge&logoColor=white)
![Allure Report](https://img.shields.io/badge/Allure_Report-FFD700?style=for-the-badge&logo=allure&logoColor=black)
![Extent Reports](https://img.shields.io/badge/Extent_Reports-007ACC?style=for-the-badge)
![Cucumber Report](https://img.shields.io/badge/Cucumber_Report-2B6B2B?style=for-the-badge&logo=cucumber&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)


## License

![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)

This project is licensed under the **[Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)**.

You may use, modify, and distribute this code in compliance with the license terms.


> ⚠️ **Disclaimer**:  
> This project is developed strictly for educational and demonstration purposes.  
> It is not affiliated with, maintained by, or officially connected to **Coursera**.  
> Use of automation tools on public platforms may violate their terms of service.  
> Please use responsibly and at your own discretion.