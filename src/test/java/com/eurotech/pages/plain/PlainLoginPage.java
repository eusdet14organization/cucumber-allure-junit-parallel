package com.eurotech.pages.plain;

import com.eurotech.context.TestContext;
import com.eurotech.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PlainLoginPage extends BasePage {
    public PlainLoginPage(TestContext context) {
        super(context);
    }

    @FindBy(css = ".error-message-container")
    public WebElement loginMessageContainer;

    @FindBy(css = "#user-name")
    public WebElement usernameInput;

    @FindBy(css = "#password")
    public WebElement passwordInput;

    @FindBy(css = "#login-button")
    public WebElement loginButton;
}
