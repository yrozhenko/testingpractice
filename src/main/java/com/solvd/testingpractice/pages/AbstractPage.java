package com.solvd.testingpractice.pages;

import com.solvd.testingpractice.utils.ConfigUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get(pageURL);
        LOGGER.info("Page is opened.");
    }

    public void closeDriver() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            LOGGER.error("Exception awaiting termination", e);
        } finally {
            driver.close();
            LOGGER.info("Driver is closed.");
        }
    }
}
