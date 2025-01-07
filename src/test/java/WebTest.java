import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.example.Food;
import org.example.FoodGenerator;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Автотесты - вариант 1
 * 1) Написать автотесты на веб часть портала - меню "Песочница"->"Товары"
 * 2) Проверка что в БД отображаются действия из Web формы меню "Песочница"->"Товары"
 * 3) Проверка что в API отобрадаются действия из Web формы меню "Песочница"->"Товары"
 */

public class WebTest extends BaseTest {
    Food food = FoodGenerator.getRandomFood();

    @org.junit.jupiter.api.Test
    @DisplayName("Сброс и добавление товара через Web часть")
    public void WebTest() throws InterruptedException, SQLException {
        app.getMainPage()
                .selectPointOfMenu("Песочница")
                .selectSubMenu("Товары")
                .selectTableElement()
                .clickBtnAdd()
                .fillField("Наименование",food.name)
                .fillDropDown(String.valueOf(food.type))
                .fillChechBox(food.exotic)
                .clickSave();
    }
    @org.junit.jupiter.api.Test
    @DisplayName("Проверка что в БД отображаются действия из Web формы меню \"Песочница\"->\"Товары\"")
    public void BDTestAssert() throws InterruptedException, SQLException {
        System.out.printf("Тестовые параметры: %nНазвание:"+ food.name+"%nТип:"+food.type+"%nЭкзотический:"+food.exotic+" %n");
        ResultSet result = BaseTest.DBSelect("SELECT * FROM FOOD");
        Allure.addAttachment("Результат", "application/json", String.valueOf(result));
        Assert.assertTrue(food.name,result.last());
    }
    @org.junit.jupiter.api.Test
    @DisplayName("Проверка что в API отобрадаются действия из Web формы меню \"Песочница\"->\"Товары\"")
    public void ApiTestAssert() throws InterruptedException, SQLException {
        ApiRequest request = RequestFactory.createRequest("GET","http://localhost:8080/api/food",food);
        Response response = request.sendRequest();
        Allure.addAttachment("Результат", "application/json", String.valueOf(response));
        Allure.addAttachment("Результат", "application/json", request.toString());
        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertTrue(response.getBody().jsonPath().getString("name").contains(food.name));

    }


}
