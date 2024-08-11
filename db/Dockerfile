FROM postgres:latest
ENV POSTGRES_PASSWORD=postgres
ENV POSTGRES_USER=postgres
EXPOSE 5432
COPY dump.sql /tmp/dump.sql
COPY init.db.sh /docker-entrypoint-initdb.d/init.db.sh