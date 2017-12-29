#!/bin/bash

set -ev

./gradlew build

cd sfinapp-backend
docker build -t brbrt/sfinapp .
docker push brbrt/sfinapp
