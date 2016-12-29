#! /bin/bash

mvn clean install -DskipTests;
rm -r ~/Software/apache-tomcat-8.0.32/webapps/web-selfcare;
rm ~/Software/apache-tomcat-8.0.32/webapps/web-selfcare.war;
mv ~/hsenidlanka/devspace_app/modules/self-care/web-selfcare/target/web-selfcare.war ~/Software/apache-tomcat-8.0.32/webapps;
shutdown.sh;
startup.sh;

