# Etapa 1: Construir o aplicativo
FROM maven:3.9.9-amazoncorretto-21-alpine AS build

# Defina o diretório de trabalho dentro do container
WORKDIR /app

# Copie o arquivo pom.xml e baixe as dependências
COPY pom.xml .

# Baixar as dependências do Maven.
RUN mvn dependency:go-offline

# Copie o código fonte do projeto
COPY src /app/src

# Compile o projeto e gere o arquivo JAR
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final para rodar a aplicação
FROM openjdk:21-jdk-slim

# Defina o diretório de trabalho dentro do container
WORKDIR /app

# Copie o JAR gerado da etapa de build
COPY --from=build /app/target/sgpa-0.0.1-SNAPSHOT.jar /app/sgpa.jar

# Exponha a porta que a aplicação vai rodar
EXPOSE 8080

# Defina o comando de execução da aplicação
ENTRYPOINT ["java", "-jar", "/app/sgpa.jar"]
