FROM tomcat:9.0.31-jdk11-openjdk

ENV PATH_TOMCAT_USERS /usr/local/tomcat/conf/
ENV PATH_MANAGER /usr/local/tomcat/webapps/manager/
ENV UNIX_SOCKET /var/run/postgresql/.s.PGSQL.5432
ADD src/tomcat_config  /usr/local/tomcat/conf/tomcat-users.xml
ADD src/tomcat_config /usr/local/tomcat/webapps/manager/META-INF/context.xml
ADD src/tomcat_config /usr/local/tomcat/conf/server.xml
ADD target/user_service.war /usr/local/tomcat/webapps/ROOT.war
RUN mkdir -p socket_volume/
RUN chmod 777 socket_volume/

CMD ["catalina.sh","run"]