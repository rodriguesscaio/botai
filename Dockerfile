# Estágio 1: Build (para compilar e empacotar a aplicação)
FROM gradle:jdk17 AS builder

WORKDIR /app

# Copia os arquivos de build do Gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Copia o código-fonte da aplicação
COPY src ./src

# Constrói a aplicação e gera o arquivo .jar
RUN gradle bootJar --no-daemon

# Estágio 2: Execução (para rodar a aplicação)
# Use uma imagem base mais leve, como o OpenJDK JRE
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo .jar do estágio de build para o estágio de execução
COPY --from=builder /app/build/libs/botai-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta 10000, que é a porta padrão do Render
EXPOSE 10000

# Define o comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]