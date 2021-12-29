package com.solvd.testingpractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;


public class SearchingProductsPage extends AbstractPage {
    private final static Logger LOGGER = LoggerFactory.getLogger(SearchingProductsPage.class);

    @FindAll(@FindBy(xpath = "//div[@data-component-type='s-search-result']//span[@class='a-size-medium a-color-base a-text-normal']"))
    public List<WebElement> resultProductsOfSearching;

    @FindBy(xpath = "//div[@data-a-expander-name='sf-brandsRefinements']/a")
    public WebElement brandFilterMenuWithBtns;

    @FindBy(xpath = "//span[text()='MSI']")
    public WebElement brandMsiBtn;

    @FindBy(css = "#brandsRefinements")
    public WebElement brandFilterMenuWithChbox;

    @FindBy(xpath = "//span[text()='Brand']")
    public WebElement labelOfbrandFilterMenuWithChbox;

    @FindBy(xpath = "(//span[text()='See more'])[1]")
    public WebElement brandFilterMenuWithChboxExpander;

    @FindBy(xpath = "//span[text()='Alienware']")
    public WebElement brandAlienwareChboxLink;

    @FindBy(xpath = "//li[@id='p_89/Alienware']//div//input")
    public WebElement brandAlienwareChbox;


    public SearchingProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isResultProductsContainKeysInName(String text) {
        String[] words = text.split(" ");
        int matchedCount = 0;
        for (WebElement element : resultProductsOfSearching) {
            boolean isOnesMatched = false;
            String currentElementText = element.getText().toLowerCase(Locale.ROOT);
            String currentElementAttributeValue = element.getAttribute("innerText").toLowerCase(Locale.ROOT);
            for (String word : words) {
                boolean isElementTextContains = currentElementText.contains(word.toLowerCase(Locale.ROOT));
                boolean isElementAttributeValueContains = currentElementAttributeValue.contains(word.toLowerCase(Locale.ROOT));
                if (isElementTextContains && isElementAttributeValueContains) {
                    isOnesMatched = true;
                }
            }
            if (isOnesMatched) {
                ++matchedCount;
            }
        }
        int total = resultProductsOfSearching.size();
        int percent = (matchedCount * 100)/ total;
        return percent >= 60;
    }

    public boolean isBrandFilterMenuWithBtnsPresent() {
        return isElementDisplayedAndEnabled(brandFilterMenuWithBtns, "Brand filter menu with brand btns");
    }

    public boolean isBrandFilterMenuWithBtnsOpen() {
        return brandFilterMenuWithBtns.getAttribute("ariaExpanded").equals("true");
    }

    public void clickOnBrandFilterMenuWithBtns() {
        clickOnElement(brandFilterMenuWithBtns, "Brand filter menu with btns", 5);
    }

    public boolean isBrandMsiBtnPresent() {
        return isElementDisplayedAndEnabled(brandMsiBtn, "MSI brand btn");
    }

    public void clickOnBrandMsiBtn() {
        clickOnElement(brandMsiBtn, "MSI brand btn", 10);
    }

    public String getMsiBrandBtn() {
        return brandMsiBtn.getText();
    }

    public boolean isBrandFilterMenuWithCheckboxPresent() {
        return isElementDisplayedAndEnabled(brandFilterMenuWithChbox, "Brand filter menu with checkbox");
    }

    public boolean isLabelOfBrandFilterMenuWithCheckboxDisplayed() {
        boolean isDisplayed = false;
        try {
            return labelOfbrandFilterMenuWithChbox.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            LOGGER.warn("Label of brand filter menu with checkbox is not present: " + e);
        }
        return isDisplayed;
    }

    public boolean isExpanderOfBrandFilterMenuWithChboxPresent() {
        return isElementDisplayedAndEnabled(brandFilterMenuWithChboxExpander, "Expander of brand filter menu");
    }

    public void clickOnExpander() {
        clickOnElement(brandFilterMenuWithChboxExpander, "Brand filter menu (with checkbox) expander", 5);
    }

    public boolean isBrandAlienwareChboxLinkPresent() {
        return isElementDisplayedAndEnabled(brandAlienwareChboxLink, "Alienware brand checkbox");
    }

    public void clickOnAlienwareBrandLinkChbox() {
        clickOnElement(brandAlienwareChboxLink, "Alienware brand checkbox", 10);
    }

    public String getAlienwareBrandChboxAttributeValue() {
        return getAttributeValueFromWaitedElement(brandAlienwareChbox, "Alienware brand checkbox", "checked");
    }

    public String getAlienwareBrandChboxLinkTextValue() {
        return brandAlienwareChboxLink.getText();
    }
}
