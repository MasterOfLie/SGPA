# SGPA - Sistema de Gestão de Processos Administrativos

## SOBRE
Este projeto é um Sistema de Gestão de Processos Administrativos (SGPA) desenvolvido com **Spring Boot**. O objetivo do SGPA é auxiliar instituições públicas e privadas na organização de processos internos, oferecendo funcionalidades como:

- Criação e gestão de processos
- Controle de tramitação entre departamentos
- Gestão de documentos
- Automação de fluxos de trabalho
- Gerenciamento de permissões de acesso

---

## Requisitos
- **Docker**
- **Banco de Dados MySQL**
- **Conta de Armazenamento no Azure** (para integração com Azure Blobs)
- **Variáveis de Ambiente**

---

## Variáveis de Ambiente
Certifique-se de definir as seguintes variáveis de ambiente para configurar o SGPA corretamente:

| Variável                             | Descrição                                                               |
|--------------------------------------|-------------------------------------------------------------------------|
| `SPRING_DB_HOST`                     | Host do banco de dados MySQL                                           |
| `SPRING_DB_PORT`                     | Porta do banco de dados MySQL                                          |
| `SPRING_DB_NAME`                     | Nome do banco de dados para a aplicação                                |
| `SPRING_DATASOURCE_USERNAME`         | Nome de usuário do banco de dados                                      |
| `SPRING_DATASOURCE_PASSWORD`         | Senha do banco de dados                                                |
| `SPRING_SERVLET_MULTIPART_MAX_FILE_SIZE` | Tamanho máximo permitido para upload de arquivo                    |
| `SPRING_SERVLET_MULTIPART_MAX_REQUEST_SIZE` | Tamanho máximo permitido para upload de requisição              |
| `SERVER_PORT`                        | Porta na qual a aplicação será executada                               |
| `AZURE_STORAGE_CONNECTION_STRING`    | String de conexão do Azure Storage                                     |
| `AZURE_STORAGE_CONTAINER_NAME`       | Nome do contêiner de armazenamento no Azure                            |
| `SPRING_PROFILES_ACTIVE`             | Perfil ativo (exemplo: `dev` ou `prod`)                                |

---

## Configuração do Docker

Crie um arquivo `Dockerfile` no diretório do projeto com o seguinte conteúdo:

```dockerfile
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
EXPOSE ${SERVER_PORT}

# Defina o comando de execução da aplicação
ENTRYPOINT ["java", "-jar", "/app/sgpa.jar"] 

```

## DISCLAIMER
Este projeto é uma aplicação em desenvolvimento e de estudo. Não me responsabilizo por eventuais problemas, falhas ou perdas decorrentes do uso ou da integração deste sistema em ambientes de produção. O código e as funcionalidades podem sofrer alterações e melhorias constantes. Utilize o sistema apenas em ambiente de desenvolvimento e para fins educacionais.

---

## Licença

Este projeto está licenciado sob a **MIT License** - veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

### MIT License

Copyright (c) 2024 Matheus Henrique Ferreira

A permissão é concedida, gratuitamente, a qualquer pessoa que obtenha uma cópia deste software e dos arquivos de documentação associados (o "Software"), para lidar no Software sem restrições, incluindo, sem limitação, os direitos de usar, copiar, modificar, mesclar, publicar, distribuir, sublicenciar e/ou vender cópias do Software, e permitir que as pessoas a quem o Software é fornecido o façam, sujeito às seguintes condições:

O aviso de copyright acima e este aviso de permissão devem ser incluídos em todas as cópias ou partes substanciais do Software.

O Software é fornecido "COMO ESTÁ", sem qualquer garantia expressa ou implícita, incluindo, mas não se limitando a, garantias de comercialização, adequação a um propósito específico e não violação. Em nenhum caso os autores ou titulares do copyright serão responsáveis por qualquer reclamação, dano ou outra responsabilidade, seja em uma ação de contrato, ato ilícito ou de outra forma, decorrente de, ou em conexão com, o Software ou o uso ou outros negócios no Software.