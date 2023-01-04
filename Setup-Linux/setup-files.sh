#!/bin/sh

sudo rm -rf /opt/Librography
mkdir /opt
sudo pacman -S p7zip
sudo 7z x ../setup/data -o/opt/ -aoa
sudo chown root -R /opt/Librography
sudo chmod 777 -R /opt/Librography
cp DBType /opt/Librography/
#touch /opt/Librography/DBType
#echo "mysql" >> /opt/Librography/DBType 
#sed -i 's/^[^0-9]*//' /opt/Librography/DBType
cp ../dist/Librography.jar /opt/Librography/
cp Librography.sh /opt/Librography/
sudo chmod a+rx /opt/Librography/Librography.jar

#pause
