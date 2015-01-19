CREATE KEYSPACE menus WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};
USE menus;
CREATE TABLE Menu (
  id uuid PRIMARY KEY,
  name text,
  location text
);

CREATE TABLE MenuItem (
  id uuid,
  type text,
  menu text,
  location set,
  location text,
);