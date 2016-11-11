#! /bin/bash

stoptomcat2.sh;
mvn clean install;
rm -r /usr/local/apache-tomcat2/webapps/web-selfcare;
rm /usr/local/apache-tomcat2/webapps/web-selfcare.war;
cp /home/hsenid/Projects/devspace_app/modules/self-care/web-selfcare/target/web-selfcare.war /usr/local/apache-tomcat2/webapps;
starttomcat2.sh;
