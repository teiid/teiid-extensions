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
 
 edit "scripts/teiid-add-database-logger.cli", add the {jdbc-url}, {driver-name}, {user} and {password}. 
 Also make sure the jdbc driver is installed in the system, then execute
 
 ./jboss-cli.sh --file=scripts/teiid-add-database-logger.cli
 
 to install database logger for "COMMAND_LOG" and "AUDIT_LOG"