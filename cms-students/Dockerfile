FROM tomcat:jdk8-openjdk
 
COPY ./build/libs/cms-students-1.0.war /usr/local/tomcat/webapps/cms-students.war
COPY ./container_conf/mysql-connector-java-8.0.17.jar /usr/local/tomcat/lib/mysql-connector-java-8.0.17.jar
COPY ./container_conf/context.xml /usr/local/tomcat/conf/context.xml
 
EXPOSE 8080
