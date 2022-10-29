# MVP Kotlin + Spring Boot + R2DBC + Multiply Databases

The project shows how to connect to multiple databases in a Spring Boot application with r2dbc driver.

## Prerequisites

Installed:

- Docker Compose,
- JDK 17,
- Kotlin 1.7.20,
- IDE - for simple start application.

## How to start MVP

#### 1. Start databases in docker compose:

```
cd ./docker

docker compose up -d

```
#### 2. Verify that the services are up and running:
```
docker compose ps

```
#### 3. After a few minutes, if the state of any component isn’t Up, try:
```
docker compose up -d

```
or
```
docker compose restart <image-name>

```
#### 4. Start Application
Start application in the IDE.

After the application started it will read test data from databases, print it into the IDE console and finish the job.
