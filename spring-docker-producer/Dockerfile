# an old variant
#
# FROM openjdk:19
# WORKDIR /app
# COPY ./build/libs/docker-producer.jar .
# CMD ["java", "-jar", "docker-producer.jar"]


# multi-stage variant

FROM gradle AS build
WORKDIR /app
COPY . .
RUN gradle build

FROM openjdk:19
WORKDIR /app
COPY --from=build /app/build/libs/docker-producer.jar .
CMD ["java", "-jar", "docker-producer.jar"]