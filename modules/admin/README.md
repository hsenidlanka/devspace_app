
# Pizza Shefu Admin Module
The admin module is for the Administration purposes of the Pizza Shefu. The main management areas include Users Management including customers and staff, Item Management, Category Management,Package Management and Notification Management.   

#### Prerequisities
 - core-logic 0.0.3 as a dependency in pom.xml file
 - CAS war in the server

#### Configuration
 - first go to the Admin module directory and execute `mvn clean install` 
 - admin.war file is produced
 - move to the tomcat installation directory. It contains a `webapp` directory
 - copy and paste the produced war file in it 
 - start tomcat by running `./startup.sh` file in the bin directory 

#### How to access the application
 - In the browser type `https://localhost/admin/PizzaShefu`


