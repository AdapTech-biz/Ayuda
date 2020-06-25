#! /bin/bash
cd eureka
mvn clean install
mkdir target/dependency
cd target/dependency
jar -xf ../*.jar
rm ./BOOT-INF/lib/servlet-api-2.5.jar

cd ../config-server
mvn clean install
mkdir target/dependency
cd target/dependency
jar -xf ../*.jar

cd ../zuul-gateway
mvn clean install
mkdir target/dependency
cd target/dependency
jar -xf ../*.jar

cd ../account-service
mvn clean install
mkdir target/dependency
cd target/dependency
jar -xf ../*.jar

cd ..
