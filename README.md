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
### - Components
![ingestion_components](https://user-images.githubusercontent.com/32476754/35489198-7996422c-0448-11e8-96cf-9e4c6385a0ee.png)
### - Request Flow
![ingestion_requestflow](https://user-images.githubusercontent.com/32476754/35489818-5437dec6-044f-11e8-9ec7-e7bf31730eeb.png)
### Redis
* MaxMemory Policy - allkeys-lru: evict keys by trying to remove the less recently used (LRU) keys first, in order to make space for the new data added.
#### Redis - Datastructure
````
- Track_Search
 * key - Artist/Genre/title
 * Value - [000-SAMPLE-ABC,111-SAMPLE-ABC]
- Track_R
 * key - 000-SAMPLE-ABC
 * Value - Track object
````
### MongoDB Datastructure
````
{
    "_id" : ObjectId("5a6d43e71ccc57166cf182ae"),
    "PRODUCTINFO" : {
        "LANGUAGE" : {
            "AMWKEY" : "000-SAMPLE-ABC",
            "COPYRIGHTYEAR" : "2010",
            "PUBLISHER" : "(P) 2010 Sony Music Entertainment",
            "EXPLICITLYRICS" : "false",
            "TITLE" : "The Boys of Fall",
            "DURATION" : 392,
            "COPYRIGHT" : "(P) 2010 Sony Music Entertainment",
            "TRACKNUMBER" : 1,
            "ARTIST" : "Kenny Chesney"
        }
    },
    "GENRES" : [ 
        {
            "GENRENAME" : "Country"
        }
    ],
    "FILES" : [ 
        {
            "FILEINFO" : {
                "ACCESSSTRING" : "resources/000-SAMPLE-ABC.mp3"
            }
        }
    ]
}
````
## Scalable Architecture
Scale each layer independently
* Rest API Layer with Load balancer and N no of nodes
* Redis Cluster
* Implemting LRU cache in redis to remove least accessed data
* MongoDB horizontal sharding wit replica sets

![ingestion_scalable](https://user-images.githubusercontent.com/32476754/35490125-2ecde8da-0452-11e8-85d7-2cd8016e8136.png)

## Assumptions
* Data will only get inserted or updated from XML file at initialization of project
* User can only search with attributes Artist/genre/title

## Suggestion
* When XML file data is huge it will take some in project initialization i would recommed to data reading and insertion as a seperate serive

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


