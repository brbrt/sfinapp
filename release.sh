#!/bin/bash

set -ev

./gradlew build

rm -rf release
mkdir release release/service release/frontend

cp service/build/libs/sfinapp-0.0.1-SNAPSHOT.jar release/service
cp frontend/build/* release/frontend

cd release
tar -zcf sfinapp.tar.gz *
