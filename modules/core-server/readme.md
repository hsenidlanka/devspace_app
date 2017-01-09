# Core-server
RESTful API designed with Jersey 
---
Provide a RESTful API to self-care modules to communicate with the system. Buit using Jersey, JAX-RS implementation. Also powered by the integrated Jetty server.

#### Pre-requisites
* Java Development Kit (JDK) 1.8
* MYSQL Server 5.7
* Apache Maven 3.3
* DevSpace Maven Dependency
* DevSpace Simulator
___
#### Config & Build
1. Download DevSpace Maven dependency from the following link.
https://1drv.ms/u/s!AgR5Dn7i6sy0g5c9PM5ym3FrB78IYA

2. Browse to the directory where the downloaded JAR file exists.

3. Install the jar to maven repository (m2 directory) using the following command.
`$ mvn install:install-file -Dfile=sdp.app.api-1.0.144.jar -DgroupId=hms -DartifactId=sdp.app.api -Dversion=1.0.144 -Dpackaging=jar`

4. Goto the location of the program folder (core-server).
`$ cd <path>/core-server`

5. Run following command.
`$ mvn clean install`

If the build is successful, continue with the further steps.
If an error occurred please check above five steps again.
___
#### Execute the program
1. Goto the bin directory in the target directory.
`$ cd target/bin`</br>

2. Run the following command to start the core-server.
`$ ./core-server start`</br>
___
#### Access the API
To access the API, please use the documentation by one of the following methods.

* SwaggerHub
https://app.swaggerhub.com/api/kasundm5566/pizza-shefu_rest_api_documentation/1.0

* Microsoft Excel sheet
https://1drv.ms/x/s!AgR5Dn7i6sy0gs53WranWWSu6GVZeg