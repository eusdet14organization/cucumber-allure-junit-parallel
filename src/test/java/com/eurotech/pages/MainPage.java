package com.eurotech.pages;

import com.eurotech.context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPage extends InternalPage {
    public MainPage(TestContext context) {
        super(context);
    }

    @FindBy(css = ".inventory_item_desc")
    public WebElement firstItem;

    @FindBy(css = ".btn_inventory")
    public List<WebElement> addToCartButtons;

    @FindBy(how = How.XPATH, using = "//input")
    public WebElement input;

    @FindBys({@FindBy(css = "#id"), @FindBy(xpath = "//input[@id='ad']")}) // AND
    public WebElement elementBys;

    @FindAll({@FindBy(css = "#id"), @FindBy(xpath = "//input[@id='ad']")}) // OR
    public List<WebElement> elementAll;

    public MainPage clickAllAddToCartButtons(){
        assertTrue(addToCartButtons.get(0).isEnabled(), "Add to cart button at main page wasn't enabled!");
        addToCartButtons.forEach(WebElement::click);
        return this;
    }
}
