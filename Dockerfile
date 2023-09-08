FROM openjdk:11-jre-slim-buster
WORKDIR /srv/cc-api
EXPOSE 8080 8787
COPY target/cc-api.jar bin/cc-api.jar
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,address=*:8787,server=y,suspend=n","-jar","bin/cc-api.jar"]
