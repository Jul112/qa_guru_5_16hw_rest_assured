package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/remote_driver.properties")
public interface DriverConfig extends Config {

    String webBrowser();

     String webRemoteDriverUrl();
}
