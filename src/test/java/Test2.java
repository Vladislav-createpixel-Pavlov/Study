import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.io.File;

public class Test2
{
    @org.junit.jupiter.api.Test
    public void Test1() {
        String response = RestAssured
                .given()
                .log().all()
                .when()
                .contentType(ContentType.JSON)
                .get("http://85.192.34.140:8080/api/easy/carBrands")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .toString();
        Allure.addAttachment("Результат", "text/plain", response);

    }
}
