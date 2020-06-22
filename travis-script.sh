#! /bin/bash
cd eureka
mvn clean install

cd ../config-server
mvn clean install

cd ../zuul-gateway
mvn clean install

cd ../account-service
mvn clean install

cd ..
