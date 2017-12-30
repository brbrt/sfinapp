#!/bin/bash

set -ev

./gradlew build

cd sfinapp-backend

cp build/libs/sfinapp-1.0.jar ./sfinapp.jar
docker build -t brbrt/sfinapp .
docker push brbrt/sfinapp
