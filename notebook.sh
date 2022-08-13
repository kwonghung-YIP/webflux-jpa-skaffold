docker run --name redis -it --rm -p 6379:6379 redis

docker run --name redis -it --rm redislabs/rejson:latest

docker run --name redis -it --rm redis /bin/bash

docker run --name redis-cli -it --link redis redis redis-cli -h redis

docker run --name redis-cli -it --rm --network="host" redis redis-cli -h 127.0.0.1
docker run --name redis-cli -it --rm --network="host" redis redis-cli -h 192.168.19.133

docker run --name postgres -p 5432:5432 \
    -it --rm \
    -e POSTGRES_DB=db \
    -e POSTGRES_USER=john \
    -e POSTGRES_PASSWORD=passwd \
    postgres

docker run --name postgres-cli \
    -it --rm \
    --network host \
    postgres psql -h 192.168.19.133 -d db -U john

docker run --name pgadmin -p 9080:80 \
    -it --rm \
    -e 'PGADMIN_DEFAULT_EMAIL=admin@abc.com' \
    -e 'PGADMIN_DEFAULT_PASSWORD=passwd' \
    dpage/pgadmin4

curl -u john:password localhost:8080/api/v1/person/1235

curl -u john:password localhost:8080/api/v1/person/page

curl -u john:password \
    --request POST \
    --header "Content-Type: application/json" \
    --data "{\"name\": \"peter\", \"email\": \"peter.pan@gmail.com\"}" \
    localhost:8080/api/v1/person/

curl -u john:password \
    --request POST \
    --header "Content-Type: application/json" \
    --data "{\"name\": \"john\", \"email\": \"john.ng@hotmail.com\"}" \
    localhost:8080/api/v1/person/

curl -v -u john:password \
    --request POST \
    --header "Content-Type: application/json" \
    --data "{\"name\": \"peter\", \"email\": \"peter.pan@gmail.com\"}" \
    192.168.19.133/api/v1/person/