package com.solvd.testingpractice.ui;

import com.solvd.testingpractice.customexceptions.TestInterruptedException;
import com.solvd.testingpractice.pages.AbstractPage;
import com.solvd.testingpractice.pages.CreateAccountPage;
import com.solvd.testingpractice.pages.HomePage;
import com.solvd.testingpractice.utils.ConfigUtil;
import com.solvd.testingpractice.utils.iCharsKeeper;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

public class HomePageTesting implements iCharsKeeper {
    private final static Logger LOGGER = LoggerFactory.getLogger(HomePageTesting.class);

    @Test
    public void verifySiteOnHomepage() {
        WebDriver driver = AbstractPage.initDriver();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        String expectedTitle = "Amazon.com. Spend less. Smile more.";
        Assert.assertEquals(driver.getTitle(), expectedTitle, "Current page title verifying.");
        Assert.assertEquals(driver.getCurrentUrl(), ConfigUtil.getProperty("homePageURL"), "Home page URL verifying.");
        homePage.closeDriver();
    }

    @Test
    public void testOpeningCreateAccountPageByBigHoverMenu() {
        WebDriver driver = AbstractPage.initDriver();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.hoverMouseAndOpenBigModalMenu();
        CreateAccountPage createAcPage = homePage.clickStartHereHoverModalBtn();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.amazon.com/ap/register"), "Register page URL verifying.");
        Assert.assertTrue(createAcPage.isHeadlineElemDisplayed(), "Headline element of register page verifying.");
        homePage.closeDriver();
    }

    @Test
    public void testOpeningCreateAccountPageByAutoOpenModalMenu() throws TestInterruptedException {
        WebDriver driver = AbstractPage.initDriver();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        if (homePage.isSmallAutoModalMenuDisplayed()) {
            try {
                CreateAccountPage createAcPage = homePage.clickStartHereAutoModalBtn();
                Assert.assertTrue(driver.getCurrentUrl().contains("https://www.amazon.com/ap/register"), "Register page URL verifying.");
                Assert.assertTrue(createAcPage.isHeadlineElemDisplayed(), "Headline element of register page verifying.");
                createAcPage.closeDriver();
            } catch (org.openqa.selenium.ElementNotInteractableException exception) {
                LOGGER.error("Check small auto-modal menu: ", exception);
            }
        } else {
            LOGGER.error("Small auto-modal menu was not displayed!");
            homePage.closeDriver();
            throw new TestInterruptedException("Small auto-modal menu was not displayed! Test is not complete!");
        }
    }
}
