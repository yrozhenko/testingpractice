package com.solvd.testingpractice.pages;

import com.solvd.testingpractice.utils.ConfigUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage {

    @FindBy(css = "#nav-link-accountList-nav-line-1")
    private WebElement navAccountAndListsField;

    @FindBy(xpath = "//a[@rel='nofollow' and text()='Start here.']")
    private WebElement startHereBtnOfBigHoveredModalMenu;

    @FindBy(xpath = "//div[@class='nav-signin-tooltip-footer']/a[text()='Start here.']")
    private WebElement startHereBtnOfSmallAutoModalMenu;

    @FindBy(css = ".nav-signin-tt.nav-flyout")
    private WebElement smallAutoModalMenu;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        super.openPage(ConfigUtil.getProperty("homePageURL"));
    }

    public void hoverMouseAndOpenBigModalMenu() {
        hoverMouseToElement(navAccountAndListsField);
    }

    public CreateAccountPage clickStartHereHoverModalBtn() {
        clickBtnElement(startHereBtnOfBigHoveredModalMenu, 6);
        return new CreateAccountPage(driver);
    }

    public CreateAccountPage clickStartHereAutoModalBtn() {
        clickBtnElement(startHereBtnOfSmallAutoModalMenu, 6);
        return new CreateAccountPage(driver);
    }

    public boolean isSmallAutoModalMenuDisplayed() {
        return smallAutoModalMenu.isDisplayed();
    }
}
