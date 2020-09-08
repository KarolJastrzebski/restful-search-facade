# RESTful Search Facade

Please see [original specification](TL-Java-code-test.md).


## Installation and running

This project provides following methods for running the application.


### Standalone mode

1. First, the application depends on mongodb instance running on port `27777`.

       ./run-mongodb.sh

2. Now, it is time to start the RESTful service.

       ./gradlew clean build
       java -jar build/libs/restful-search-facade-0.0.1-SNAPSHOT.jar

   Alternatively, gradle contains task `bootRun` to run spring boot applications.

       ./gradlew bootRun


### Running in docker

Running things separately has its benefits, however, Docker can run multiple containers at once.

    docker-compose up -d --build

Which should start following containers:

 - MongoDB instance running at `localhost:27777`
 - [RESTful application](http://localhost:6868/)
   - debug connection exposed at `localhost:5005`
 - [MongoDB frontend](http://localhost:8081/)


## Loading sample data

In `sample` folder there are `.json` files extracted from [the specification](TL-Java-code-test.md).
Data can be imported into working mongodb instance by executing:

    ./import-sample-data.sh
