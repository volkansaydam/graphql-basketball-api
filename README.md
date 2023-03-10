# A GraphQL Basketball Api
This is a basic basketball api which provide 
add, remove and list players of a basketball team.
It is a [Spring Boot Application](https://spring.io/) 
which provide a [GraphQL](https://graphql.org/) API 
endpoint. I use [Spring Boot GraphQL Library](https://docs.spring.io/spring-graphql/docs/current/reference/html/)
for GraphQL operations. It is written with
[Intellij Idea](https://www.jetbrains.com/idea/download/#section=windows)

## With Docker Compose
I create a docker image and publish it from docker hub.
### What You Need To Run
1. [Docker](https://www.docker.com/products/docker-desktop/)
2. [Git](https://git-scm.com/downloads)
### How To Run
After cloning the project, you just need to run docker 
compose file at the project 
directory: `docker-compose up`
### How To Use
The Api Endpoint is `http://localhost:8080/`. If you want a
interface Spring Boot GraphQL Library provide an api
interface which is called [GraphiQL](https://github.com/graphql/graphiql).
You can go to this endpoint: `http://localhost:8080/graphiql`

## With Manuel Installation
### What You Need To Run
1. [MySQL 5.6+](https://dev.mysql.com/downloads/)
, I used [Docker Image](https://hub.docker.com/_/mysql)
 version. You can run it with this 
docker command: `docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=123456 -d -p 3306:3306 -p 33060:33060 mysql:8`
2. [Java 17](https://www.oracle.com/java/technologies/downloads/)
3. [Maven 3.5+](https://maven.apache.org/download.cgi)
4. [Git](https://git-scm.com/downloads)

### How To Run
After cloning the project, go to the project directory and run 
this code: `./mvnw spring-boot:run`. 
And to run tests: `./mvnw test`. Or you can run it 
with your favorite IDE using their maven plugin.

### How To Use
The Api Endpoint is `http://localhost:8080/`. If you want a 
interface Spring Boot GraphQL Library provide an api 
interface which is called [GraphiQL](https://github.com/graphql/graphiql).
You can enable this feature with changing the 
`spring.graphql.graphiql.enabled` options false to true 
in the application properties. After rerun, you can 
go to this endpoint: `http://localhost:8080/graphiql`






