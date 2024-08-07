package com.eurotech.actors;

import com.eurotech.context.TestContext;
import com.eurotech.pages.plain.PlainLoginPage;
import com.eurotech.utils.ConfigurationReader;

public class LoginActor {

    TestContext context;
    PlainLoginPage plp;

    public LoginActor(TestContext context){
        this.context = context;
        plp = new PlainLoginPage(context);
    }

    private void login(String username, String password) {
        plp.usernameInput.sendKeys(username);
        plp.passwordInput.sendKeys(password);
        plp.loginButton.click();
    }

    public MainPageActor loginAsStandardUser() {
        return loginAs(ConfigurationReader.get("standard_login"), ConfigurationReader.get("password"));
    }

    public MainPageActor loginAs(String username, String password) {
        login(username, password);
        return new MainPageActor(context);
    }

    public String incorrectLoginAs(String username, String password) {
        login(username, password);
        return plp.loginMessageContainer.getText();
    }
}
