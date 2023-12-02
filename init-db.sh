set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE DATABASE "loyalty-program-cards-service";
  GRANT ALL PRIVILEGES ON DATABASE "loyalty-program-cards-service" TO postgres;

  \connect "loyalty-program-cards-service";
EOSQL

