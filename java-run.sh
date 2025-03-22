#!/usr/bin/env bash
##
profile=MakeAnOrderFromPizzaGuy
application=MakeAnOrderFromPizzaGuy
version=1.0-SNAPSHOT
echo "Running....${application}-${version}.jar"

##Setting Environment Variables:
export Username="m.towhid.islam"
export Password="my-pass"

##Run-Jar:
#cmd ~>$ java -jar -D<vm-key=value> target/application.jar arg_var1 arg_var2
java -jar \
-DskippTests=true \
target/${application}-${version}.jar \
"price:12.00" "currency:euro"
