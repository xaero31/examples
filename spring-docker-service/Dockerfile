# an old variant

# FROM openjdk:19
# ENV test.property hiFromDocker

# VOLUME user-files -- deprecated :D
# WORKDIR /app
# COPY ./build/libs/docker-example.jar .

# EXPOSE 8081
# CMD ["java", "-jar", "docker-example.jar"]


# multi-stage variant

FROM gradle AS build
WORKDIR /app
COPY . .
RUN gradle build

FROM openjdk:19
WORKDIR /app
ENV test.property hiFromMultiStageDocker
COPY --from=build /app/build/libs/docker-example.jar .

EXPOSE 8081
CMD ["java", "-jar", "docker-example.jar"]