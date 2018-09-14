package steps;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private AppiumDriver appiumDriver;

    public DriverFactory() throws Exception {
        createDriver();
    }

    private void createDriver() throws Exception {
        // Desired Capabilities
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", "Google");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("newCommandTimeout", 120);
        desiredCapabilities.setCapability("avdReadyTimeout", 120000);
        desiredCapabilities.setCapability("app", System.getProperty("user.dir") + "/app/Flipkart.apk");
//        desiredCapabilities.setCapability("app", "/root/app/Flipkart.apk");
        desiredCapabilities.setCapability("clearSystemFiles", true);

        // IP address of docker-machine
        appiumDriver = new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);
//        appiumDriver = new AppiumDriver(new URL("http://192.168.99.100:4723/wd/hub"), desiredCapabilities);
        appiumDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public AppiumDriver getDriver() {
        return appiumDriver;
    }
}
