package tests;

import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebSteps {

    int startQuantity = getQuantity();

    @Step("Set cookie")
    void setCookie(String value) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("Nop.customer", value));
    }

    @Step("Get quantity in the cart")
    int getQuantity() {
        open(""); //cart-qty
        String q = $(".cart-qty").getOwnText();
        return Integer.parseInt(q.replaceAll("[^0-9]", ""));
    }

    @Step("Compare end quantity and start quantity")
    void checkQuantity() {
        assertEquals(getQuantity(),(startQuantity+1));
    }


}
