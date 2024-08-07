package com.eurotech.steps;

import com.eurotech.context.NewUserInfo;
import com.eurotech.context.TestContext;
import com.eurotech.pages.LoginPage;
import com.eurotech.pages.MainPage;
import com.eurotech.pages.UserCreationPage;
import com.eurotech.utils.ConfigurationReader;
import com.eurotech.utils.DriverFactory;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExampleSteps {
    TestContext context;
    Scenario scenario;

    @Before
    public void beforeEach(Scenario scenario) {
        context = new TestContext();
        context.driver = DriverFactory.get();
        context.wait = new WebDriverWait(context.driver, Duration.ofSeconds(Long.parseLong(ConfigurationReader.get("timeout"))));
        context.actions = new Actions(context.driver);
        context.js = (JavascriptExecutor) context.driver;

        this.scenario = scenario;
    }

    @After
    public void afterEach(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) context.driver;

            byte[] src = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(src, "image/png", "screenshot");
        }
        if (context.driver != null) {
            context.driver.quit();
        }
    }

    @BeforeStep
    public void beforeStep()
    {
        scenario.log("Current URL:" + context.driver.getCurrentUrl());
    }

    @Given("user enter login page")
    public void userEnterLoginPage() {
        context.driver.get(ConfigurationReader.get("base_url"));
        scenario.log("Entered login page");
    }
    
    @When("user enters valid credentials")
    public void userEntersValidCredentials(){
        LoginPage loginPage = new LoginPage(context);
        loginPage.loginAsStandardUser();
    }

    @And("clicks login button")
    public void clicksLoginButton() {
        LoginPage loginPage = new LoginPage(context);
        loginPage.loginButton.click();
    }

    @Then("main page opens")
    public void mainPageOpens() {
        MainPage mainPage = new MainPage(context);
        assertTrue(mainPage.firstItem.isDisplayed(), "The first item should displayed");
    }

    @And("the page has text in the footer {string}")
    public void thePageHasTextInTheFooter(String expectedText) {
        MainPage mainPage = new MainPage(context);
        assertTrue(mainPage.footer.getText().contains(expectedText));
    }

    @When("user enters login {string} and password {string}")
    public void userEntersLoginLoginAndPasswordPassword(String login, String password) {
        LoginPage loginPage = new LoginPage(context);
        loginPage.loginAs(login, password);
    }

    @Then("error message contains text {string}")
    public void errorMessageContainsTextErrorMessage(String expectedText) {
        LoginPage loginPage = new LoginPage(context);
        String actualText = loginPage.loginMessageContainer.getText();

        assertTrue(actualText.contains(expectedText), "Error message mismatch.");
    }

    @When("user adds all items to the cart")
    public void userAddsAllItemsToTheCart() {
        MainPage mainPage = new MainPage(context);
        mainPage.addToCartButtons.forEach(WebElement::click);
    }

    @Then("amount of products in the cart is '{int}'")
    public void amountOfProductsInTheCartIs(int expectedAmount) {
        MainPage mainPage = new MainPage(context);
        int actualAmount = Integer.parseInt(mainPage.shoppingCartLink.getText());
        Assertions.assertEquals(expectedAmount, actualAmount);
    }

    @Given("user enter create user page")
    public void userEnterCreateUserPage() {
        context.driver.get("https://demo.wpeverest.com/user-registration/online-event-registration-form/");
    }

    @When("create new user with parameters:")
    public void createNewUserWithParameters(Map<String, String> table) throws InterruptedException {
        UserCreationPage userCreationPage = new UserCreationPage(context);
        userCreationPage.firstNameInput.sendKeys(table.get("firstName"));
        userCreationPage.lastNameInput.sendKeys(table.get("lastName"));
        userCreationPage.emailInput.sendKeys(table.get("email"));
        userCreationPage.passwordInput.sendKeys(table.get("password"));
        userCreationPage.phoneInput.sendKeys(table.get("phone"));
        userCreationPage.genderSelection.forEach(checkbox -> {
            if(checkbox.getAttribute("value").contains(table.get("gender")))
                checkbox.click();
        });
        userCreationPage.accommodationInput.sendKeys(table.get("accommodation"));
        if(Boolean.parseBoolean(table.get("volunteer"))) {
            userCreationPage.volunteerSelection.get(0).click();
        } else {
            userCreationPage.volunteerSelection.get(1).click();
        }
        userCreationPage.headerFromSelection.forEach(checkbox -> {
            if(checkbox.getAttribute("value").contains(table.get("heardBy")))
                checkbox.click();
        });
        userCreationPage.extraGuestNameInput.sendKeys(table.get("extraGuest"));
        Thread.sleep(4500);
        userCreationPage.submitButton.submit();
        userCreationPage.submitButton.click();
        Thread.sleep(4500);
    }

    @Then("the user creation page has text {string}")
    public void theUserCreationPageHasTextUserSuccessfullyRegistered(String expectedMessage) {
        UserCreationPage userCreationPage = new UserCreationPage(context);
        String actualMessage = userCreationPage.successMessage.getText();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @When("create new user via object with parameters:")
    public void createNewUserViaObjectWithParameters(Map<String, String> table) throws InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        NewUserInfo newUserInfo = objectMapper.convertValue(table, NewUserInfo.class);

        UserCreationPage userCreationPage = new UserCreationPage(context);
        userCreationPage.createNewUser(newUserInfo);
    }
}
