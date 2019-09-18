# basket-service
Simple basket micro-service

## Running the project
For running the project, you need to install docker and docker-compose.
Then simply run the following script:
```
./buildAndRun.sh
```
This will first run the build process, which includes building a docker image,
and then boot up both a MariaDB database for persistance, and the application itself.
