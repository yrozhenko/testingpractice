package com.solvd.testingpractice.pages;

import com.solvd.testingpractice.utils.ConfigUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

public abstract class AbstractPage {
    protected final WebDriver driver;
    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractPage.class);

    AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public static WebDriver initDriver() {
        System.setProperty(ConfigUtil.getProperty("driverName"), ConfigUtil.getProperty("driverPath"));
        LOGGER.info("Chrome driver is initialized.");
        return new ChromeDriver();
    }

    protected void openPage(String pageURL) {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(pageURL);
        LOGGER.info("Page is opened.");
    }

    public void closeDriver() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOGGER.error("Exception awaiting termination", e);
        } finally {
            driver.close();
            LOGGER.info("Driver is closed.");
        }
    }

    public void clickOnElement(WebElement element, String elementName, int timeToWait) {
        WebElement waitedElement = new WebDriverWait(driver, Duration.ofSeconds(timeToWait)).until(ExpectedConditions.elementToBeClickable(element));
        waitedElement.click();
        LOGGER.info("\"{}\" element is clicked.", elementName);
    }

    public void hoverMouseToElement(WebElement element) {
        String elementName = element.getText();
        Actions action = new Actions(driver);
        action.moveToElement(element);
        action.perform();
        LOGGER.info("Mouse is hovered to \"{}\".", elementName);
    }

    public boolean isElementDisplayedAndEnabled(WebElement element, String elementName) {
        boolean isDisplayed = element.isDisplayed();
        boolean isEnabled = element.isEnabled();
        LOGGER.info("\"{}\" is displayed: {}, is enabled: {}.",elementName, isDisplayed,  isEnabled);
        return isDisplayed && isEnabled;
    }

    public void sendKeysToElement(WebElement element, String keys, String elementName, int timeToWait) {
        WebElement waitedElement = new WebDriverWait(driver, Duration.ofSeconds(timeToWait)).until(ExpectedConditions.elementToBeClickable(element));
        waitedElement.sendKeys(keys);
        LOGGER.info("Values are set to \"{}\".", elementName);
    }

    public String getAttributeValueFromElement(WebElement element, String elementName, String attributeName) {
        LOGGER.info("Attribute value has been gotten from \"{}\".", elementName);
        return element.getAttribute(attributeName);
    }

    public String getAttributeValueFromWaitedElement(WebElement element, String elementName, String attributeName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeToBeNotEmpty(element, attributeName));
        return getAttributeValueFromElement(element, elementName, attributeName);
    }

}
