package pl.testeroprogramowania.testData;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pl.testeroprogramowania.pages.SignUpPage;

public class User {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;

    private WebDriver driver;
    public User(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver=driver;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String completeRandomEmail() {
        int randomNumber = (int) (Math.random() * 1000);
        String randomEmail = "testNumber" + randomNumber + "@testy.com";
        return randomEmail;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }


}
