@echo off

echo Criando a pasta data, utilizadas pelo MySQL

set mypath=C:\Librography

net stop MySql

set mysqlpath=%mypath%\mysql

if not exist "%mysqlpath%\data" mkdir "%mysqlpath%\data"

echo Inicializando o MySQL. Criando o conteúdo da pasta data

"%mysqlpath%/bin/mysqld.exe" --initialize-insecure --user=mysql --basedir="%mysqlpath%" --datadir="%mysqlpath%\data"

echo Definindo o MySQL como um serviço

"%mysqlpath%\bin\mysqld.exe" --install MySQL

echo Iniciando o serviço criado

sc start MySQL

echo Verificando se o serviço esta ativo

:INITDATA
sc query "MySQL" | find "RUNNING"
if "%ERRORLEVEL%"=="0" (
	echo Serviço ativo ...
	
	echo Configurando a base de dados...

	


    echo Definindo uma senha para o usuário root...

	:: "%mysqlpath%\bin\mysql.exe" -u root --skip-password -e "ALTER USER 'root'@'localhost' IDENTIFIED BY 'goldenowl';"

	:: echo Criando o usuário utilizado pela aplicação e atribuindo as permissões dele

	:: "%mysqlpath%\bin\mysql.exe" -u root -p goldenowl -e "CREATE USER 'appLibrography'@'localhost' IDENTIFIED BY 'app1Librography';GRANT CREATE, SELECT, INSERT, UPDATE, DELETE ON *.* TO 'appLibrography'@'localhost';FLUSH PRIVILEGES;"

	::echo Criando o banco de dados da aplicação

	::"%mysqlpath%\bin\mysql.exe" -u appLibrography -p app1Librography -e "CREATE DATABASE appLibrography;"

	:: echo Criando a tabela utilizada pela a aplicação

	:: "%mysqlpath%\bin\mysql.exe" -i C:\Librography\Install_Files\CreateDBScript.sql 
 

) else (
    echo Serviço ainda não iniciado, aguardando 5 segundos
	ping 127.0.0.1 -n 6 > nul
	goto INITDATA
)
