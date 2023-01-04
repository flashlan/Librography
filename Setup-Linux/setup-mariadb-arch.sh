#!/bin/sh


echo "Script is executed as : `whoami`"

#var0=$(who am i | awk '{print $1}')
#echo "var0 is $var0"

server=127.0.0.1
dbname=applibrography
dbuser=applibrography
dbuserpassword=gowl


if nc -z $server 3306 2>/dev/null; then
    echo "$server ✓ - MariaDB Server is installed!"
    echo "Proceeding to configuration:"
    sudo mysql -uroot --execute="CREATE DATABASE $dbname"; #change to variables
    sudo mysql -uroot --execute="CREATE USER '$dbuser'@'localhost' IDENTIFIED BY '$dbuserpassword';"
	sudo mysql -uroot --execute="GRANT ALL PRIVILEGES ON $dbuser.* TO '$dbuser'@'localhost';"
	sudo mysql -uroot --execute="FLUSH PRIVILEGES;"
	sudo mysql -uroot --execute="source script.sql"
else
    echo "$server ✗ - MariaDb is not Installed!!"
    echo "Do tou wanto to install?"
    # if else aqui com resposta""
    sudo pacman -Syu
    sudo pacman -S mariadb
    sudo mariadb-install-db --user=mysql --basedir=/usr --datadir=/var/lib/mysql
    sudo systemctl start mariadb.service
    sudo systemctl enable mariadb.service
fi



# PS3='Choose your firewal profile: '


#mysql -uroot -p%rootpassword% --execute="CREATE DATABASE %dbname%;"
