#!/bin/bash
psql -U postgres <<-EOSQL
  CREATE DATABASE dbprojectnc;
  GRANT ALL PRIVILEGES ON DATABASE dbprojectnc TO postgres;
EOSQL

psql -U postgres -d dbprojectnc</tmp/dump.sql
