import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresTests {

    @Test
    @DisplayName("Проверить успешное создание пользователя")
    void successfulCreateUserTest() {
        String loginData = "{\"name\": \"natalya\", \"job\": \"qa\"}";
        given()
                .body(loginData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("natalya"))
                .body("job", is("qa"));
    }

    @Test
    @DisplayName("Проверить успешное получение пользователя")
    void successfulGetUserTest() {
        given()
                .contentType(JSON)
                .log().uri()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"));
    }

    @Test
    @DisplayName("Проверить успешное обновление пользователя")
    void successfulUpdateUserTest() {
        String loginData = "{\"name\": \"natalya\", \"job\": \"aqa\"}";
        given()
                .body(loginData)
                .contentType(JSON)
                .log().uri()
                .when()
                .put("https://reqres.in/api/users/95")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("natalya"))
                .body("job", is("aqa"));
    }

    @Test
    @DisplayName("Проверить успешное удаление пользователя")
    void successfulDeleteUserTest() {
        given()
                .contentType(JSON)
                .log().uri()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    @Test
    @DisplayName("Проверить, что для несуществующего пользователя возвращается код 404")
    void unsuccessfulUserNotFoundTest() {
        given()
                .contentType(JSON)
                .log().uri()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

}
