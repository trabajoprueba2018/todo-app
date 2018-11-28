#!/bin/sh

sudo mysql -e "use mysql; update user set authentication_string=PASSWORD('r00t') where User='root'; update user set plugin='mysql_native_password';FLUSH PRIVILEGES; SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));"
sudo mysql_upgrade -u root -pr00t
sudo service mysql restart
./scripts/bash/init-db.sh
sudo rm /usr/local/bin/docker-compose
curl -L https://github.com/docker/compose/releases/download/1.23.1/docker-compose-`uname -s`-`uname -m` > docker-compose
chmod +x docker-compose
sudo mv docker-compose /usr/local/bin
