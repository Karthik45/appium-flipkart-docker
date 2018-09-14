package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;
import static utils.Properties.waitTime;

public class BasePage {

    private AppiumDriver appiumDriver;

    public BasePage(AppiumDriver driver) {
        this.appiumDriver = driver;
    }

    public void waitForElementVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(appiumDriver, waitTime);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(appiumDriver, waitTime);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBePresent(By by) {
        WebDriverWait wait = new WebDriverWait(appiumDriver, waitTime);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public boolean isElementPresent(By by) {
        return appiumDriver.findElements(by).size() > 0;
    }

    public boolean isElementPresentAndDisplayed(By by) {
        if (appiumDriver.findElements(by).size() > 0)
            return appiumDriver.findElement(by).isDisplayed();
        return false;
    }

    public void waitForElementToDisappear(By locator) {
        WebDriverWait wait = new WebDriverWait(appiumDriver, waitTime);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(appiumDriver, waitTime);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isDisplayed(List<WebElement> elementList) {
        await().atMost(30, SECONDS).until(() -> elementList.size() > 0);
        return elementList.size() > 0;
    }

    public void jsClick(WebElement webElement) {
        ((JavascriptExecutor) appiumDriver).executeScript("arguments[0].clickOn();", webElement);
    }

    public void jsClickCheckbox(WebElement webElement) {
        waitForElementToBeVisible(webElement);
        waitForElementToBeClickable(webElement);
        ((JavascriptExecutor) appiumDriver).executeScript("arguments[0].checked = true;", webElement);
    }

    public void enterText(WebElement webElement, String value) {
        waitForElementToBeVisible(webElement);
        waitForElementToBeClickable(webElement);
        webElement.clear();
        webElement.sendKeys(value);
    }

    public void enterTextWithoutClearing(WebElement webElement, String value) {
        waitForElementToBeVisible(webElement);
        waitForElementToBeClickable(webElement);
        webElement.sendKeys(value);
    }

    public void selectDropDownWithVisibleText(WebElement webElement, String value) {
        waitForElementToBeVisible(webElement);
        waitForElementToBeClickable(webElement);
        await().atMost(20, SECONDS).until(() -> new Select(webElement).getOptions().size() > 0);
        new Select(webElement).selectByVisibleText(value);
    }

    public void selectDropDownWithPosition(WebElement webElement, int i) {
        waitForElementToBeVisible(webElement);
        waitForElementToBeClickable(webElement);
        await().atMost(20, SECONDS).until(() -> new Select(webElement).getOptions().size() > 1);
        new Select(webElement).selectByIndex(i);
    }

    public void clickOn(WebElement webElement) {
        waitForElementToBeVisible(webElement);
        waitForElementToBeClickable(webElement);
        webElement.click();
    }

    public String getTextFromApp(WebElement webElement) {
        waitForElementToBeVisible(webElement);
        return webElement.getText();
    }
}