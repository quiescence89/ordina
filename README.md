# Project jakartaee-8-project for Ordina by Michael Jungen

Prerequisits:
1. Maven
2. Docker
3. OpenJDK 11

Steps to run this project:

1. Start your Docker daemon
2. Execute `./buildAndRun.sh` (Linux/MacOs) or `buildAndRun.bat` (Windows)
3. Wait until Open Liberty is up- and running (e.g. use `docker logs -f CONTAINER_ID`)

Then, in theory the REST services should be accessible via the following URLs. However, due to time constraints it is not working at the moment.
4. Visit http://localhost:9080/resources/analyze/calculateHighestFrequency/{text}
5. Visit http://localhost:9080/resources/analyze/calculateFrequencyForWord/{text}/{word}
6. Visit http://localhost:9080/resources/analyze/calculateMostFrequentNWords/{text}/{n}