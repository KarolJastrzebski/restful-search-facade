#!/bin/sh

for i in events users; do

  docker cp sample/$i.json kj-mongodb:$i.json

  docker exec -t kj-mongodb mongoimport \
    --db example_db \
    --collection $i \
    --authenticationDatabase admin \
    --username root \
    --password example \
    --drop \
    --jsonArray \
    --file $i.json

done
