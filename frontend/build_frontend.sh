#!/bin/sh

npm install --global bower gulp

npm install
bower install

gulp build
