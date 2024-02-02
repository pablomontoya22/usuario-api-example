# usuario-api-example

### Levantar con H2 Base de datos
Esta aplicación funciona con H2, la cual es una base de datos que se carga en la memoria, es util para hacer pruebas sin necesidad de configurar un servidor de base de datos, la configuración actual H2 está para solo trabajar en memoria, lo que quiere decir que cuando apague el servidor, la data se perderá.

### Estrucutura de las tablas

``` sql
CREATE TABLE IF NOT EXISTS users (
  id UUID NOT NULL DEFAULT random_uuid() PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(50) NOT NULL,
  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  modified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_login TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  token VARCHAR(500) NOT NULL,
  is_active BOOLEAN DEFAULT true
);

ALTER TABLE users
ADD CONSTRAINT users_uq_email
UNIQUE (email);

CREATE TABLE IF NOT EXISTS phones (
  id UUID NOT NULL DEFAULT random_uuid() PRIMARY KEY,
  number VARCHAR(15) NOT NULL,
  city_code VARCHAR(5) NOT NULL,
  country_code VARCHAR(5) NOT NULL,
  user_id UUID,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


```

Este script se encuentra en <b>src/main/resources/data.sql</b>

#### Importante
Esta API está desarrollada con la versión Java 8.

### Instalar dependencias
Linea de comandos:

```
mvn clean install
```

### Iniciar el servidor
Linea de comandos:

```
mvn spring-boot:run
```
### Probar la API
Esta API se puede probar importando la colección de Postman ubicada en la carpeta <b>src/main/resources/Users Collection.postman_collection.json</b> o también se puede probar usando swagger en la url:

<http://localhost:8080/api/swagger-ui.html>
