# MediScreen
Mediscreen is a project that handles patient personnal information, notes about their health situation wrote by doctors
and an assessment of their diabetes situation based on their gender, age and notes.

# Technical

Java Version : 17
Spring boot version : 3.0.5
Docker version : 3.8
MongoDB version : 6.0.5
MySQL

# Setup project

with Docker
1. GitClone the project
2. open it with intellij
3. mvn verify all micro-services
4. docker compose up

With spring

1. GitClone the project
2. open it with intellij
3. launch config-server
4. launch all the services (patient,note, ui)


# UML diagram

<img width="493" alt="image" src="https://github.com/Carac92/MediScreen/assets/96061779/dad2b6a4-1841-41a0-aa86-386ee3dfab66">

# Technic documentation

 - UI micro-service : http://localhost:8080/swagger-ui.html#/
 - note micro-service : http://localhost:8082/swagger-ui.html#/
 - patient micro-service : http://localhost:8080/swagger-ui.html#/

