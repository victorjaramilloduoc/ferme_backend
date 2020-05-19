## Commit inicial
 * versión 0.0.1
 
Documentación API local:

> [Swagger] (http://localhost:9000/swagger-ui.html)
 
Empaquetar aplicación:

> [Maven] `mvn clean install`
 
Correr aplicación en modo local:

> [Java] `java -jar target/ferme-0.0.1.war`
 
Correr aplicación en contenedor Docker 
* Primero se debe crear la imagen

> [Docker] `docker build ferme .`

 * Luego

> [Docker] `docker container run -d -p 8080:8080 --name ferme ferme`