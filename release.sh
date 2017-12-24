#!/bin/bash

set -ev

./gradlew build

rm -rf release
mkdir release 

cp service/build/libs/sfinapp-0.0.1-SNAPSHOT.jar release

cd release
tar -zcf sfinapp.tar.gz *
