#!/bin/bash

WARFILE=sfinapp-server.war
TARGET=~/sfinapp/jetty/webapps

mvn clean install
cp target/$WARFILE $TARGET

echo "Deployed"
