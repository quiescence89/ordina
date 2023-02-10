#!/bin/sh
mvn clean package && docker build -t de.rieckpil.blog/jakartaee-8-project .
docker rm -f jakartaee-8-project || true && docker run -d -p 9080:9080 -p 9443:9443 --name jakartaee-8-project de.rieckpil.blog/jakartaee-8-project