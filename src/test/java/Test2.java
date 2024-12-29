import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Test2
{
    @org.junit.jupiter.api.Test
    public void Test1() {
        RestAssured
                .given()
                .log().all()
                .when()
                .contentType(ContentType.JSON)
                .get("http://85.192.34.140:8080/api/easy/carBrands")
                .then()
                .statusCode(200);
    }
}
