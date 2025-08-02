# 🌐 spring-jwt-security

A lightweight JWT authentication library for Spring Boot. Easily secure your REST endpoints using the `@AuthRequired` annotation and decode JWT tokens without boilerplate.

## 🔧 Features

* ✅ `@AuthRequired` annotation to protect controller methods
* ✅ AOP-powered JWT verification
* ✅ Token creation & decoding via `JwtService`
* ✅ Externalized secret config via `application.properties`
* ✅ Auto-configuration via `JwtAutoConfiguration`

---

## 📦 Installation

In your consuming Spring Boot project, add the local dependency:

```xml
<dependency>
  <groupId>digital.josef.jwtsecurity</groupId>
  <artifactId>spring-jwt-security</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

> **Note:** Until published to a Maven repository, install the JAR locally with:
>
> ```bash
> mvn install
> ```

---

## ⚙️ Configuration

Add your JWT secret in `application.properties`:

```properties
jwt.secret=my_very_secret_key
```

---

## 🔐 Protect Endpoints

Annotate controller methods with `@AuthRequired`:

```java
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @GetMapping("/secure")
    @AuthRequired
    public String secureEndpoint() {
        return "Protected content";
    }
}
```

---

## 🔑 Generate Tokens

Use the provided `JwtService`:

```java
@Autowired
private JwtService jwtService;

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    Map<String, Object> claims = JwtService.generateClaims("user-id");
    String token = jwtService.generateToken(claims, request.getEmail());

    return ResponseEntity.ok(Map.of("token", token));
}
```

---

## 📜 License

MIT

---

## 👤 Author

Built by [josef.digital](https://josef.digital)
