package com.solvd.testingpractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class CreateAccountPage extends AbstractPage {
    private final static Logger LOGGER = LoggerFactory.getLogger(CreateAccountPage.class);

    @FindBy(xpath = "//h1")
    private WebElement titleElemOfCAPage;

    @FindBy(css = "[for=ap_customer_name]")
    private WebElement nameFieldLabel;

    @FindBy(css = "#ap_customer_name")
    private WebElement nameInputField;

    @FindBy(css = "[for=ap_email]")
    private WebElement emailFieldLabel;

    @FindBy(css = "#ap_email")
    private WebElement emailInputField;

    @FindBy(css = "[for=ap_password]")
    private WebElement passwordFieldLabel;

    @FindBy(css = "#ap_password")
    private WebElement passwordInputField;

    @FindBy(css = "[for=ap_password_check]")
    private WebElement reEnterPasswordFieldLabel;

    @FindBy(css = "#ap_password_check")
    private WebElement reEnterPasswordInputField;

    @FindBy(css = "#continue")
    private WebElement continueBtn;

    @FindBy(xpath = "//a[text()='Conditions of Use']")
    private WebElement conditionsOfUseLink;

    @FindBy(xpath = "//a[text()='Privacy Notice']")
    private WebElement privacyNoticeLink;

    @FindBy(xpath = "//div[contains(text(),'Enter your name')]")
    private WebElement enterNameNotification;

    @FindBy(xpath = "//div[@id='auth-email-missing-alert']//div[contains (text(),'Enter your email')]")
    private WebElement enterEmailNotification;

    @FindBy(xpath = "//div[contains (text(),'Enter your password')]")
    private WebElement enterPasswordNotification;

    @FindAll({@FindBy (xpath = "//div[contains (text(),'Wrong or Invalid email address or mobile phone number')]"), @FindBy(xpath = "//div[contains (text(),' Enter a valid email address')]")})
    private List<WebElement> invalidEmailAddressNotification;

    @FindAll({@FindBy (xpath = "//div[contains (text(), 'Passwords must be at least 6 characters.')]"), @FindBy (xpath = "//div[contains ( text(), 'Minimum 6 characters required')]")})
    private List<WebElement> minLengthOfInputPassword;

    @FindBy(xpath = "//div[contains (text(), 'Type your password again')]")
    private WebElement reEnterPasswordNotification;

    @FindBy(xpath = "//div[contains (text(), 'Passwords must match')]")
    private WebElement reEnterPasswordNotMatchedNotification;

    public CreateAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public boolean verifyNameLabelDisplayed() {
        return nameFieldLabel.isDisplayed();
    }

    public boolean verifyNameInputFieldDisplayedAndEnabled() {
        boolean isDisplayed = nameInputField.isDisplayed();
        boolean isEnabled = nameInputField.isEnabled();

        return isDisplayed && isEnabled;
    }

    public boolean verifyEmailLabelDisplayed() {
        return emailFieldLabel.isDisplayed();
    }

    public boolean verifyEmailInputFieldDisplayedAndEnabled() {
        boolean isDisplayed = emailInputField.isDisplayed();
        boolean isEnabled = emailInputField.isEnabled();

        return isDisplayed && isEnabled;
    }

    public boolean verifyPasswordLabelDisplayed() {
        return passwordFieldLabel.isDisplayed();
    }

    public boolean verifyPasswordInputFieldDisplayedAndEnabled() {
        boolean isDisplayed = passwordInputField.isDisplayed();
        boolean isEnabled = passwordInputField.isEnabled();

        return isDisplayed && isEnabled;
    }

    public boolean verifyReEnterLabelDisplayed() {
        return reEnterPasswordFieldLabel.isDisplayed();
    }

    public boolean verifyReEnterInputFieldDisplayedAndEnabled() {
        boolean isDisplayed = reEnterPasswordInputField.isDisplayed();
        boolean isEnabled = reEnterPasswordInputField.isEnabled();

        return isDisplayed && isEnabled;
    }

    public boolean verifyContinueBtnDisplayedAndEnabled() {
        boolean isDisplayed = continueBtn.isDisplayed();
        boolean isEnabled = continueBtn.isEnabled();

        return isDisplayed && isEnabled;
    }

    public boolean verifyConditionsOfUseLinkDisplayedAndEnabled() {
        boolean isDisplayed = conditionsOfUseLink.isDisplayed();
        boolean isEnabled = conditionsOfUseLink.isEnabled();

        return isDisplayed && isEnabled;
    }

    public boolean verifyPrivacyNoticeLinkDisplayedAndEnabled() {
        boolean isDisplayed = privacyNoticeLink.isDisplayed();
        boolean isEnabled = privacyNoticeLink.isEnabled();

        return isDisplayed && isEnabled;
    }

    public NewAccountVerificationPage clickContinueBtn() {
        continueBtn.click();
        LOGGER.info("Continue btn is clicked.");

        return new NewAccountVerificationPage(driver);
    }

    public boolean verifyTitleElementDisplayed() {
        return titleElemOfCAPage.isDisplayed();
    }

    public boolean verifyEnterNameNotificationDisplayed() {
        return enterNameNotification.isDisplayed();
    }

    public boolean verifyEnterEmailNotificationDisplayed() {
        return enterEmailNotification.isDisplayed();
    }

    public boolean verifyEnterPasswordNotificationDisplayed() {
        return enterPasswordNotification.isDisplayed();
    }

    public void setKeysToNameField(String keys) {
        nameInputField.sendKeys(keys);
        LOGGER.info("Values are set to input Name field.");
    }

    public void setKeysToEmailField(String keys) {
        emailInputField.sendKeys(keys);
        LOGGER.info("Values are set to input Email field.");
    }

    public void setKeysToPasswordField(String keys) {
        passwordInputField.sendKeys(keys);
        LOGGER.info("Values are set to input Password field.");
    }

    public void setKeysToReEnterField(String keys) {
        reEnterPasswordInputField.sendKeys(keys);
        LOGGER.info("Values are set to input Re-enter password field.");
    }

    public void clearNameField() {
        nameInputField.clear();
        LOGGER.info("The field is cleared and empty.");
    }

    public void clearEmailField() {
        emailInputField.clear();
        LOGGER.info("The field is cleared and empty.");
    }

    public String getTextValueFromNameField() {
        LOGGER.info("Text values has been gotten from Name field.");
        return nameInputField.getAttribute("value");
    }

    public boolean verifyInvalidEmailNotification() {
        return invalidEmailAddressNotification.stream().anyMatch(WebElement::isDisplayed);
    }

    public boolean verifyMinLengthPasswordNotification() {
        return minLengthOfInputPassword.stream().anyMatch(WebElement::isDisplayed);
    }

    public boolean verifyReEnterPasswordNotification() {
        return reEnterPasswordNotification.isDisplayed();
    }

    public boolean verifyReEnterPasswordNotMatchedNotification() {
        return reEnterPasswordNotMatchedNotification.isDisplayed();
    }
}
