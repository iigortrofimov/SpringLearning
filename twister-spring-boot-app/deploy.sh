#!/usr/bin/env bash

mvn clean package
echo 'Copy files...'
scp -i ~/.ssh/iigorka2009_2.pem /home/igor/Spring_Java/twister-spring-boot-app/target/twister-spring-boot-app-1.0-SNAPSHOT.jar ubuntu@13.49.33.101:/home/ubuntu/springbootapp/
echo 'Restart server...'
ssh -i ~/.ssh/iigorka2009_2.pem ubuntu@13.49.33.101 << EOF
#pgrep java | xargs kill -9
nohup java -jar twister-spring-boot-app-1.0-SNAPSHOT.jar > log.txt &
EOF
echo 'Bye!'
