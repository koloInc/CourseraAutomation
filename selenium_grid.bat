@echo off
cd /d C:\Users\2403720\eclipse-workspace\CourseraAutomation

:: Start Selenium server in a new window
start "" java -jar selenium-server-4.34.0.jar standalone

:: Wait 10 seconds to allow the server to start
timeout /t 20 >nul

:: Open a new terminal and launch the Grid UI
start cmd /k "start http://localhost:4444/"
