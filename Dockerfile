FROM openjdk:11.0.4-jdk-slim
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/wait-for-it.sh /app/wait-for-it.sh
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["/app/wait-for-it.sh", "mariadb:3306", "--", "java","-Djava.security.egd=file:/dev/./urandom","-cp", "app:app/lib/*", "net.klostergaard.basketservice.BasketServiceApplication"]