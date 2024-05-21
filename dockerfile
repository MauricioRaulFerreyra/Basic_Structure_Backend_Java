# Utiliza la imagen base OpenJDK 17 con Alpine
FROM openjdk:17-jdk-alpine

# Instala bash
RUN apk add --no-cache bash

# Copia el script wait-for-it.sh a la imagen
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# Copia tu archivo JAR a la imagen
COPY target/demo-0.0.1-SNAPSHOT.jar java_app.jar

# Define el comando de inicio usando wait-for-it.sh para esperar a la base de datos
ENTRYPOINT ["/wait-for-it.sh", "java_db:5432", "--", "java", "-jar", "java_app.jar"]
