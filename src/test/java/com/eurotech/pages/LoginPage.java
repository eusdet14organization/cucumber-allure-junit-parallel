package com.eurotech.pages;

import com.eurotech.context.TestContext;
import com.eurotech.utils.ConfigurationReader;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(TestContext context) {
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

    @Step("Вход в систему как стандартный пользователь")
    public MainPage loginAsStandardUser(){
       return loginAs(ConfigurationReader.get("standard_login"), ConfigurationReader.get("password"));
    }

    public MainPage loginAs(String name, String password){
        login(name, password);
        return new MainPage(context);
    }

    public String incorrectLoginAs(String username, String password){
        login(username, password);
        return loginMessageContainer.getText();
    }

    private void login(String login, String password) {
        usernameInput.sendKeys(login);
        passwordInput.sendKeys(password);
        loginButton.click();
    }
}
