
docker rm -f cards-db

docker run --name cards-db \
           -p "7000:5432" \
           -v "./../init-db.sh:/docker-entrypoint-initdb.d/init-user-db.sh" \
           -e POSTGRES_URL=database \
           -e POSTGRES_USER=postgres \
           -e POSTGRES_PASSWORD=postgres \
           -d postgres
