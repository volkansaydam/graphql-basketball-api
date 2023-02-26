FROM openjdk:17
MAINTAINER volkansaydam
COPY target/basketballapi-0.0.1-SNAPSHOT.jar basketballapi-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/basketballapi-0.0.1-SNAPSHOT.jar"]