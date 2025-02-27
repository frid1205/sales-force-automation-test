@echo off
echo Running Maven tests...
mvn test -f "%CD%\pom.xml"

REM Cek apakah perintah sebelumnya gagal (error level lebih dari 0)
if %errorlevel% neq 0 (
    echo Maven test failed with error level %errorlevel%.
) else (
    echo Maven test completed successfully.
)

cmd /k