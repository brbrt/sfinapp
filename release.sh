#!/bin/bash

set -ev

./gradlew build

cd sfinapp-backend


ls -l
ls -l build/
ls -l build/libs/

cp build/libs/sfinapp-1.0.jar ./sfinapp.jar
docker build -t brbrt/sfinapp .
docker push brbrt/sfinapp
