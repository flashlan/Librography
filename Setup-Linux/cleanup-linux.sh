#!/bin/bash

sudo mv /var/lib/mysql /var/lib/mysql_old
sudo mv /etc/mysql /etc/mysql_backup
mv ~/.librography ~/.librography_backup
sudo apt remove mariadb
sudo apt remove mariadb-server 
sudo apt remove mariadb-client
sudo apt remove openjdk-8-jdk
