@echo off
:start


PATH=C:\Librography_data\postgres\bin;%path%
netstat -o -n -a | findstr 5432
if %ERRORLEVEL% equ 0 goto FOUND
echo Postgresql not found or not usisng default port 5432...
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
echo opening the Postgresql download link....
pause
START Https://www.enterprisedb.com/postgresql-tutorial-resources-training?uuid=ea5c8104-3940-4ed1-b427-81cf19781581&campaignId=70138000000rYFmAAM
ECHO 1 - Download the setup from link opening on browser and after install and then we will continue the setup
ECHO 2 - Install with optiion "Only Server" and setup a root password
ECHO 3 - take a note of the root password, fisnish the installer  and continue this setup
Rem %Downloads%/postgresql-10.19-1-windows-x64.exe --mode unattended --unattendedmodeui minimal --superpassword MIRANDA --prefix C:\Librography_data\postgres --datadir C:\Librography_data\postgres\data
pause
Rem :continue
Rem echo ok
goto start
Rem goto FIN
:FOUND
echo port found. It mean the Postgresql is installed
echo we can continue the setup...
pause
:FIN

echo .
echo .
@echo off
rem ------------------------------------------------
echo Creating access to user. Waiting ...
echo Enter the Postgresql user password:
set /p rootpassword=
set dbname=applibrography
set dbuser=applibrography
set userpassword=gowl
set PGPASSWORD=MIRANDA
Rem "C:\Program Files\MySQL\MySQL Server 5.7\bin\mysql.exe" "--defaults-file=C:\ProgramData\MySQL\MySQL Server 5.7\my.ini" "-uroot" "-p"
SET PATH=%PATH%;C:\Librography_data\postgres\bin
psql -U postgres -c "CREATE DATABASE %dbname%;"
psql -U postgres -c "CREATE USER '%dbuser%'@'localhost' IDENTIFIED BY '%userpassword%';"
psql -U postgres -c "GRANT ALL PRIVILEGES ON %dbuser%.* TO '%dbuser%'@'localhost';"
psql -U postgres -c "FLUSH PRIVILEGES;"
psql -U postgres -f script-postgresql.sql -d applibrography
Rem psql -U postgres -c "\c applibrography; \i script-postgresql.sql;"
Rem psql -U postgres -c "source script-posgresql.sql"
Rem psql -U postgres -c "\i script-postgresql.sql;"
echo Done.
echo "Postgresql user created."
echo "Username:   %dbuser%"
echo "Password:   %userpassword%"
rem ------------------------------------------------
echo .
echo . 
