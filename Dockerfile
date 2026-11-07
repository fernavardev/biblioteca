# ---- Etapa 1: compilar con Maven Wrapper ----
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app
COPY .mvn .mvn
COPY mvnw pom.xml ./
# mvnw puede venir con saltos de linea de Windows (CRLF)
RUN sed -i 's/\r$//' mvnw && chmod +x mvnw && ./mvnw -q -B dependency:go-offline
COPY src src
RUN ./mvnw -q -B clean package -DskipTests

# ---- Etapa 2: imagen liviana solo con el JRE ----
FROM eclipse-temurin:21-jre

WORKDIR /app
RUN useradd --system --no-create-home appuser
COPY --from=build /app/target/biblioteca-0.0.1-SNAPSHOT.jar /app/biblioteca.jar

# La wallet se monta externamente al ejecutar el contenedor
ENV TNS_ADMIN=/app/wallet

USER appuser
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/biblioteca.jar"]