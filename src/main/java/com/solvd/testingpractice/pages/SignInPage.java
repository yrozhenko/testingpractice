package com.solvd.testingpractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends AbstractPage {

    @FindBy(css = "#createAccountSubmit")
    private WebElement createNewAccountBtn;

    public SignInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CreateAccountPage clickCreateNewAccountBtn() {
        clickOnElement(createNewAccountBtn, "Create new account btn", 10);
        return new CreateAccountPage(driver);
    }
}
