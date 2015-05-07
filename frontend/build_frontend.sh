#!/bin/sh

sudo npm install --global --quiet bower gulp

npm install
bower install

gulp build
