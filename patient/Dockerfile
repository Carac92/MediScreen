FROM openjdk:latest
ADD target/patientAPI.jar patientAPI.jar
ENV DB_USER=root
ENV DB_PW=Letizia+1
ENV DB_URL=jdbc:mysql://localhost:3306/patientdb
ENTRYPOINT ["java","-jar","/patientAPI.jar"]
EXPOSE 8081