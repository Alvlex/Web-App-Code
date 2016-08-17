FROM java:8

MAINTAINER Alex Vanlint

RUN mkdir -p /runtime/status

ADD target/status-report-1.0-SNAPSHOT.jar /runtime/status

CMD ["java","-jar","/runtime/status/status-report-1.0-SNAPSHOT.jar"]