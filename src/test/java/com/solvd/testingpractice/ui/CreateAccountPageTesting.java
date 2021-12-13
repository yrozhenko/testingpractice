package com.solvd.testingpractice.ui;

import com.solvd.testingpractice.pages.AbstractPage;
import com.solvd.testingpractice.pages.CreateAccountPage;
import com.solvd.testingpractice.pages.HomePage;
import com.solvd.testingpractice.utils.ConfigUtil;
import com.solvd.testingpractice.utils.UsefulMethods;
import com.solvd.testingpractice.utils.iConstantKeeper;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateAccountPageTesting implements iConstantKeeper {
    private final static Logger LOGGER = LoggerFactory.getLogger(CreateAccountPageTesting.class);


    @Test
    public void verifyElementsOfCreateAccountPage() {
        WebDriver driver = AbstractPage.initDriver();
        HomePage hp = new HomePage(driver);
        hp.open();
        String expectedHomepageTitle = "Amazon.com. Spend less. Smile more.";
        Assert.assertEquals(driver.getTitle(), expectedHomepageTitle, "Home page title verifying.");
        Assert.assertEquals(driver.getCurrentUrl(), ConfigUtil.getProperty("homePageURL"), "Home page URL verifying.");
        CreateAccountPage createAccPage = hp.clickStartHereAutoModalBtn();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.amazon.com/ap/register"), "Register page URL verifying.");
        LOGGER.info("On create account page now.");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(createAccPage.verifyTitleElementDisplayed(), "Title element verifying.");
        softAssert.assertTrue(createAccPage.verifyNameLabelDisplayed(), "Name label of field verifying.");
        softAssert.assertTrue(createAccPage.verifyEmailLabelDisplayed(), "Email label of field verifying.");
        softAssert.assertTrue(createAccPage.verifyPasswordLabelDisplayed(), "Password label of field verifying.");
        softAssert.assertTrue(createAccPage.verifyReEnterLabelDisplayed(), "Re-enter label of field verifying.");
        softAssert.assertTrue(createAccPage.verifyNameInputFieldDisplayedAndEnabled(), "Name input field verifying.");
        softAssert.assertTrue(createAccPage.verifyEmailInputFieldDisplayedAndEnabled(), "Email input field verifying.");
        softAssert.assertTrue(createAccPage.verifyPasswordInputFieldDisplayedAndEnabled(), "Password input field verifying.");
        softAssert.assertTrue(createAccPage.verifyReEnterInputFieldDisplayedAndEnabled(), "Re-enter input field verifying.");
        softAssert.assertTrue(createAccPage.verifyContinueBtnDisplayedAndEnabled(), "Continue/submit btn verifying.");
        softAssert.assertTrue(createAccPage.verifyConditionsOfUseLinkDisplayedAndEnabled(), "\"Conditions of use\" link verifying.");
        softAssert.assertTrue(createAccPage.verifyPrivacyNoticeLinkDisplayedAndEnabled(), "\"Privacy notice\" link verifying.");
        softAssert.assertAll();
        LOGGER.info("All common elements of register page are verified.");
        createAccPage.closeDriver();
    }

    @Test
    public void verifyFillingAndNotificationRequirementsForAllFields() {
        WebDriver driver = AbstractPage.initDriver();
        HomePage hp = new HomePage(driver);
        hp.open();
        String expectedHomepageTitle = "Amazon.com. Spend less. Smile more.";
        Assert.assertEquals(driver.getTitle(), expectedHomepageTitle, "Home page title verifying.");
        Assert.assertEquals(driver.getCurrentUrl(), ConfigUtil.getProperty("homePageURL"), "Home page URL verifying.");
        CreateAccountPage createAccPage = hp.clickStartHereAutoModalBtn();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.amazon.com/ap/register"), "Register page URL verifying.");
        LOGGER.info("On create account page now.");
        createAccPage.clickContinueBtn();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(createAccPage.verifyEnterNameNotificationDisplayed(), "Enter name notification verifying.");
        softAssert.assertTrue(createAccPage.verifyEnterEmailNotificationDisplayed(), "Enter email notification verifying.");
        softAssert.assertTrue(createAccPage.verifyEnterPasswordNotificationDisplayed(), "Enter password notification verifying.");
        softAssert.assertAll();
        LOGGER.info("All notifications for empty fields are verified.");
        createAccPage.closeDriver();
    }

    @Test
    public void validateAcceptableSignsOfYourNameField() {
        WebDriver driver = AbstractPage.initDriver();
        HomePage hp = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();
        hp.open();
        String expectedHomepageTitle = "Amazon.com. Spend less. Smile more.";
        Assert.assertEquals(driver.getTitle(), expectedHomepageTitle, "Home page title verifying.");
        Assert.assertEquals(driver.getCurrentUrl(), ConfigUtil.getProperty("homePageURL"), "Home page URL verifying.");
        CreateAccountPage createAccPage = hp.clickStartHereAutoModalBtn();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.amazon.com/ap/register"), "Register page URL verifying.");
        LOGGER.info("On create account page now.");
        createAccPage.setKeysToNameField(UsefulMethods.getRandomValues(setOfLatinCyrillicAlphanumericChars, 12));
        createAccPage.clickContinueBtn();
        softAssert.assertFalse(createAccPage.verifyEnterNameNotificationDisplayed(), "Enter name notification verifying after set alphanumeric chars.");
        createAccPage.clearNameField();
        createAccPage.setKeysToNameField(UsefulMethods.getRandomValues(setOfSpecChars, setOfSpecChars.length()));
        createAccPage.clickContinueBtn();
        softAssert.assertFalse(createAccPage.verifyEnterNameNotificationDisplayed(), "Enter name notification verifying after set special chars.");
        softAssert.assertAll();
        LOGGER.info("All input key verifications are passed.");
        createAccPage.closeDriver();
    }

    @Test
    public void checkForMaxLengthOfYourNameField() {
        WebDriver driver = AbstractPage.initDriver();
        HomePage hp = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();
        hp.open();
        String expectedHomepageTitle = "Amazon.com. Spend less. Smile more.";
        Assert.assertEquals(driver.getTitle(), expectedHomepageTitle, "Home page title verifying.");
        Assert.assertEquals(driver.getCurrentUrl(), ConfigUtil.getProperty("homePageURL"), "Home page URL verifying.");
        CreateAccountPage createAccPage = hp.clickStartHereAutoModalBtn();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.amazon.com/ap/register"), "Register page URL verifying.");
        LOGGER.info("On create account page now.");
        String expectedRandomCharacters = UsefulMethods.getRandomValues(setOfLatinCyrillicAlphanumericChars, 50);
        LOGGER.info("Created 50 random characters for Name field.");
        createAccPage.setKeysToNameField(expectedRandomCharacters);
        createAccPage.clickContinueBtn();
        String currentText = createAccPage.getTextValueFromNameField();
        softAssert.assertEquals(currentText.length(), expectedRandomCharacters.length(), "Field max capacity checking.");
        createAccPage.clearNameField();
        expectedRandomCharacters = UsefulMethods.getRandomValues(setOfLatinCyrillicAlphanumericChars, 55);
        LOGGER.info("Created 55 random characters to Name field.");
        createAccPage.setKeysToNameField(expectedRandomCharacters);
        createAccPage.clickContinueBtn();
        currentText = createAccPage.getTextValueFromNameField();
        softAssert.assertEquals(currentText.length(), 50, "Field max capacity oversize acceptance checking.");
        LOGGER.info("Oversize (more then 50 characters) acceptance is verified.");
        softAssert.assertAll();
        LOGGER.info("All verifications of Name field's max capacity are passed.");
        createAccPage.closeDriver();
    }

    @Test
    public void checkEmailFieldInputValidation() {
        WebDriver driver = AbstractPage.initDriver();
        HomePage hp = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();
        hp.open();
        String expectedHomepageTitle = "Amazon.com. Spend less. Smile more.";
        Assert.assertEquals(driver.getTitle(), expectedHomepageTitle, "Home page title verifying.");
        Assert.assertEquals(driver.getCurrentUrl(), ConfigUtil.getProperty("homePageURL"), "Home page URL verifying.");
        CreateAccountPage createAccPage = hp.clickStartHereAutoModalBtn();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.amazon.com/ap/register"), "Register page URL verifying.");
        LOGGER.info("On create account page now.");
        createAccPage.setKeysToEmailField("testingtestgmail.com");
        createAccPage.clickContinueBtn();
        softAssert.assertTrue(createAccPage.verifyInvalidEmailNotification(), "Invalid email address verifying.");
        createAccPage.clearEmailField();
        createAccPage.setKeysToEmailField("testingtest@gmailcom");
        createAccPage.clickContinueBtn();
        softAssert.assertTrue(createAccPage.verifyInvalidEmailNotification(), "Invalid email address verifying.");
        softAssert.assertAll();
        LOGGER.info("All input key verifications are passed.");
        createAccPage.closeDriver();
    }

    @Test
    public void checkPasswordFieldMinLength() {
        WebDriver driver = AbstractPage.initDriver();
        HomePage hp = new HomePage(driver);
        hp.open();
        String expectedHomepageTitle = "Amazon.com. Spend less. Smile more.";
        Assert.assertEquals(driver.getTitle(), expectedHomepageTitle, "Home page title verifying.");
        Assert.assertEquals(driver.getCurrentUrl(), ConfigUtil.getProperty("homePageURL"), "Home page URL verifying.");
        CreateAccountPage createAccPage = hp.clickStartHereAutoModalBtn();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.amazon.com/ap/register"), "Register page URL verifying.");
        LOGGER.info("On create account page now.");
        String setOfLatinAlphanumericChars = setOfLatinChars + setOfNumbers;
        createAccPage.setKeysToPasswordField(UsefulMethods.getRandomValues(setOfLatinAlphanumericChars, 5));
        createAccPage.clickContinueBtn();
        Assert.assertTrue(createAccPage.verifyMinLengthPasswordNotification());
        createAccPage.closeDriver();
    }

    @Test
    public void checkReEnterPasswordFieldFillOutProcedure() {
        WebDriver driver = AbstractPage.initDriver();
        HomePage hp = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();
        hp.open();
        String expectedHomepageTitle = "Amazon.com. Spend less. Smile more.";
        Assert.assertEquals(driver.getTitle(), expectedHomepageTitle, "Home page title verifying.");
        Assert.assertEquals(driver.getCurrentUrl(), ConfigUtil.getProperty("homePageURL"), "Home page URL verifying.");
        CreateAccountPage createAccPage = hp.clickStartHereAutoModalBtn();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.amazon.com/ap/register"), "Register page URL verifying.");
        LOGGER.info("On create account page now.");

    }
}
