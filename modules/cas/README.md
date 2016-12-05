#CAS - Central Authentication Service#

For securing the Admin module, the CAS module has been included. In order to log in to Admin module or to use or access a resource which is inside the Admin module, you have to go through this CAS module.

###Pre-requisites###

* CAS server-4.1.4
* SSL(Secure Socket Layer) configuration
* apache-maven-3.3.9
* tomcat-8.0.32
* mysql Server version: 5.6.28-0

###How to configure###

####1) SSL configuration on Tomcat
**(i)** In   your web server, you need to configure a secure connection before you install CAS sever. For that, first you have  to generate a key  providing an alias and a password. As, in your CLI run the following command.
<pre><code>keytool -keystore $JAVA_HOME/jre/lib/security/cacerts -genkey -alias tomcat -keyalg RSA</code></pre>

**(ii)** Then you will be prompted the following kind of questionnaire,
<pre><code>What is your first and last name?
[Unknown]: localhost
What is the name of your organizational unit?
[Unknown]: localhost
What is the name of your organization?
[Unknown]: localhost
What is the name of your City or Locality?
[Unknown]: localhost
What is the name of your State or Province?
[Unknown]: localhost
What is the two-letter country code for this unit?
[Unknown]: SL
Is CN=localhost, OU=localhost, O=localhost, L=localhost, ST=localhost, C=SL correct?
[no]: yes
</code></pre>
***Note: The CN- Comman Name field in the certificate must be your host name. It can be localhost also.***


**(iii)**  Then you need to export the certificate into server.crt.
 <pre><code>keytool -keystore $JAVA_HOME/jre/lib/security/cacerts -export -alias tomcat -file server.crt</code></pre>

**(iv)** Import the server.crt into your cacerts
<pre><code>keytool -import -file server.crt -keystore $JAVA_HOME/jre/lib/security/cacerts</code></pre>

**(v)** Finally you have to configure tomcat for ssl. So, open the server.xml file inside tomcat/conf directory. Add the following lines or edit as needed.
<pre><code>< Connector port="8443"
    maxThreads="150" minSpareThreads="25" maxSpareThreads="75"
    enableLookups="false" disableUploadTimeout="true" acceptCount="100"
    SSLEnabled="true"
    scheme="https"
    secure="true"
    clientAuth="false"
    sslProtocol="TLS"
    keystoreFile="~/Software/jdk1.8.0_73/jre/lib/security/cacerts"
    keystorePass="changeit"
    truststoreFile="~/Software/jdk1.8.0_73/jre/lib/security/cacerts"
    truststorePass="changeit" /></code></pre>

**(vi)** Check the connection as the following the link.
<code>[https://localhost:8443/cas](https://localhost:8443/cas)</code>
<br/>


####2) Deploy CAS in tomcat

* Get the <code>cas.war</code> file and copy it to the webapps directory of your tomcat server.

###How to build###
* If it is already running, shutdown the tomcat sever.

* Goto your webapps directory where <code>cas.war</code>is existing. Then build the  <code>cas.war</code> using following command<pre><code>mvn clean install -DskipTests;</code></pre>
* Restart the tomcat sever.

###How to run###

* Open your browser and type the url as <pre><code>[https://localhost:8443/cas/admin/users/list](https://localhost:8443/cas/admin/users/list)</code></pre>
* You should be then navigated to the login page of admin module.
