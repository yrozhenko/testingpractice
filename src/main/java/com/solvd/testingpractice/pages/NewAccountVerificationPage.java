package com.solvd.testingpractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewAccountVerificationPage extends AbstractPage {
    private final static Logger LOGGER = LoggerFactory.getLogger(NewAccountVerificationPage.class);

    @FindBy(xpath = "//h1[text() = 'Verify email address']")
    private WebElement verifyEmailAddressTitle;

    @FindBy(xpath = "//span[text() = 'Solve this puzzle to protect your account']")
    private WebElement captchaTitle;

    NewAccountVerificationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public boolean isVerifyEmailAddressTitleDisplayed() {
        return verifyEmailAddressTitle.isDisplayed();
    }

    public boolean isCaptchaTitleDisplayed() {
        return captchaTitle.isDisplayed();
    }
}
