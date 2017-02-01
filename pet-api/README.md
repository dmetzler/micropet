# MicroPET :: PET API

This is a simple CRUD API to persist Pets in a DB



    /api/pet/v1/          GET List all pets   ?category={catId}
                          POST creates a pet
    /api/pet/v1/pet/{id}  GET Gets a pet
                          PUT Modifies a pet
                          DELETE Delete a pet

## How to build / run

Edit the `conf.yml` file to update DB parameters and then :

    mvn clean package
    java -jar target/pet-api-1.0-SNAPSHOT.jar server conf.yml


## Build a Docker image

    mvn clean package docker:build
    docker run -it -v `pwd`:/conf dmetzler/pet-api server conf/conf.yml

