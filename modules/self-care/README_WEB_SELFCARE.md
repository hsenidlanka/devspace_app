## PizzaShefu Web Selfcare

[![N|Solid](http://i.imgur.com/onfjH4w.jpg)](https://nodesource.com/products/nsolid)

### Introduction
PizzaShefu Web selfcare is the web interface which the customer gets to Intract. Using web selfcare customer can browse Menu, order Online etc.

### Prerequisite
The project created using following sdk's and tools.
  - java version "1.8.0_74"
  - Java(TM) SE Runtime Environment (build 1.8.0_74-b02)
  - Apache Maven 3.3.9
  - Spring Framework 4.3.3.RELEASE
  - jstl 1.2
  - javax.servlet-api 3.1.0
  - hibernate-validator 5.3.0.Final
  - json-simple 1.1
  - slf4j-api 1.7.21 using log4j-core 2.7
**_and deployed and tested on_**
  - apache-tomcat-8.0.32 
### Configuration and building
Web selfcare has no it's own Database bacause it's totally based on Rest API calls. Therefor you need to configure and deploy *[core server](https://github.com/hsenidlanka/devspace_app/tree/master/modules/core-server)* before deploying Web Selfcare. After that Go to 
```sh
cd $BASE_FOLDER/modules/self-care/web-selfcare
```
then type following command to generate war file.
```sh
mvn clean install
```

### How to Deploy

After successfully completing above operation you can see .war file in the 
```sh
$BASE_FOLDER/modules/self-care/web-selfcare/target
```
folder copy that folder to.
```sh
cd $CATALINA_HOME/webapps
```
Make Sure your core server and Tomcat server up and running. Then type in web browser's address bar
```sh
localhost:8080/web-selfcare
```