# Rest Assured API Testing

This project is a basic API automation framework built with Java, Maven, TestNG, and Rest Assured. It tests the Conduit API hosted at:

```text
https://conduit-api.bondaracademy.com
```

## Project Structure

```text
pom.xml
src/test/java/com/priya/api/ConduitApiTest.java
```

## Maven Configuration

The `pom.xml` file defines the project dependencies and test runner configuration.

Key dependencies:

- `rest-assured`: Used to send HTTP requests and validate API responses.
- `testng`: Used as the testing framework to organize and run test methods.
- `maven-surefire-plugin`: Used by Maven to discover and execute the TestNG tests.

The project is configured to use Java 21 through:

```xml
<maven.compiler.release>21</maven.compiler.release>
```

## Test Class

The main API test file is:

```text
src/test/java/com/priya/api/ConduitApiTest.java
```

This class contains two API tests for the Conduit API.

## Base URI Setup

```java
private static final String BASE_URI = "https://conduit-api.bondaracademy.com";
```

The `BASE_URI` stores the API host URL in one place.

```java
@BeforeClass
public void setUp() {
    RestAssured.baseURI = BASE_URI;
}
```

The `@BeforeClass` method runs once before the test methods in the class. It sets the default Rest Assured base URI, so each test can call endpoints using relative paths like `/api/tags`.

## GET Request Test

```java
@Test
public void simpleGetRequestReturnsTagsContainingTest()
```

This test sends a GET request to:

```text
/api/tags
```

The test validates that:

- The API returns HTTP status code `200`.
- The response body contains a `tags` list.
- The `tags` list includes the value `Test`.

Rest Assured flow used here:

```java
given()
    .when()
    .get("/api/tags")
    .then()
    .statusCode(200)
    .body("tags", hasItem("Test"));
```

`given()` prepares the request, `when()` sends the request, and `then()` validates the response.

## POST Request Test

```java
@Test
public void simplePostRequestCreatesArticle()
```

This test sends a POST request to:

```text
/api/articles/
```

The test creates a new article using a JSON request body.

```java
String title = "AQCX TEST " + System.currentTimeMillis();
```

The article title includes the current timestamp so that every test run creates a unique title.

The request sets:

- `Content-Type` as JSON.
- An `Authorization` header for authenticated article creation.
- A JSON body containing article details.

The test validates that:

- The API returns HTTP status code `201`.
- The returned article title matches the generated title.
- The returned article slug is not null.

## Assertions

The test class uses Hamcrest matchers for response validation:

- `hasItem("Test")`: Checks that a list contains a specific value.
- `equalTo(title)`: Checks that the response title exactly matches the generated title.
- `notNullValue()`: Checks that the slug exists in the response.

## Running the Tests

Run the API tests with Maven:

```bash
mvn test
```

After execution, Maven generates test output under the `target/` folder. This folder is ignored by Git because it contains generated build and report files.

## Note About Authorization

The POST test currently uses an authorization token directly in the test code. For a real project, it is better to store tokens in environment variables or a secure configuration file instead of committing them to source control.
