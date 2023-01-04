#!/bin/bash

<<<<<<< HEAD
echo "Now we will need  update your system and install dependencies, do you want to proceed? " 
PS3='Choose a option '
options=("proceed"  "Quit")
select opt in "${options[@]}"
do
    case $opt in
        "proceed")
 
=======
# echo "$(openssl rand -base64 12)"

>>>>>>> e6b92619aca86245c4e77e357cbd326edf2cda46

########################## temporario ####################
#sudo apt update 
#sudo apt upgrade 
#sudo apt install openjdk-8-jdk

	echo "What is the current configuration for this machine?" 
	PS3='Choose a option '
	options=("server" "client" "Quit")
	select opt in "${options[@]}"
	do
	    case $opt in
		"server")
		    echo "proceed to install server"
			
				if [ -d /var/lib/mysql/mysql ] ; then
					echo "mysql already installed, skipping!" 
				else
					sudo apt install mariadb-server
					echo "now we will proceed with the secure installation and configuration of the database"
					#echo "first you will choose the password for Mysql server" 
					echo "Next you can answer in order: n - y - y - y - y "
					read -p "Press enter to continue"
					sudo mysql_secure_installation
				fi
		    
				if [ -d /var/lib/mysql/gowl ] ; then
					echo " database gowl exist! skipping " 
				else
					rm ~/.librography/IPSERVER
					touch ~/.librography/IPSERVER
					touch ~/.librography/DefaultPrinter

					mkdir ~/.librography
					rm ~/.librography/DBUSER
					touch ~/.librography/DBUSER
					DBUSER=gowl 
					echo $DBUSER  >> ~/.librography/DBUSER

					rm ~/.librography/DBPASS
					touch ~/.librography/DBPASS
					echo "Entre com a senha para o usuario do banco de dados: "
					read DBPASS
					echo $DBPASS >> ~/.librography/DBPASS

					sudo mysql  <<MYSQL_SCRIPT
					CREATE DATABASE $DBUSER;
					CREATE USER '$DBUSER'@'localhost' IDENTIFIED BY '$DBPASS';
					GRANT ALL PRIVILEGES ON $DBUSER.* TO '$DBUSER'@'localhost';
					source script.sql
					FLUSH PRIVILEGES;
MYSQL_SCRIPT
					echo "MySQL user created."
					echo "Username:   $DBUSER"
					echo "Password:   $DBPASS"
				fi
				
				IPSERVER=localhost
				echo $IPSERVER >> ~/.librography/IPSERVER

				echo  "-------- Installation finished! ------------" 
				echo " DataBase Url/ip: $IPSERVER" 
				echo " DataBase Username : $DBUSER"
				echo " DataBase Password : $DBPASS" 
						
				break
		    ;;
		"client")
		    echo "proceed to install a cliente"
				sudo apt install mariadb-client
				mkdir ~/.librography
				rm ~/.librography/IPSERVER
				touch ~/.librography/IPSERVER
				touch ~/.librography/DefaultPrinter
				echo "Enter the database server ip: "
				read IPSERVER
				echo $IPSERVER >> ~/.librography/IPSERVER
			
				rm ~/.librography/DBUSER
				touch ~/.librography/DBUSER
				DBUSER=gowl 
				echo $DBUSER  >> ~/.librography/DBUSER

				rm ~/.librography/DBPASS
				touch ~/.librography/DBPASS
				echo "Entre com a senha do usuario do banco de dados: "
				read DBPASS
				echo $DBPASS >> ~/.librography/DBPASS

				echo  "-------- Installation finished! ------------" 
				echo " DataBase Url/ip: $IPSERVER" 
				echo " DataBase Username : $DBUSER"
				echo " DataBase Password : $DBPASS" 

				break
		    ;;
		"Quit")
		    break
		    ;;
		*) echo "invalid option $REPLY";;
	    esac
	done

		break
	    ;;
	"Quit")
	    break
	    ;;
	*) echo "invalid option $REPLY";;
    esac
done

<<<<<<< HEAD
# create shortcut on desktop
=======
# is the mysql already configured?


#PASS=`pwgen -s 40 1`
#PASS= dbpass

>>>>>>> e6b92619aca86245c4e77e357cbd326edf2cda46

