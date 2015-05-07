#!/bin/sh

npm install --quiet

node_modules/bower/bin/bower install
node_modules/gulp/bin/gulp.js build
