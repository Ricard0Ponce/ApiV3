FROM openjdk:17-jre-slim
ARG user=spring
ARG group=spring
ARG uid=1000
ARG gid=1000
RUN groupadd -g ${gid} ${group} && useradd -u ${uid} -g ${group} -s /bin/sh ${user}
USER spring:spring  
ARG JAR_FILE=target/APIV3.jar

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]