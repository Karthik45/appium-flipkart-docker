package steps;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.File;

public class StartingSteps extends BaseSteps {

    private AppiumDriverLocalService appiumService;

    @BeforeTest
    public void setUp() throws Exception {
//        appiumService = AppiumDriverLocalService.buildService(
//                new AppiumServiceBuilder()
//                        .usingDriverExecutable(new File("/usr/local/bin/node"))
//                        .withAppiumJS(new File("/usr/local/bin/appium"))
//                        .withIPAddress("0.0.0.0")
//                        .usingPort(4723)
//                        .withArgument(GeneralServerFlag.SESSION_OVERRIDE));
//        appiumService.start();
        driverFactory = new DriverFactory();
    }

    @Test
    public void sampleTest(){
        LoginPage loginPage = new LoginPage(driverFactory.getDriver());
        loginPage.enterLoginDetails();
        loginPage.InvalidCredentialsMessage();
    }

    @AfterTest
    public void teardown() {
        driverFactory.getDriver().quit();
//        appiumService.stop();
    }

}
