#! /bin/bash
TOMCAT_HOME=$CATALINA_HOME;
$TOMCAT_HOME/bin/shutdown.sh;
native2ascii src/main/resources/messages_ja_JP.properties src/main/resources/messages_ja.properties
rm -r $TOMCAT_HOME/webapps/web-selfcare;
rm $TOMCAT_HOME/webapps/web-selfcare.war;
mvn clean install -DskipTests;
# -DskipTests: Skips testing
cp target/web-selfcare.war $TOMCAT_HOME/webapps/web-selfcare.war;
$TOMCAT_HOME/bin/startup.sh;