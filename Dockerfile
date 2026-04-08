# ETAP 1: Budowanie aplikacji
FROM eclipse-temurin:21-jdk AS build

# Aktualizacja menedżera pakietów i instalacja findutils (używamy apt-get zamiast microdnf)
RUN apt-get update && apt-get install -y findutils

WORKDIR /app

# Kopiowanie plików konfiguracyjnych Gradle
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle gradle

# Kopiowanie kodu źródłowego
COPY src src

# Nadanie uprawnień do wykonywania skryptu gradlew i budowanie projektu
RUN chmod +x ./gradlew
RUN ./gradlew build -x test

# ETAP 2: Uruchamianie aplikacji
FROM eclipse-temurin:21-jdk
VOLUME /tmp

# Kopiowanie zbudowanego pliku JAR z poprzedniego etapu
COPY --from=build /app/build/libs/*.jar app.jar

# Komenda uruchamiająca aplikację
ENTRYPOINT ["java","-jar","/app.jar"]

# Informacja o porcie (domyślny dla Spring Boot / Java web)
EXPOSE 8080