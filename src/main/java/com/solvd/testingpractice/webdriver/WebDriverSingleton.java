package com.solvd.testingpractice.webdriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class WebDriverSingleton {
//    private static WebDriver driver;
//
//    private WebDriverSingleton() {}
//
//    public static WebDriver getInstance() {
//        if (driver == null) {
//            driver = new ChromeDriver();
//        }
//        return driver;
//    }

    private static final Logger LOGGER = LogManager.getLogger(WebDriverSingleton.class);
    private static volatile WebDriverSingleton instance;
    private final WebDriver driver;

    private WebDriverSingleton() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
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
