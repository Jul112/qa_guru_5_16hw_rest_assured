package tests;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiSteps {
    Response response = get("/cart");
    Cookie newCustomerCookie =response.getDetailedCookie("Nop.customer");
    private int startQuantity= getQuantityInTheCart();

    @Step ("Check quantity of products in the cart")
    int getQuantityInTheCart() {
        String quantityS =
                given().filter(new AllureRestAssured())
                        .cookie(newCustomerCookie)
                        .log().uri()
                        .when().get("/cart")
                        .then().log().body().extract().response()
                        .htmlPath().getString("**.find{it.@class=='cart-qty'}");

        return Integer.parseInt(quantityS.replaceAll("[^0-9]", ""));
    }

    @Step ("Add product to cart")
    void addToCart() {
        startQuantity = getQuantityInTheCart();
        given()
                .filter(new AllureRestAssured())
                .cookie(newCustomerCookie)
                .log().uri()
                .when()
                .post("/addproducttocart/catalog/31/1/1")
                .then()
                .log().body()
                .statusCode(200)
                .body("success", is(true))
                .body("message", containsString("The product has been added"));
    }

    @Step ("Checking increase of products quantity after adding an item")
    void verifyIncreaseOfQuantity() {
        assertEquals(getQuantityInTheCart(),(startQuantity+1));
    }
}
