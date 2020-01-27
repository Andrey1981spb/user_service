FROM tomcat:9-jre8-alpine

ENV PATH_TOMCAT_USERS /usr/local/tomcat/conf/
ENV PATH_MANAGER /usr/local/tomcat/webapps/manager/
ENV UNIX_SOCKET /var/run/postgresql/.s.PGSQL.5432
ADD config/tomcat-users.xml  /usr/local/tomcat/conf/tomcat-users.xml
ADD config/context.xml /usr/local/tomcat/webapps/manager/META-INF/context.xml
ADD test.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh","run"]