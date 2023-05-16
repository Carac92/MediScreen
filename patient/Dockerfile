FROM openjdk:latest
ADD target/patientAPI.jar patientAPI.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-jar","/patientAPI.jar"]
EXPOSE 8081