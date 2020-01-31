FROM tomcat:9-jre8-alpine

ENV PATH_TOMCAT_USERS /usr/local/tomcat/conf/
ENV PATH_MANAGER /usr/local/tomcat/webapps/manager/
ADD config/tomcat-users.xml  /usr/local/tomcat/conf/tomcat-users.xml
ADD config/context.xml /usr/local/tomcat/webapps/manager/META-INF/context.xml
ADD test.war /usr/local/tomcat/webapps/ROOT.war
RUN mkdir -p socket_volume/
RUN chmod 777 socket_volume/

CMD ["catalina.sh","run"]