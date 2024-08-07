package com.eurotech.pages.plain;

import com.eurotech.context.TestContext;
import com.eurotech.pages.BasePage;
import com.eurotech.pages.InternalPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

import java.util.List;

public class PlainMainPage extends InternalPage {
    public PlainMainPage(TestContext context) {
        super(context);
    }

    @CacheLookup // Opasno!
    @FindBy(css = ".btn_inventory")
    public List<WebElement> addToCartButtons;

    @FindBy(how = How.XPATH, using = "//input")
    public WebElement input;

    @FindBys({@FindBy(css = "#id"), @FindBy(xpath = "//input[@id='ad']")}) // AND
    public WebElement elementBys;

    @FindAll({@FindBy(css = "#id"), @FindBy(xpath = "//input[@id='ad']")}) // OR
    public List<WebElement> elementAll;
}
