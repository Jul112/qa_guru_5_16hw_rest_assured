package tests;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.DriverHelper.configureDriver;

public class DemoWebShopTests{

    @BeforeAll
    static void setup() {
        configureDriver();
        RestAssured.baseURI = "http://demowebshop.tricentis.com";
        Configuration.baseUrl = "http://demowebshop.tricentis.com";
    }

    ApiSteps apiSteps = new ApiSteps();
    WebSteps webSteps = new WebSteps();
    String valueOfCookie = apiSteps.newCustomerCookie.getValue();


    @Test
    @DisplayName("Add products to cart and check the quantity from API")
    public void checkAddingToCartFromApi() {
        apiSteps.getQuantityInTheCart();
        apiSteps.addToCart();
        apiSteps.verifyIncreaseOfQuantity();
    }

    @Test
    @DisplayName("Add products to cart from API and check the quantity from UI")
    public void checkAddingToCartFromUI() {
        apiSteps.getQuantityInTheCart();
        apiSteps.addToCart();

        webSteps.setCookie(valueOfCookie);
        webSteps.checkQuantity();
    }
}

//1. Проверить количество товара в корзине по api
// 2.Сделать тест на добавление товара в корзину и проверить количество товара в корзине по апи
//3. Проверить что товар добавлен по UI
//Все тесты реализовать с передачей cookie
