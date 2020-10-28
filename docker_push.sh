docker login -u $DOCKER_USER -p $DOCKER_PASS
docker build --tag kimwithglasses/safe-place-db:0.0.1 . && docker push kimwithglasses/safe-place-db
cd SafePlaceAPI && ./gradlew bootJar && docker build --tag kimwithglasses/safe-place-api:0.0.1 . && docker push kimwithglasses/safe-place-api
