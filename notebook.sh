docker run --name redis -it --rm -p 6379:6379 redis

docker run --name redis -it --rm redislabs/rejson:latest

docker run --name redis -it --rm redis /bin/bash

docker run --name redis-cli -it --link redis redis redis-cli -h redis

docker run --name redis-cli -it --rm --network="host" redis redis-cli -h 127.0.0.1
docker run --name redis-cli -it --rm --network="host" redis redis-cli -h 192.168.19.133

docker run --name postgres -p 5432:5432 \
    -it --rm \
    -e 'POSTGRES_DB=db' \
    -e 'POSTGRES_USER=john' \
    -e 'POSTGRES_PASSWORD=passwd' \
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

curl -k -v https://localhost:8080/api/v1/person/1235

curl -k -v --http2 https://localhost:8080/api/v1/person/1235

curl -k -v --http2 -u john:password https://localhost:8080/api/v1/person/09cbf36c-b4fe-4527-b43d-cee078113c3f

curl -k -v --http2 -u john:password https://localhost/api/v1/person/09cbf36c-b4fe-4527-b43d-cee078113c3f

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

curl -k -v -u john:password \
    --request POST \
    --header "Content-Type: application/json" \
    --data "{\"name\": \"peter\", \"email\": \"peter.pan@gmail.com\"}" \
    https://192.168.19.133/api/v1/person/

git config --global credential.helper cache

openssl req -newkey rsa:2048 -nodes -keyout key.pem -x509 -days 365 -out certificate.pem
openssl x509 -text -noout -in certificate.pem
openssl pkcs12 -inkey key.pem -in certificate.pem -export -out certificate.p12
openssl pkcs12 -in certificate.p12 -noout -info


https://www.digitalocean.com/community/tutorials/openssl-essentials-working-with-ssl-certificates-private-keys-and-csrs
https://devopscube.com/create-self-signed-certificates-openssl/
https://www.ibm.com/docs/en/api-connect/10.0.1.x?topic=overview-generating-self-signed-certificate-using-openssl

