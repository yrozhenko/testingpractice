package com.solvd.testingpractice.pages;

import com.solvd.testingpractice.utils.ConfigUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage {

    @FindBy(css = "#nav-link-accountList-nav-line-1")
    private WebElement navAccountAndListsField;

    @FindBy(css = "#nav-link-accountList")
    private WebElement navLinkAccountList;

    @FindBy(xpath = "//a[@rel='nofollow' and text()='Start here.']")
    private WebElement startHereBtnOfBigHoveredModalMenu;

    @FindBy(xpath = "//div[@class='nav-signin-tooltip-footer']/a[text()='Start here.']")
    private WebElement startHereBtnOfSmallAutoModalMenu;

    @FindBy(css = ".nav-signin-tt.nav-flyout")
    private WebElement smallAutoModalMenu;

    @FindBy(css = "[aria-label='Search']")
    private WebElement topProductSearchingField;

    @FindBy(css = "#nav-search-submit-button")
    private WebElement searchSubmitBtn;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        super.openPage(ConfigUtil.getProperty("homePageURL"));
    }

    public void hoverMouseAndOpenBigModalMenu() {
        hoverMouseToElement(navLinkAccountList);
    }

    public SignInPage clickNavigationalLinkToSignInPage() {
        clickOnElement(navLinkAccountList, "Account list link",10);
        return new SignInPage(driver);
    }

    public CreateAccountPage clickStartHereHoverModalBtn() {
        clickOnElement(startHereBtnOfBigHoveredModalMenu, "Start here link",10);
        return new CreateAccountPage(driver);
    }

    public CreateAccountPage clickStartHereAutoModalBtn() {
        clickOnElement(startHereBtnOfSmallAutoModalMenu, "Start here link", 10);
        return new CreateAccountPage(driver);
    }

    public boolean isSmallAutoModalMenuDisplayed() {
        return smallAutoModalMenu.isDisplayed();
    }

    public boolean isProductSearchingFieldPresent() {
        return isElementDisplayedAndEnabled(topProductSearchingField, "Top product searching field");
    }

    public void clickOnProductSearchField() {
        clickOnElement(topProductSearchingField, "Top product searching field", 10);
    }

    public void sendKeysToProductSearchingField(String keys) {
        sendKeysToElement(topProductSearchingField, keys, "Product searching field", 10);
    }

    public String getTextFromProductSearchingField() {
        return getAttributeValueFromElement(topProductSearchingField, "Product searching field", "value");
    }

    public boolean isSearchSubmitBtnPresent() {
        return isElementDisplayedAndEnabled(searchSubmitBtn, "Submit bnt of product searching field");
    }

    public SearchingProductsPage clickOnSearchSubmitBtn() {
        clickOnElement(searchSubmitBtn, "Product searching field", 10);
        return new SearchingProductsPage(driver);
    }

}
