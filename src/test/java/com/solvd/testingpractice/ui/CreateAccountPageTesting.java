package com.solvd.testingpractice.ui;

import com.solvd.testingpractice.customexceptions.TestInterruptedException;
import com.solvd.testingpractice.pages.AbstractPage;
import com.solvd.testingpractice.pages.CreateAccountPage;
import com.solvd.testingpractice.pages.HomePage;
import com.solvd.testingpractice.utils.ConfigUtil;
import com.solvd.testingpractice.utils.UsefulMethods;
import com.solvd.testingpractice.utils.iCharsKeeper;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateAccountPageTesting implements iCharsKeeper {
    private final static Logger LOGGER = LoggerFactory.getLogger(CreateAccountPageTesting.class);

    @Test
    public void verifyElementsOfCreateAccountPage() throws TestInterruptedException {
        WebDriver driver = AbstractPage.initDriver();
        HomePage homePage = new HomePage(driver);
        LOGGER.info("__verifyElementsOfCreateAccountPage test__");
        homePage.open();
        String expectedTitle = "Amazon.com. Spend less. Smile more.";
        Assert.assertEquals(driver.getTitle(), expectedTitle, "Home page title verifying.");
        Assert.assertEquals(driver.getCurrentUrl(), ConfigUtil.getProperty("homePageURL"), "Home page URL verifying.");
        if (homePage.isSmallAutoModalMenuDisplayed()) {
            try {
                CreateAccountPage createAccPage = homePage.clickStartHereAutoModalBtn();
                Assert.assertTrue(driver.getCurrentUrl().contains(ConfigUtil.getProperty("registerPageURL")), "Register page URL verifying.");
                LOGGER.info("On create account page now.");
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertTrue(createAccPage.isHeadlineElemDisplayed(), "Headline element verifying.");
                softAssert.assertTrue(createAccPage.isNameFieldLabelDisplayed(), "Name label of field verifying.");
                softAssert.assertTrue(createAccPage.isEmailFieldLabelDisplayed(), "Email label of field verifying.");
                softAssert.assertTrue(createAccPage.isPasswordFieldLabelDisplayed(), "Password label of field verifying.");
                softAssert.assertTrue(createAccPage.isReEnterFieldLabelDisplayed(), "Re-enter label of field verifying.");
                softAssert.assertTrue(createAccPage.isNameInputFieldDisplayedAndEnabled(), "Name input field verifying.");
                softAssert.assertTrue(createAccPage.isEmailInputFieldDisplayedAndEnabled(), "Email input field verifying.");
                softAssert.assertTrue(createAccPage.isPasswordInputFieldDisplayedAndEnabled(), "Password input field verifying.");
                softAssert.assertTrue(createAccPage.isReEnterInputFieldDisplayedAndEnabled(), "Re-enter input field verifying.");
                softAssert.assertTrue(createAccPage.isContinueBtnDisplayedAndEnabled(), "Continue/submit btn verifying.");
                softAssert.assertTrue(createAccPage.isConditionsOfUseLinkDisplayedAndEnabled(), "\"Conditions of use\" link verifying.");
                softAssert.assertTrue(createAccPage.isPrivacyNoticeLinkDisplayedAndEnabled(), "\"Privacy notice\" link verifying.");
                softAssert.assertAll();
                LOGGER.info("All common elements of register page are verified.");
                createAccPage.closeDriver();
            } catch (org.openqa.selenium.ElementNotInteractableException exception) {
                LOGGER.error("Check small auto-modal menu: ", exception);
            }
        } else {
            LOGGER.error("Small auto-modal menu was not displayed!");
            homePage.closeDriver();
            throw new TestInterruptedException("Small auto-modal menu was not displayed! Test is not complete!");
        }
    }

    @Test
    public void verifyFillingAndNotificationRequirementsForAllFields() {
        WebDriver driver = AbstractPage.initDriver();
        HomePage homePage = new HomePage(driver);
        LOGGER.info("__verifyFillingAndNotificationRequirementsForAllFields test__");
        homePage.open();
        String expectedTitle = "Amazon.com. Spend less. Smile more.";
        Assert.assertEquals(driver.getTitle(), expectedTitle, "Home page title verifying.");
        Assert.assertEquals(driver.getCurrentUrl(), ConfigUtil.getProperty("homePageURL"), "Home page URL verifying.");
        CreateAccountPage createAccPage = homePage.clickStartHereAutoModalBtn();
        Assert.assertTrue(driver.getCurrentUrl().contains(ConfigUtil.getProperty("registerPageURL")), "Register page URL verifying.");
        LOGGER.info("On create account page now.");
        createAccPage.clickContinueBtn();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(createAccPage.isEnterNameNotificationDisplayed(), "Enter name notification verifying.");
        softAssert.assertTrue(createAccPage.isEnterEmailNotificationDisplayed(), "Enter email notification verifying.");
        softAssert.assertTrue(createAccPage.isEnterPasswordNotificationDisplayed(), "Enter password notification verifying.");
        softAssert.assertAll();
        LOGGER.info("All notifications for empty fields are verified.");
        createAccPage.closeDriver();
    }

    @Test
    public void validateAcceptableSignsOfYourNameField() {
        WebDriver driver = AbstractPage.initDriver();
        HomePage homePage = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();
        LOGGER.info("__validateAcceptableSignsOfYourNameField test__");
        homePage.open();
        String expectedTitle = "Amazon.com. Spend less. Smile more.";
        Assert.assertEquals(driver.getTitle(), expectedTitle, "Home page title verifying.");
        Assert.assertEquals(driver.getCurrentUrl(), ConfigUtil.getProperty("homePageURL"), "Home page URL verifying.");
        CreateAccountPage createAccPage = homePage.clickStartHereAutoModalBtn();
        Assert.assertTrue(driver.getCurrentUrl().contains(ConfigUtil.getProperty("registerPageURL")), "Register page URL verifying.");
        LOGGER.info("On create account page now.");
        createAccPage.setKeysToNameField(UsefulMethods.getRandomValues(setOfLatinCyrillicAlphanumericChars, 12));
        createAccPage.clickContinueBtn();
        softAssert.assertFalse(createAccPage.isEnterNameNotificationDisplayed(), "Enter name notification verifying after set alphanumeric chars.");
        createAccPage.clearNameField();
        createAccPage.setKeysToNameField(UsefulMethods.getRandomValues(setOfSpecChars, setOfSpecChars.length()));
        createAccPage.clickContinueBtn();
        softAssert.assertFalse(createAccPage.isEnterNameNotificationDisplayed(), "Enter name notification verifying after set special chars.");
        softAssert.assertAll();
        LOGGER.info("All input key verifications are passed.");
        createAccPage.closeDriver();
    }

    @Test
    public void checkForMaxLengthOfYourNameField() {
        WebDriver driver = AbstractPage.initDriver();
        HomePage homePage = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();
        LOGGER.info("__checkForMaxLengthOfYourNameField test__");
        homePage.open();
        String expectedTitle = "Amazon.com. Spend less. Smile more.";
        Assert.assertEquals(driver.getTitle(), expectedTitle, "Home page title verifying.");
        Assert.assertEquals(driver.getCurrentUrl(), ConfigUtil.getProperty("homePageURL"), "Home page URL verifying.");
        CreateAccountPage createAccPage = homePage.clickStartHereAutoModalBtn();
        Assert.assertTrue(driver.getCurrentUrl().contains(ConfigUtil.getProperty("registerPageURL")), "Register page URL verifying.");
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
        HomePage homePage = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();
        LOGGER.info("__checkEmailFieldInputValidation test__");
        homePage.open();
        String expectedTitle = "Amazon.com. Spend less. Smile more.";
        Assert.assertEquals(driver.getTitle(), expectedTitle, "Home page title verifying.");
        Assert.assertEquals(driver.getCurrentUrl(), ConfigUtil.getProperty("homePageURL"), "Home page URL verifying.");
        CreateAccountPage createAccPage = homePage.clickStartHereAutoModalBtn();
        Assert.assertTrue(driver.getCurrentUrl().contains(ConfigUtil.getProperty("registerPageURL")), "Register page URL verifying.");
        LOGGER.info("On create account page now.");
        String testEmailNamingWithoutAtSymbol = "testingtestgmail.com";
        createAccPage.setKeysToEmailField(testEmailNamingWithoutAtSymbol);
        createAccPage.clickContinueBtn();
        softAssert.assertTrue(createAccPage.isInvalidEmailNotificationDisplayed(), "Invalid email address verifying.");
        createAccPage.clearEmailField();
        String testEmailNamingWithoutDotSymbol = "testingtest@gmailcom";
        createAccPage.setKeysToEmailField(testEmailNamingWithoutDotSymbol);
        createAccPage.clickContinueBtn();
        softAssert.assertTrue(createAccPage.isInvalidEmailNotificationDisplayed(), "Invalid email address verifying.");
        softAssert.assertAll();
        LOGGER.info("All input key verifications are passed.");
        createAccPage.closeDriver();
    }

    @Test
    public void checkPasswordFieldMinLength() throws TestInterruptedException {
        WebDriver driver = AbstractPage.initDriver();
        HomePage homePage = new HomePage(driver);
        LOGGER.info("__checkPasswordFieldMinLength test__");
        homePage.open();
        String expectedTitle = "Amazon.com. Spend less. Smile more.";
        Assert.assertEquals(driver.getTitle(), expectedTitle, "Home page title verifying.");
        Assert.assertEquals(driver.getCurrentUrl(), ConfigUtil.getProperty("homePageURL"), "Home page URL verifying.");
        if (homePage.isSmallAutoModalMenuDisplayed()) {
            try {
                CreateAccountPage createAccPage = homePage.clickStartHereAutoModalBtn();
                Assert.assertTrue(driver.getCurrentUrl().contains(ConfigUtil.getProperty("registerPageURL")), "Register page URL verifying.");
                LOGGER.info("On create account page now.");
                String setOfLatinAlphanumericChars = setOfLatinChars + setOfNumbers;
                createAccPage.setKeysToPasswordField(UsefulMethods.getRandomValues(setOfLatinAlphanumericChars, 5));
                createAccPage.clickContinueBtn();
                Assert.assertTrue(createAccPage.isMinLengthPasswordNotificationDisplayed(), "Verifying notification of Password min length.");
                LOGGER.info("Notification is displayed.");
                createAccPage.closeDriver();
            } catch (org.openqa.selenium.ElementNotInteractableException exception) {
                LOGGER.error("Check small auto-modal menu: ", exception);
            }
        } else {
            LOGGER.error("Small auto-modal menu was not displayed!");
            homePage.closeDriver();
            throw new TestInterruptedException("Small auto-modal menu was not displayed! Test is not complete!");
        }
    }

    @Test
    public void checkReEnterPasswordFieldFillOutProcedure() {
        WebDriver driver = AbstractPage.initDriver();
        HomePage homePage = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();
        LOGGER.info("__checkReEnterPasswordFieldFillOutProcedure test__");
        homePage.open();
        String expectedTitle = "Amazon.com. Spend less. Smile more.";
        Assert.assertEquals(driver.getTitle(), expectedTitle, "Home page title verifying.");
        Assert.assertEquals(driver.getCurrentUrl(), ConfigUtil.getProperty("homePageURL"), "Home page URL verifying.");
        CreateAccountPage createAccPage = homePage.clickStartHereAutoModalBtn();
        Assert.assertTrue(driver.getCurrentUrl().contains(ConfigUtil.getProperty("registerPageURL")), "Register page URL verifying.");
        LOGGER.info("On create account page now.");
        String randomPassword = UsefulMethods.getRandomValues(setOfNumbers, 6);
        createAccPage.setKeysToPasswordField(randomPassword);
        createAccPage.clickContinueBtn();
        softAssert.assertTrue(createAccPage.isReEnterPasswordNotificationDisplayed(), "Verifying of notification about empty re-enter password.");
        createAccPage.setKeysToReEnterField(UsefulMethods.getRandomValues(setOfNumbers, 6));
        createAccPage.clickContinueBtn();
        softAssert.assertTrue(createAccPage.isReEnterPasswordNotMatchedNotificationDisplayed(), "Verifying of notification about not matched re-entered password.");
        softAssert.assertAll();
        LOGGER.info("Two notifications of re-entering password is verified.");
        createAccPage.closeDriver();
    }

    @Test
    public void testAccountCreationWithCorrectValues() {
        WebDriver driver = AbstractPage.initDriver();
        HomePage homePage = new HomePage(driver);
        LOGGER.info("__createAccountWithCorrectValues test__");
        homePage.open();
        String expectedTitle = "Amazon.com. Spend less. Smile more.";
        Assert.assertEquals(driver.getTitle(), expectedTitle, "Home page title verifying.");
        Assert.assertEquals(driver.getCurrentUrl(), ConfigUtil.getProperty("homePageURL"), "Home page URL verifying.");
        CreateAccountPage createAccPage = homePage.clickStartHereAutoModalBtn();
        Assert.assertTrue(driver.getCurrentUrl().contains(ConfigUtil.getProperty("registerPageURL")), "Register page URL verifying.");
        LOGGER.info("On create account page now.");
        createAccPage.setKeysToNameField("TestName");
        String randomTestMailAddress = UsefulMethods.getRandomValues(setOfLatinChars, 8) + "test@gmail.com";
        createAccPage.setKeysToEmailField(randomTestMailAddress);
        String randomPassword = UsefulMethods.getRandomValues(setOfNumbers, 8);
        createAccPage.setKeysToPasswordField(randomPassword);
        createAccPage.setKeysToReEnterField(randomPassword);
        createAccPage.clickContinueBtn();
        Assert.assertTrue(driver.getCurrentUrl().contains(ConfigUtil.getProperty("authenticationPageURL")), "Created new account verification page URL checking.");
        expectedTitle = "Authentication required";
        Assert.assertEquals(driver.getTitle(), expectedTitle, "Page title verifying.");
        LOGGER.info("On authentication page now.");
        createAccPage.closeDriver();
    }
}
