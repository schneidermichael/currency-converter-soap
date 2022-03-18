FROM tomee:8.0.10-plume

LABEL maintainer="michael.schneider@stud.fh-campuswien.ac.at"

COPY target/currency-converter-1.0.0.war /usr/local/tomee/webapps/currency-converter-1.0.0.war
COPY src/main/tomee/conf/tomcat-users.xml /usr/local/tomee/conf/tomcat-users.xml
COPY src/main/tomee/conf/server.xml /usr/local/tomee/conf/server.xml
COPY src/main/tomee/conf/localhost.jks /usr/local/tomee/conf/localhost.jks

EXPOSE 8080
EXPOSE 4321

CMD ["catalina.sh", "run"]
