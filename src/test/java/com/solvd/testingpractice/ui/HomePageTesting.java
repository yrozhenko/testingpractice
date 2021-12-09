package com.solvd.testingpractice.ui;

import com.solvd.testingpractice.pages.AbstractPage;
import com.solvd.testingpractice.pages.CreateAccountPage;
import com.solvd.testingpractice.pages.HomePage;
import com.solvd.testingpractice.utils.ConfigUtil;
import com.solvd.testingpractice.utils.iConstantKeeper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class HomePageTesting implements iConstantKeeper {

    @Test
    public void verifySiteOnHomepage() {
        WebDriver driver = AbstractPage.initDriver();
        HomePage hp = new HomePage(driver);
        hp.open();
        String expectedHomepageTitle = "Amazon.com. Spend less. Smile more.";
        Assert.assertEquals(driver.getTitle(), expectedHomepageTitle, "Current page title verifying.");
        Assert.assertEquals(driver.getCurrentUrl(), ConfigUtil.getProperty("homePageURL"), "Home page URL verifying.");
        hp.closeDriver();
    }

    @Test
    public void getCreateAccountPageByBigHoverMenu() {
        WebDriver driver = AbstractPage.initDriver();
        HomePage hp = new HomePage(driver);
        hp.open();
        hp.hoverMouseAndOpenBigModalMenu();
        CreateAccountPage createAcPage = hp.clickStartHereHoverModalBtn();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.amazon.com/ap/register"), "Register page URL verifying.");
        Assert.assertTrue(createAcPage.verifyTitleElementDisplayed(), "Title element of register page verifying.");
        hp.closeDriver();
    }

    @Test
    public void getCreateAccountPageByAutoOpenModalMenu() {
        WebDriver driver = AbstractPage.initDriver();
        HomePage hp = new HomePage(driver);
        hp.open();
        CreateAccountPage createAcPage = hp.clickStartHereAutoModalBtn();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.amazon.com/ap/register"), "Register page URL verifying.");
        Assert.assertTrue(createAcPage.verifyTitleElementDisplayed(), "Title element of register page verifying.");
        hp.closeDriver();
    }
}
