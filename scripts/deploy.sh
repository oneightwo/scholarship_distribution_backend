#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/prkopenssh scholarship_distribution-1.0.0-SNAPSHOT.jar oneightwo@188.227.72.42:/home/oneightwo/

echo 'Restart server...'

ssh -i ~/.ssh/prkopenssh oneightwo@188.227.72.42 <<EOF

pgrep java |  | xargs kill -9
nohup java -jar scholarship_distribution-0.0.1-SNAPSHOT.jar > log.txt &

EOF
 sudo lsof -i :3000

echo 'end'

sudo -i -u postgres