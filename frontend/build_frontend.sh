#!/bin/sh

sudo npm install --global --quiet bower gulp
sudo chown -R $USER:$GROUP ~/.npm

npm install
bower install

gulp build
