# Start with a base image containing Java runtime
FROM mysql:8.0.21

# Add Author info
LABEL maintainer="kimwithglasses@kakao.com"

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=moBack
#ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root

COPY ./sql-scripts/ /docker-entrypoint-initdb.d/

CMD ["mysqld"]

