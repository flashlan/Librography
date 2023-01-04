	
@echo off

Rem cls
:start
ECHO What do you want to do?
ECHO 1. Click 1 to setup a Server machine
ECHO 2. Click 2 to setup a Client Machine
ECHO 3. Click 3 to setup a Student Terminal
ECHO 4. Click 4 to cancel and quit
set /p choice=Type the number to proceed to setup.
rem if not '%choice%'=='' set choice=%choice:~0;1% ( don`t use this command, because it takes only first digit in the case you type more digits. After that for example choice 23455666 is choice 2 and you get "bye"
if '%choice%'=='' ECHO "%choice%" is not valid please try again
if '%choice%'=='1' goto server
if '%choice%'=='2' goto client
if '%choice%'=='3' goto terminal
if '%choice%'=='4' goto quit
ECHO.
goto start
:server
ECHO Starting installing and setup the server....
ECHO -------------------------------------------------
ECHO First take a note of your ip number shown above.
ECHO You will need this to install the clients and terminals:
for /f "delims=[] tokens=2" %%a in ('ping -4 -n 1 %ComputerName% ^| findstr [') do set NetworkIP=%%a
echo Network IP:
ECHO ------------------------------------------------------
ECHO  %NetworkIP%
ECHO ------------------------------------------------------
pause
call setupFiles.bat
ECHO ok
ECHO -------------------------------------------------------
echo localhost>C:\Librography\ipserver
call setupJava.bat
call setupMysql.bat
ECHO 
goto end
:client
ECHO Starting installing and setup the client....
ECHO -------------------------------------------------
call setupFiles.bat
Rem setup server ip
echo Enter the server ip : 
set /p ipserver=
echo The server ip is  %ipserver%
echo %ipserver%>C:\Librography\ipserver
call setupJava.bat
ECHO BYE
goto end
:terminal
ECHO TEST
goto end
:quit
ECHO Bye
goto end
:end
pause
Rem exit