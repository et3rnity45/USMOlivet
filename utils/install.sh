#!/bin/sh
echo "installing ..."
# Pre-requesties :
# 1 - install jdk11, mysql-server
# 2 - create a user :
	#sudo useradd usmo
	#sudo passwd usmo
	#sudo chown usmo:usmo usmolivet-0.0.1-SNAPSHOT.jar
  #sudo chmod 500 usmolivet-0.0.1-SNAPSHOT.jar

# 3 - put the JAR, usmo.sh and install.sh at /home/usmo/
# 4 - create database :
  # mysql -u root
  # CREATE DATABASE usmolivet;
  # GRANT ALL PRIVILEGES ON usmolivet.* TO 'usmolivet_user'@'localhost' IDENTIFIED BY 'JHE6Ysu!XcdZQ2BZ';

# connect you as usmo in SHH
# 5 - configure your jar as service :

sudo cp usmo.service /etc/systemd/system/
sudo systemctl daemon-reload
sudo systemctl enable usmo.service
sudo systemctl start usmo
sudo systemctl status usmo
