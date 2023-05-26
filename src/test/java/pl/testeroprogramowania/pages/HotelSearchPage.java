package pl.testeroprogramowania.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HotelSearchPage {


    @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
    public WebElement searchHotelSpan;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    public WebElement searchHotelInput;

    @FindBy(xpath = "//*[@id=\"select2-drop\"]//span")
    public WebElement hotelSuggestionList;

    @FindBy(name = "checkin")
    public WebElement checkinInput;

    @FindBy(name = "checkout")
    public WebElement checkoutInput;

    @FindBy(id = "travellersInput")
    public WebElement travellersInput;

    @FindBy(id = "adultInput")
    public WebElement adultInput;

    @FindBy(id = "childInput")
    public WebElement childInput;

    @FindBy(id = "adultPlusBtn")
    public WebElement adultPlusButton;

    @FindBy(id = "childPlusBtn")
    public WebElement childPlusButton;

    @FindBy(xpath = "//button[text()=' Search']")
    public WebElement searchButton;

    @FindBy(xpath = "//li[@id='li_myaccount']")
    public List<WebElement> myAccount;

    @FindBy(xpath = "//a[contains(text(), '  Sign Up')]")
    public List<WebElement> signUp;

    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setCity(String cityName) {
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        hotelSuggestionList.click();
    }

    public void setDate(String checkin, String checkout) {
        checkinInput.sendKeys(checkin);
        checkoutInput.sendKeys(checkout);
        searchButton.click();
    }

    public void searchHotelClick() {
        searchButton.click();
    }

    public void setTravellers(String adults, String child) {
        travellersInput.click();
        adultInput.sendKeys(adults);
        childInput.sendKeys(child);
    }

    public void clickMyAccount() {
        myAccount.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
    }

    public void clickSignUp() {
        signUp.get(1).click();
    }

}
