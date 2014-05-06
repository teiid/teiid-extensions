teiid-extensions
================

Teiid extensions and utilities
 * database-service,  is stateless bean that has JPA entities to log messages to a database
 * database-logging-appender, is logging appender that can invoke the "database-service" bean and pass the log message
 * build, contains the deployment jar file.
 
 To build issue
 
 mvn clean install
 
 then unzip the "build/target/build-${version}-dist.zip, on to JBoss EAP installation. Then start JBoss AS server,
 
 then do the following
 
 cd <jboss-eap-6.x>/bin
 
 edit "scripts/teiid-logger-ds.properties", add the {db.driver_name}, {db.url}, {db.user} and {db.password} properties. 
 By default these values are set to H2 database configured with JBoss EAP for development purposes.
  
 Also make sure the jdbc driver for the database you are using is correctly installed in the system, then execute
 
 ./jboss-cli.sh --file=scripts/teiid-add-database-logger.cli --properties=./scripts/teiid-logger-ds.properties
 
 to install database logger for "COMMAND_LOG" and "AUDIT_LOG" in Teiid.