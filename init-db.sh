set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE DATABASE "loyalty-program";
  GRANT ALL PRIVILEGES ON DATABASE "loyalty-program" TO postgres;

  \connect "loyalty-program";
  CREATE SCHEMA cards;
EOSQL

