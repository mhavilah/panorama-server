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
The **unit tests** will be run as part of the above build process.
Alternatively, they can be invoked explicitly via:

```Bash
$ ./mvnw clean test
```

### Integration Tests
By default, the slower Integration tests are excluded from the Maven Surefire based unit test run.

Integration Tests start an embedded Tomcat Server via Spring Boot and issue API calls to its published endpoints.

```Bash
$ ./mvn verify -Pfailsafe
```

```
...
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.mih.userposts.UserPostEndpointIT
...
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.6)

11:46:28.794 [main] INFO  com.mih.userposts.UserPostEndpointIT - Starting UserPostEndpointIT using Java 11.0.11 on Matts-MacBook-Pro.local with PID 62161 (started by mhavilah in /Users/mhavilah/projects/interviews/bt/panorama/repo/server)
11:46:28.800 [main] INFO  com.mih.userposts.UserPostEndpointIT - No active profile set, falling back to default profiles: default
11:46:31.736 [main] INFO  o.s.b.w.e.tomcat.TomcatWebServer - Tomcat initialized with port(s): 0 (http)
11:46:31.755 [main] INFO  o.a.coyote.http11.Http11NioProtocol - Initializing ProtocolHandler ["http-nio-auto-1"]
11:46:31.756 [main] INFO  o.a.catalina.core.StandardService - Starting service [Tomcat]
11:46:31.757 [main] INFO  o.a.catalina.core.StandardEngine - Starting Servlet engine: [Apache Tomcat/9.0.54]
...
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 22.916 s - in com.mih.userposts.UserPostEndpointIT
...
[INFO] --- maven-failsafe-plugin:2.22.2:verify (default) @ userposts ---
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  02:15 min
[INFO] Finished at: 2021-11-08T11:46:47+11:00
[INFO] ------------------------------------------------------------------------
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
[{"id":1,"name":"Leanne Graham","username":"Bret",
  "posts":[{"userId":1,"id":1,"title":"sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
    "body":"quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"},
    {"userId":1,"id":2,"title":"qui est esse","body", ....
```
**NB:**

The additional fields from the downstream user API (email, address etc) have been intentionally omitted.


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

### Metrics
A simple runtime metrics facility has been added as an example of how the API might be readied for production operation.

#### Profiling
Timing profile data is captured for several of the services within this API:
- Round trip API time for downstream Users API
- Round trip API time for downstream Posts API
- Total time for UsersAndPosts API response assembly

This data can be retrieved via the **Prometheus** compatible Actuator endpoint:
```Bash
$ curl -s http://localhost:8080/api/actuator/prometheus | grep http_server_requests_seconds_max
```
```
# HELP http_server_requests_seconds_max  
# TYPE http_server_requests_seconds_max gauge
http_server_requests_seconds_max{exception="None",method="GET",outcome="SUCCESS",status="200",uri="/usersAndPosts",} 0.450938814
http_server_requests_seconds_max{exception="None",method="GET",outcome="SUCCESS",status="200",uri="/actuator/prometheus",} 0.008422539
http_server_requests_seconds_max{exception="None",method="GET",outcome="SUCCESS",status="200",uri="/actuator",} 0.0
```



