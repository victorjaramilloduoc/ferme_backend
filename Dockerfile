FROM tomcat:9.0

LABEL maintainer="victorjaramillo"

ADD target/ferme-0.0.1.war /usr/local/tomcat/webapps/

EXPOSE 8080

ENV SHOW_SQL=false

CMD ["catalina.sh", "run"]