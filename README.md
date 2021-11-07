# BT Panorama Code assignment - Users and Posts

This Java server application aggregates two APIs - Users and Posts and joins them
into an aggregated API:  _usersAndPosts_.

## Prerequisites
The project assumes the following tooling:
- Java 11 or newer
- Maven 3.x

## Building
The project can be build into an executable Jar archive via: 
```Bash
$ ./mvnw clean package
```


## Testing
The tests will be run as part of the above build process.
Alternatively, they can be invoked explicitly via:

```Bash
$ ./mvnw clean test
```


## Running
The server can be started via the command:
```Bash
$ mvn spring-boot:run
```
```
[INFO] Scanning for projects...
[INFO] 
[INFO] -------------------------< com.mih:userposts >--------------------------
[INFO] Building userposts 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
...

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.6)

2021-11-07 16:18:54.259  INFO 30049 --- [           main] com.mih.userposts.UserpostsApplication   : Starting UserpostsApplication using Java 11.0.11 on Matts-MacBook-Pro.local with PID 30049 (/Users/mhavilah/projects/interviews/bt/panorama/repo/server/target/classes started by mhavilah in /Users/mhavilah/projects/interviews/bt/panorama/repo/server)
...
2021-11-07 16:18:55.423  INFO 30049 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path '/api'
2021-11-07 16:18:55.435  INFO 30049 --- [           main] com.mih.userposts.UserpostsApplication   : Started UserpostsApplication in 6.493 seconds (JVM running for 6.781)
```
The embedded Tomcat server will start on port 8080 and has the endpoint at:
**/api/usersAndPosts**:

```Bash
$ ./mvnw spring-boot:run&
...
$ curl localhost:8080/api/usersAndPosts 
```
with a response similar to:
```json
[{"id":null,"name":"Bart Simpson","username":"bsimpson","posts":[]}]
```
**NB:**

The additional fields from the user API (email, address etc) have been intentionally omitted.


## System Info
The Spring Boot Actuator API has been installed under: [http://localhost:8080/api/actuator]

Sample endpoints:
### Health
```Bash
$ curl  http://localhost:8080/api/actuator/health
```
```json
{"status":"UP"}
```
### System Information
```Bash
$ curl http://localhost:8080/api/actuator/info
```
```json
{"build":{"author":{"name":"Matt Havilah"},"java":{"version":"11"},"version":"0.0.1-SNAPSHOT","artifact":"userposts","name":"userposts","time":"2021-11-07T05:35:43.539Z","group":"com.mih"}}
```


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.6/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.6/maven-plugin/reference/html/#build-image)
* [Jersey](https://docs.spring.io/spring-boot/docs/2.5.6/reference/htmlsingle/#boot-features-jersey)

