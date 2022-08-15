#!/bin/bash

CERT_NAME=selfsigned

openssl req -newkey rsa:2048 -nodes -keyout $CERT_NAME.key -x509 -days 365 -out $CERT_NAME.crt

openssl x509 -text -noout -in $CERT_NAME.crt

openssl pkcs12 -inkey $CERT_NAME.key -in $CERT_NAME.crt -export -out $CERT_NAME.p12

openssl pkcs12 -in $CERT_NAME.p12 -noout -info