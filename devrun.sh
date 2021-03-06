#!/usr/bin/env bash

source environments/development.conf
source ~/settings.conf

java -Dserver.port=$PORT \
     -Dspring.profiles.active=$PROFILE \
     -jar $JAR_LOCATION/$JAR_FILE
