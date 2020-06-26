#!/bin/bash
echo $PWD
docker login -u $DOCKERID -p $DOCKERPWD\

export EUREKA_IMAGE=xyd93/ayuda-eureka
docker build -t $EUREKA_IMAGE ./eureka
docker push $EUREKA_IMAGE

export CONFIG_IMAGE=xyd93/ayuda-config
docker build -t $CONFIG_IMAGE ./config-server
docker push $CONFIG_IMAGE

export ZUUL_IMAGE=xyd93/ayuda-zuul
docker build -t $ZUUL_IMAGE ./zuul-gateway
docker push $ZUUL_IMAGE

export ACCOUNT_IMAGE=xyd93/ayuda-account
docker build -t $ACCOUNT_IMAGE ./account-service
docker push $ACCOUNT_IMAGE
