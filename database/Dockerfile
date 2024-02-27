FROM mysql:8.2.0
COPY config/. /etc/mysql/conf.d
COPY init/. /docker-entrypoint-initdb.d
ENV TZ=Asia/Seoul