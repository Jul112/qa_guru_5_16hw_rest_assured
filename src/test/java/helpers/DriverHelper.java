package helpers;

import config.DriverConfig;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class DriverHelper {

   private static DriverConfig config = ConfigFactory.newInstance().create(DriverConfig.class, System.getProperties());

   /* public static boolean isRemoteWebDriver() {
        return !config.webRemoteDriverUrl().equals("");
    }*/

    public static void configureDriver() {
        addListener("AllureSelenide", new AllureSelenide());

        Configuration.browser = config.webBrowser();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (config.webRemoteDriverUrl()!=null) {
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.remote = config.webRemoteDriverUrl();
        }

        Configuration.browserCapabilities = capabilities;
    }
}
