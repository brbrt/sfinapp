#!/bin/bash

set -ev

./gradlew build

cd sfinapp-backend
docker build --build-arg JAR_FILE=build/libs/sfinapp-1.0.jar -t brbrt/sfinapp .
docker push brbrt/sfinapp
