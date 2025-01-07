import io.restassured.response.Response;
import org.example.Food;
import org.example.FoodGenerator;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;

import java.sql.SQLException;

/**
 * Автотесты - вариант 3
 * 1) Написать автотесты на API часть портала - "Товары" и сброс
 * 3) Проверка что в БД - отображаются действия проделанные в API
 * 2) Проверка что в веб части портала - меню "Песочница"->"Товары" - отображаются действия проделанные в API
 */

public class APITest extends BaseTest{
    Food food = FoodGenerator.getRandomFood();
    @org.junit.jupiter.api.Test
    @DisplayName("Сброс и добавление товара через API")
    public void ApiTest() throws SQLException {

        ApiRequest request = RequestFactory.createRequest("POST","http://localhost:8080/",food);
        Response response = request.sendRequest();
        Assert.assertEquals(200,response.getStatusCode());
//        ApiRequest request = RequestFactory.createRequest("GET","http://localhost:8080/api/food",food);
//        Response response = request.sendRequest();
//        Assert.assertEquals(200,response.getStatusCode());
//        Assert.assertTrue(response.getBody().jsonPath().getString("name").contains("Помидор"));
    }
    @org.junit.jupiter.api.Test
    @DisplayName("Проверка что в веб части портала - меню \"Песочница\"->\"Товары\" - отображаются действия проделанные в API")
    public void WebTest() throws InterruptedException, SQLException {
        app.getMainPage()
                .selectPointOfMenu("Песочница")
                .selectSubMenu("Товары")
                .checkOpenSanboxPage()
                .selectTableElement();

    }
    @org.junit.jupiter.api.Test
    @DisplayName("Проверка что в БД - отображаются действия проделанные в API")
    public void BDTest() throws InterruptedException, SQLException {


    }

}

