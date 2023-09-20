# task-api-example

### Run with H2 database
This API has implemented with H2 embedded, H2 is simple database to make tests, H2 is working in memory, when you shutdown, the new data will lost.

### Run with MySQL database
If you want to use MySQL, you need to execute these SQL sentences:

``` sql
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

```

You can find these sentences in the file <b>src/main/resources/mysql-setup.sql</b>

### Activate MySQL database
The you need to uncomment the 'MySQL CONFIGURACION' section in <b>src/main/resources/application.properties</b>
to connect to MySQL.

#### Important
This API was implemented with Java 8.

### Start the API
Running by command line:

```
mvn spring-boot:run
```
### Test the API
To test you can import the Postman collection <b>src/main/resources/Tasks Collection.postman_collection.json</b> or you can browse the available endpoints with swagger

<http://localhost:8080/api/swagger-ui.html>
