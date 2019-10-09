FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD melelee.jar melelee.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/melelee.jar"]