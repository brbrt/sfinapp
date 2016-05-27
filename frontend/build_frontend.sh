#!/bin/sh

set -e

cat banner_frontend.txt

npm install --quiet

rm -rf build/
./node_modules/.bin/webpack
