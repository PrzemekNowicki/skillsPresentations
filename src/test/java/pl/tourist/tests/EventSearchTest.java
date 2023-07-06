package pl.tourist.tests;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import pl.tourist.pages.EventSearchPage;

public class EventSearchTest extends BaseTest{
    @Test
    public void searchHotelTest(){

        EventSearchPage eventSearchPage = new EventSearchPage(driver)
                .clickDestinationCountryList()
                .selectCountryDestination("Polska")
                .selectDepartureCity("Warszawa")
                .selectNumberOfAdults("3")
                .selectNumberOfChild("1")
                .clickSearchOfferButton();
    }
}
