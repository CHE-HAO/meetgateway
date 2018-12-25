@echo off
echo [INFO] Use maven package __production__ webapp zip.

call mvn clean package -Dpackage.bin=true -Dmaven.test.skip=true

pause 