//package com.solvd.testingpractice.webdriver;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.openqa.selenium.WebDriver;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class WebDriverPool {
//    private static volatile WebDriverPool instance;
//    private static final int MAX_POOL_SIZE = 5;
//    private static volatile int driversCounter = 0;
//    private final List<WebDriver> webDriversPool = new ArrayList<>();
//    private final List<WebDriver> driversBuffer = new ArrayList<>(MAX_POOL_SIZE);
//
//    private static final Logger LOGGER = LogManager.getLogger(WebDriverPool.class);
//
//    private WebDriverPool() {
//        try {
//            Class.forName(ConfigUtil.getProperty("driver"));
//            LOGGER.info("Create successfully connection to MySQL!");
//        } catch (Exception e) {
//            LOGGER.error("Connection to MySQL is failed!" + e);
//        }
//    }
//
//    public static WebDriverPool getInstance() {
//        if (instance == null) {
//            synchronized (WebDriverPool.class) {
//                if (instance == null) {
//                    instance = new WebDriverPool();
//                }
//            }
//        }
//        return instance;
//    }
//
//    private WebDriver getDriver() {
//        WebDriver driver;
//        try {
//            driver = DriverManager.getConnection(ConfigUtil.getProperty("url"),
//                    ConfigUtil.getProperty("username"), ConfigUtil.getProperty("password"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return driver;
//    }
//
//    public synchronized Connection retrieve() throws SQLException, InterruptedException, TimeoutException {
//
//        if (driversCounter < MAX_POOL_SIZE) {
//            ++driversCounter;
//            return getDriver();
//        } else {
//            for (int i = 0; i < 5; i++){
//                if (!webDriversPool.isEmpty()) {
//                    return webDriversPool.remove(0);
//                }
//                Thread.sleep(2000);
//            }
//            throw new TimeoutException();
//        }
//    }
//
//    public synchronized void putBack(Connection connection) {
//        webDriversPool.add(connection);
//        LOGGER.debug("Thread_№" + Thread.currentThread().getName() + " finished to use connection");
//    }
//}
