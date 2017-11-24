FROM openjdk:8
ADD build/libs/springbootwebapp-0.0.1-SNAPSHOT.jar springbootwebapp-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","springbootwebapp-0.0.1-SNAPSHOT.jar"]