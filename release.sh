#!/bin/bash

set -ev

./gradlew build dockerPush --debug