CREATE DATABASE taskdb;

CREATE USER 'usertask'@'localhost' IDENTIFIED BY 'task123';

GRANT ALL PRIVILEGES ON taskdb.* To 'usertask'@'localhost';

FLUSH PRIVILEGES;

CREATE TABLE IF NOT EXISTS tareas (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  descripcion VARCHAR(250) NOT NULL,
  fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  vigente BOOLEAN DEFAULT true
);

INSERT INTO tareas (descripcion, fecha_creacion, vigente) VALUES
  ('Tarea 1', '2022-12-31 23.59.59', true),
  ('Tarea 2', '2021-08-10 11.30.00', true);
