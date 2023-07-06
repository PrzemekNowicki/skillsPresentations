package pl.tourist.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

//MainPage
public class EventSearchPage {



    @FindBy (id = "hotelKey")
    public WebElement eventNameField;

    @FindBy(name = "destination")
    public WebElement destinationCountryList;

    @FindBy(name = "mw")
    public WebElement departureFromList;

    @FindBy(id = "sykoni_adults")
    public WebElement numberOfAdultsList;

    @FindBy(id = "i2")
    public WebElement numberOfChildList;

    @FindBy(id = "searchbutton")
    public WebElement searchOfferButton;

    private WebDriver driver;
    public EventSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver=driver;
    }


    public EventSearchPage completeEventNameField(String eventName) {
        if (eventNameField.isDisplayed()) {
            eventNameField.sendKeys(eventName);
        }
        return this;
    }

    public EventSearchPage clickDestinationCountryList() {
        destinationCountryList.click();
        return this;
    }

    public EventSearchPage selectCountryDestination(String destinationCountry) {
        Select destinations = new Select(destinationCountryList);
        destinations.selectByVisibleText(destinationCountry);

        List<WebElement> possibleDestinationCountriesList =  destinations.getOptions();
        for (WebElement destinationsList : possibleDestinationCountriesList) {
            System.out.println("Możliwy do wybrania kraj docelowy:" +" "+ destinationsList.getText());
        }

        return this;
    }

    public EventSearchPage selectDepartureCity(String departureCity) {
        Select departureCities = new Select(departureFromList);
        departureCities.selectByVisibleText(departureCity);

        List<WebElement> possibleDepartureCitiesList =  departureCities.getOptions();
        for (WebElement citiesList : possibleDepartureCitiesList) {
            System.out.println("Możliwe do wybrania miasto odjazdu:" +" "+ citiesList.getText());
        }

        return this;
    }

    public EventSearchPage selectNumberOfAdults(String adults) {
        Select numberOfAdults = new Select(numberOfAdultsList);
        numberOfAdults.selectByVisibleText(adults);

        List<WebElement> adultsNumberList =  numberOfAdults.getOptions();
        for (WebElement adultsNumber : adultsNumberList) {
            System.out.println("Możliwa do wybrania liczba dorosłych uczestników:" +" "+ adultsNumber.getText());
        }

        return this;
    }


    public EventSearchPage selectNumberOfChild(String child) {
        Select numberOfChild = new Select(numberOfChildList);
        numberOfChild.selectByVisibleText(child);

        List<WebElement> childNumberList =  numberOfChild.getOptions();
        for (WebElement childNumber : childNumberList) {
            System.out.println("Możliwe do wybrania liczba dzieci:" +" "+ childNumber.getText());
        }

        return this;

    }

    public EventSearchPage clickSearchOfferButton(){
        searchOfferButton.click();
        return this;
    }








}
