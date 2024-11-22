# Use uma imagem do OpenJDK
FROM openjdk:21-jdk-slim

# Defina o diretório de trabalho no container
WORKDIR /app

# Copie o arquivo JAR da aplicação para o diretório de trabalho
COPY target/InnovaTransit-0.0.1-SNAPSHOT.jar /app/InnovaTransit.jar

# Exponha a porta que o aplicativo irá usar
EXPOSE 8080

# Defina o comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "InnovaTransit.jar"]

