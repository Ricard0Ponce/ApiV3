FROM openjdk:11-jre-slim

ARG user=spring
ARG group=spring
ARG uid=1000
ARG gid=1000

ENV USER=${user}
ENV GROUP=${group}
ENV UID=${uid}
ENV GID=${gid}

RUN groupadd -g ${gid} ${group} && useradd -u ${uid} -g ${group} -s /bin/sh ${user}

USER ${USER}:${GROUP}

ARG JAR_FILE=target/swagger-spring-3.8.1.jar

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]