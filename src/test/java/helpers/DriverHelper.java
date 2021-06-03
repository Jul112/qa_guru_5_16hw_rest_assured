package helpers;

import config.DriverConfig;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class DriverHelper {

   private static DriverConfig config = ConfigFactory.newInstance().create(DriverConfig.class, System.getProperties());

    public static String getWebRemoteDriver() {
        // https://%s:%s@selenoid.autotests.cloud/wd/hub/
        return String.format(config.webRemoteDriverUrl(),
                config.webRemoteDriverUser(),
                config.webRemoteDriverPassword());
    }

    public static boolean isRemoteWebDriver() {
        return !config.webRemoteDriverUrl().equals("");
    }

    public static void configureDriver() {
        addListener("AllureSelenide", new AllureSelenide());

        Configuration.browser = config.webBrowser();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (isRemoteWebDriver()) {
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.remote = getWebRemoteDriver();
        }

        Configuration.browserCapabilities = capabilities;
    }
}
