@echo off
echo Generating Allure report...
cd C:\Users\2403720\eclipse-workspace\CourseraAutomation
allure generate allure-results --clean -o allure-report && allure open allure-report
pause
