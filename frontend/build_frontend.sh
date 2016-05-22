#!/bin/sh

set -e

cat banner_frontend.txt

npm install --quiet

node_modules/gulp/bin/gulp.js clean
node_modules/gulp/bin/gulp.js build --production
