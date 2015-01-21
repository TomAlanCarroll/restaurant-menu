CREATE KEYSPACE menus WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};
USE menus;
CREATE TABLE Menu (
  id uuid,
  type text,
  menuId text,
  location text,
  PRIMARY KEY (menuId, id)
) WITH CLUSTERING ORDER BY (id DESC);

CREATE TABLE MenuItem (
  id uuid,
  type text,
  menuId text,
  name text,
  description text,
  locale text,
  price decimal,
  PRIMARY KEY (menuId, id)
) WITH CLUSTERING ORDER BY (id DESC);