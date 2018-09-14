package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class LoginPage extends BasePage {

    private AppiumDriver appiumDriver;

    @FindBy(id = "btn_mlogin")
    private WebElement exisistingUserSignIn_Btn;

    @FindBy(id = "com.google.android.gms:id/cancel")
    private List<WebElement> noneOfTheAbove_Txt;

    @FindBy(id = "mobileNo")
    private WebElement mobileNumber_Txt;

    @FindBy(id = "et_password")
    private WebElement password_Txt;

    @FindBy(id = "com.flipkart.android:id/btn_mlogin")
    private WebElement signIn_Btn;

    @FindBy(id = "com.flipkart.android:id/pageLevelError")
    private WebElement errorMessage;

    public LoginPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
    }

    public void enterLoginDetails() {
        clickOn(exisistingUserSignIn_Btn);
        clickOn(mobileNumber_Txt);
        checkAnyExisistingEmailsOrPresentOrNot();
        enterText(mobileNumber_Txt, "12345");
        enterText(password_Txt, "password");
        clickOn(signIn_Btn);
    }

    private void checkAnyExisistingEmailsOrPresentOrNot() {
        if (isDisplayed(noneOfTheAbove_Txt))
            clickOn(noneOfTheAbove_Txt.get(0));
    }

    public void InvalidCredentialsMessage() {
        String message = getTextFromApp(errorMessage);
        Assert.assertEquals(message, "Please enter valid Email ID/Mobile Number");
    }
}