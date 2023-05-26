package pl.testeroprogramowania.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SignUpPage {
    @FindBy(name = "firstname")
    public WebElement firstNameField;

    @FindBy(name = "lastname")
    public WebElement lastNameField;

    @FindBy(name = "phone")
    public WebElement phoneField;

    @FindBy(name = "password")
    public WebElement passwordField;

    @FindBy(name = "confirmpassword")
    public WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@class]")
    public List<WebElement> signUpButton;

    @FindBy(name = "email")
    public WebElement emailField;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void completeFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void completeLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void completePhone(String phone) {
        phoneField.sendKeys(phone);
    }

    public void completePassword(String password) {
        passwordField.sendKeys(password);
    }

    public void completeConfirmPassword(String confirmPassword) {
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void clickSignUpbutton() {
        signUpButton.get(2).click();
    }

    public void completeRandomEmail() {
        int randomNumber = (int) (Math.random() * 1000);
        String randomEmail = "testNumber" + randomNumber + "@testy.com";
        emailField.sendKeys(randomEmail);
    }

    public void completeEmail(String email) {
        emailField.sendKeys(email);
    }


}

