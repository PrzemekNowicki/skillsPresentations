package pl.testeroprogramowania.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoggedUserPage {
    @FindBy(className = "RTL")
    public WebElement welcomeTextAfterLogin;

    public LoggedUserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


}
