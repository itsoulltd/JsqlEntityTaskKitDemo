#!/usr/bin/env bash
##
profile=MakeAnOrderFromPizzaGuy
application=MakeAnOrderFromPizzaGuy
version=1.0-SNAPSHOT
echo "Building.... ${application}-${version}"

##Maven build:
mvn clean compile assembly:single -P${profile}