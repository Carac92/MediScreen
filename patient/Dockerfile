FROM openjdk:19
ADD target/patientAPI.jar patientAPI.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-jar","/patientAPI.jar"]
EXPOSE 8081