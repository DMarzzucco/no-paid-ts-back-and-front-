# Imagen base de Maven para compilar el proyecto
FROM maven:3.8.5-eclipse-temurin-17 AS build

# Directorio de trabajo en el contenedor
WORKDIR /app

# Copiar archivos de proyecto al contenedor
COPY pom.xml .
COPY src ./src

# Ejecutar el comando de compilación
RUN mvn clean package -DskipTests

# Imagen base para el JAR ejecutable
FROM eclipse-temurin:17-jre-alpine

# Directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo JAR generado desde la fase de compilación
COPY --from=build /app/target/DeathTime.API.Spring-0.0.1-SNAPSHOT.jar app.jar

# Variable de entorno para configurar el puerto
ENV PORT=8080

# Exponer el puerto de la aplicación
EXPOSE $PORT

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]