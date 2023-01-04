@echo off
:start
PATH=C:\Program Files\MySQL\MySQL Server 5.7\bin;%path%
netstat -o -n -a | findstr 3306
if %ERRORLEVEL% equ 0 goto FOUND
echo MySql not found or not usisng default port 3306...
ECHO Do you wanto open open browser to download it?
ECHO 1. yes
ECHO 2. no
set /p choice=Choose your option.
if '%choice%'=='' ECHO "%choice%" is not valid please try again
if '%choice%'=='1' goto open
if '%choice%'=='2' goto FIN
ECHO.
goto start
:open
echo opening the Mysql download link....
pause
START Https://downloads.mysql.com/archives/get/p/25/file/mysql-installer-web-community-5.7.15.0.msi
ECHO 1 - Download the setup from link opening on browser and after install and then we will continue the setup
ECHO 2 - Install with optiion "Only Server" and setup a root password
ECHO 3 - take a note of the root password, fisnish the installer  and continue this setup
pause
Rem :continue
Rem echo ok
goto start
Rem goto FIN
:FOUND
echo port found. It mean the Mysql is installed
echo we can continue the setup...
pause
:FIN

echo .
echo .
@echo off
rem ------------------------------------------------
echo Creating access to user. Waiting ...
echo Enter the MySql root password:
set /p rootpassword=
set dbname=applibrography
set dbuser=applibrography
set userpassword=gowl
Rem "C:\Program Files\MySQL\MySQL Server 5.7\bin\mysql.exe" "--defaults-file=C:\ProgramData\MySQL\MySQL Server 5.7\my.ini" "-uroot" "-p"
SET PATH=%PATH%;C:\Program Files\MySQL\MySQL Server 5.7\bin
mysql -uroot -p%rootpassword% --execute="CREATE DATABASE %dbname%;"
mysql -uroot -p%rootpassword% --execute="CREATE USER '%dbuser%'@'localhost' IDENTIFIED BY '%userpassword%';"
mysql  -uroot -p%rootpassword% --execute="GRANT ALL PRIVILEGES ON %dbuser%.* TO '%dbuser%'@'localhost';"

mysql  -uroot -p%rootpassword% --execute="FLUSH PRIVILEGES;"
mysql  -uroot -p%rootpassword% --execute="source script.sql"
echo Done.
echo "MySQL user created."
echo "Username:   %dbuser%"
echo "Password:   %userpassword%"
rem ------------------------------------------------
echo .
echo . 
