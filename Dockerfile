#Build stage

FROM gradle:latest AS BUILD
WORKDIR /home/app/
COPY . . 
RUN gradle build

# Package stage

FROM openjdk:17-alpine
ENV JAR_NAME=api-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/home/app/
WORKDIR $APP_HOME
COPY --from=BUILD $APP_HOME .
EXPOSE 8080
ENTRYPOINT exec java -jar $APP_HOME/build/libs/$JAR_NAME