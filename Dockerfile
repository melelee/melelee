FROM openjdk:8-jdk-alpine
MAINTAINER melelee@qq.com
ADD target/melelee.jar /root/app/melelee.jar
CMD java -jar /root/app/melelee.jar