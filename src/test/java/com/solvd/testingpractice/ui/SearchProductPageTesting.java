package com.solvd.testingpractice.ui;

import com.solvd.testingpractice.pages.AbstractPage;
import com.solvd.testingpractice.pages.HomePage;
import com.solvd.testingpractice.pages.SearchingProductsPage;
import com.solvd.testingpractice.utils.ConfigUtil;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchProductPageTesting {
    private final static Logger LOGGER = LoggerFactory.getLogger(HomePageTesting.class);

    @Test
    public void testProductBrandFilter() {
        WebDriver driver = AbstractPage.initDriver();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        Assert.assertTrue(homePage.isProductSearchingFieldPresent(), "Top product searching field presence verifying.");
        homePage.clickOnProductSearchField();
        String keyValuesForProductSearchingField = "monitor";
        homePage.sendKeysToProductSearchingField(keyValuesForProductSearchingField);
        String actualValue = homePage.getTextFromProductSearchingField();
        Assert.assertEquals(actualValue, keyValuesForProductSearchingField, "Checking expected and actual sent keys equal.");
        Assert.assertTrue(homePage.isSearchSubmitBtnPresent(), "Search submit btn verifying.");
        SearchingProductsPage searchingProductsPage = homePage.clickOnSearchSubmitBtn();
        Assert.assertTrue(driver.getCurrentUrl().contains(ConfigUtil.getProperty("searchedProductListURL")), "Searched product list page URL verifying.");
        LOGGER.info("On searched product list page now.");
        Assert.assertTrue(searchingProductsPage.isResultProductsContainKeysInName(keyValuesForProductSearchingField), "Comparing of actual typed search text and product list result.");
        LOGGER.info("Compared the matches between searching test and result product names.");
        String brandName;
        if (searchingProductsPage.isLabelOfBrandFilterMenuWithCheckboxDisplayed()) {
            Assert.assertTrue(searchingProductsPage.isBrandFilterMenuWithCheckboxPresent(), "Checking is brand filter menu with checkbox present.");
            Assert.assertTrue(searchingProductsPage.isExpanderOfBrandFilterMenuWithChboxPresent(), "Checking presence of brand filter menu expander with checkbox.");
            searchingProductsPage.clickOnExpander();
            Assert.assertTrue(searchingProductsPage.isBrandAlienwareChboxLinkPresent(), "Checking Alienware brand checkbox presence.");
            searchingProductsPage.clickOnAlienwareBrandLinkChbox();
            Assert.assertEquals(searchingProductsPage.getAlienwareBrandChboxAttributeValue(), "true", "Checking is Alienware brand checkbox selected.");
            brandName = searchingProductsPage.getAlienwareBrandChboxLinkTextValue();
        } else {
            Assert.assertTrue(searchingProductsPage.isBrandFilterMenuWithBtnsPresent(), "Checking is brand filter menu with btns present.");
            if (!searchingProductsPage.isBrandFilterMenuWithBtnsOpen()) {
                searchingProductsPage.clickOnBrandFilterMenuWithBtns();
            }
            Assert.assertTrue(searchingProductsPage.isBrandMsiBtnPresent(), "Checking is MSI brand btn present.");
            searchingProductsPage.clickOnBrandMsiBtn();
            brandName = searchingProductsPage.getMsiBrandBtn();
        }
        Assert.assertTrue(searchingProductsPage.isResultProductsContainKeysInName(brandName), "Comparing of actual typed search text and product list result.");
        LOGGER.info("Compared the matches between clicked brand name and result product names.");
        searchingProductsPage.closeDriver();
    }
}
