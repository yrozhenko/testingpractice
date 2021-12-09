package com.solvd.testingpractice.pages;

import com.solvd.testingpractice.utils.ConfigUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends AbstractPage {
    private final static Logger LOGGER = LoggerFactory.getLogger(HomePage.class);

    @FindBy(css = "#nav-link-accountList-nav-line-1")
    private WebElement navAccountAndListsField;

    @FindBy(css = "#nav-flyout-accountList")
    private WebElement navHiddenModalMenu;

    @FindBy(xpath = "//a[@rel='nofollow' and text()='Start here.']")
    private WebElement startHereBtnOfBigHoveredModalMenu;

    @FindBy(xpath = "//div[@class='nav-signin-tooltip-footer']/a[text()='Start here.']")
    private WebElement startHereBtnOfSmallAutoModalMenu;


    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        super.openPage(ConfigUtil.getProperty("homePageURL"));
    }

    public void hoverMouseAndOpenBigModalMenu() {
        Actions action = new Actions(driver);
        action.moveToElement(navAccountAndListsField);
        action.moveToElement(navHiddenModalMenu);
        action.build().perform();
        LOGGER.info("Mouse is hovered to Account&Lists modal menu.");
    }

    public CreateAccountPage clickStartHereHoverModalBtn() {
        startHereBtnOfBigHoveredModalMenu.click();
        LOGGER.info("Account&Lists hover modal btn is clicked.");
        return new CreateAccountPage(driver);
    }

    public CreateAccountPage clickStartHereAutoModalBtn() {
        startHereBtnOfSmallAutoModalMenu.click();
        LOGGER.info("Homepage auto-modal btn is clicked.");
        return new CreateAccountPage(driver);
    }
}
