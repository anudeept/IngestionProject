# Ingestion Project
Rest API to search TracksBundle.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes

### Prerequisites
````
- Java 8
- Spring Boot web (Rest API's)
- Spring Boot Batch (Reading of XML files )
- Mongodb Database
- Redis Server (Cache)

````
# Architecture
## Components
# Project Setup
### - MongoDB
1. Download MongoDB community version - https://www.mongodb.com/download-center#community
2. Extract tar file
````
$ tar -zxvf mongodb-osx-ssl-x86_64-3.6.2.tgz

````
3. Create data directory
````
$ mkdir /Documents/Ingestion/data/db
````
4. Run MongoDB (default port 27017)
````
cd bin
$ ./mongodb --dbpath /Documents/Ingestion/data/db
````
### - Redis
Download, extract and compile Redis with:

````
$ wget http://download.redis.io/releases/redis-4.0.7.tar.gz
$ tar xzf redis-4.0.7.tar.gz
$ cd redis-4.0.7
$ make
````

Run Redis with:
````
$ cd src
$ ./redis-server
````
### Spring Boot Project
1. Pull project in to Intellij or Eclipse IDE
2. Build and Install Maven Project
3. Run Maven Project in IDE or JAR File
````
$ java -jar Ingestion.jar
````
## Output
Open PostMan and try search API's with any combinations for example
````
- localhost:8080/tracks/search/?artist=Kenny Chesney
- localhost:8080/tracks/search/?genre=Country
- localhost:8080/tracks/search/?title=The Boys of Fall
- localhost:8080/tracks/search/?artist=Kenny Chesney&genre=Country
- localhost:8080/tracks/search/?artist=Kenny Chesney&genre=Country&title=The Boys of Fall


