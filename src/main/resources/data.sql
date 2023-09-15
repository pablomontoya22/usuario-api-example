DROP TABLE IF EXISTS testdb;
 
CREATE TABLE IF NOT EXISTS users (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  surname VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL
);
 
INSERT INTO users (name, surname, email) VALUES
  ('Admin', 'Admin', 'admin@test.com'),
  ('User', 'User', 'user@test.com');