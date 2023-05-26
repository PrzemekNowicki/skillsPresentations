package pl.testeroprogramowania.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ResultsPage {
    @FindBy(xpath = "//h4[contains(@class, 'list_title')]//b")
    public List<WebElement> hotelList;

    @FindBy(xpath = "//h2[@class='text-center']")
    public WebElement textCenter;

    @FindBy(xpath = "//div[@class='alert alert-danger']")
    public WebElement headingError;

    @FindBy(className = "RTL")
    public WebElement welcomeTextAfterLogin;


    public ResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<String> getHotelNames() {
        return hotelList.stream().map(el -> el.getAttribute("textContent")).collect(Collectors.toList());
    }


}
