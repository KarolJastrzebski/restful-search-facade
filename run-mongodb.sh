#!/bin/sh

docker run -d --rm --name kj-mongodb -p 27777:27017 \
  -e MONGO_INITDB_ROOT_USERNAME=root \
  -e MONGO_INITDB_ROOT_PASSWORD=example \
  mongo
