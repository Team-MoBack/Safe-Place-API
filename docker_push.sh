#!/bin/bash
echo "${DOCKER_PASS}" | docker login -u "${DOCKER_USER}" --password-stdin
docker build --tag kimwithglasses/safe-place-db:0.0.1 . && docker push kimwithglasses/safe-place-db
cd SafePlaceAPI && ./gradlew bootJar && docker build --tag kimwithglasses/safe-place-api:0.0.1 . && docker push kimwithglasses/safe-place-api
