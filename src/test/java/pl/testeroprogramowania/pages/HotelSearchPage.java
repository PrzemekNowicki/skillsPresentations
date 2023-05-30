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

    private WebDriver driver;
    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver=driver;
    }

    public HotelSearchPage setCity(String cityName) {
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        hotelSuggestionList.click();
        return this;
    }

    public HotelSearchPage setDate() {
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();

        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String todayAsString = dateFormat.format(today);
        String tomorrowAsString = dateFormat.format(tomorrow);
        checkinInput.sendKeys(todayAsString);
        checkoutInput.sendKeys(tomorrowAsString);
        searchButton.click();
        return this;
    }

    public ResultsPage searchHotelClick() {
        searchButton.click();
        return new ResultsPage(driver);
    }

//    public setTravellers(int adults, int child) {
//        travellersInput.click();
//        adultInput.sendKeys(adults);
//        childInput.sendKeys(child);
//    }


    public HotelSearchPage clickMyAccount() {
        myAccount.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        return this;
    }

    public SignUpPage clickSignUp() {
        signUp.get(1).click();
        return new SignUpPage(driver);


    }
}
