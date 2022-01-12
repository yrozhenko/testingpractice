package com.solvd.testingpractice.webdriver;

import com.solvd.testingpractice.utils.ConfigUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class WebDriverSingleton {
    private static final Logger LOGGER = LogManager.getLogger(WebDriverSingleton.class);
    private static volatile WebDriverSingleton instance;
    private final WebDriver driver;

    private WebDriverSingleton() {
        System.setProperty(ConfigUtil.getProperty("driverName"), ConfigUtil.getProperty("driverPath"));
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
    }

    public static WebDriverSingleton getInstance() {
        if (instance == null) {
            synchronized (WebDriverSingleton.class) {
                if (instance == null) {
                    instance = new WebDriverSingleton();
                }
            }
        }
        return instance;
    }

    public WebDriver getDriver() {
        return instance.driver;
    }

    public void closeDriver() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            LOGGER.error(e.getStackTrace());
        } finally {
            driver.close();
        }
    }
}
