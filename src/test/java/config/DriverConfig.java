package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        //"system:properties",
        "classpath:config/remote_driver.properties"
})
public interface DriverConfig extends Config {

    String webBrowser();

     String webRemoteDriverUrl();

    String webRemoteDriverUser();

    String webRemoteDriverPassword();
}
