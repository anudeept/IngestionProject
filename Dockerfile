FROM openjdk:8
ADD /target/Ingestion.jar Ingestion.jar
EXPOSE 8080
ENTRYPOINT ["java" ,"-jar" ,"Ingestion.jar"]