package com.eurotech.pages;

import com.eurotech.context.NewUserInfo;
import com.eurotech.context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class UserCreationPage extends BasePage {
    @FindBy(css = "#first_name")
    public WebElement firstNameInput;

    @FindBy(css = "#last_name")
    public WebElement lastNameInput;

    @FindBy(css = "#user_email")
    public WebElement emailInput;

    @FindBy(css = "#user_pass")
    public WebElement passwordInput;

    @FindBy(xpath = "//input[@data-label='Phone']")
    public WebElement phoneInput;

    @FindBy(xpath = "//*[@data-label='Gender']")
    public List<WebElement> genderSelection;

    @FindBy(xpath = "//*[@maxlength=50]")
    public WebElement accommodationInput;

    @FindBy(xpath = "//label[@for='Yes']/..//input")
    public List<WebElement> volunteerSelection;

    @FindBy(xpath = "//label[@for='Friends or colleague']/..//input")
    public List<WebElement> headerFromSelection;

    @FindBy(xpath = "//*[contains(@data-label,'Please share the name')]")
    public WebElement extraGuestNameInput;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitButton;

    @FindBy(css = ".user-registration-message")
    public WebElement successMessage;

    public UserCreationPage(TestContext context) {
        super(context);
    }

    public void createNewUser(String firstName, String lastName, String email, String password, String phone, String gender, String accommodation, boolean volunteer, String heardBy, String extraGuest) throws InterruptedException {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        phoneInput.sendKeys(phone);
        genderSelection.forEach(checkbox -> {
            if(checkbox.getAttribute("value").contains(gender))
                checkbox.click();
        });
        accommodationInput.sendKeys(accommodation);
        if(volunteer) {
            volunteerSelection.get(0).click();
        } else {
            volunteerSelection.get(1).click();
        }
        headerFromSelection.forEach(checkbox -> {
            if(checkbox.getAttribute("value").contains(heardBy))
                checkbox.click();
        });
        extraGuestNameInput.sendKeys(extraGuest);
        Thread.sleep(4500);
        submitButton.submit();
        submitButton.click();
        Thread.sleep(4500);
    }

    public void createNewUser(NewUserInfo newUserInfo) throws InterruptedException {
        createNewUser(newUserInfo.firstName, newUserInfo.lastName, newUserInfo.email, newUserInfo.password, newUserInfo.phone, newUserInfo.gender, newUserInfo.accommodation, newUserInfo.volunteer, newUserInfo.heardBy, newUserInfo.extraGuest);
    }
}
