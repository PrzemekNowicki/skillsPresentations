package pl.testeroprogramowania.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.testeroprogramowania.pages.HotelSearchPage;
import pl.testeroprogramowania.pages.ResultsPage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() {
        ResultsPage resultsPage = new ResultsPage(driver);
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);

        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDate();
        hotelSearchPage.searchHotelClick();

        List<String> hotelNames = resultsPage.getHotelNames();
        //lub można: (wyswietlone nazwy jeden pod drugim - bardziej prawidlowe rozwiazanie)
        hotelNames.forEach(el -> System.out.println(el));
        //lub nie używając wyrażenia lambda
        hotelNames.forEach(System.out::println);

        //ASERCJE
        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));
    }

    @Test
    public void noFoundHotelResult() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        ResultsPage resultsPage = new ResultsPage(driver);

        hotelSearchPage.setCity("Warsaw");
        hotelSearchPage.setDate();
        hotelSearchPage.setTravellers("4", "2");
        hotelSearchPage.searchHotelClick();

        Assert.assertTrue(resultsPage.textCenter.getText().contains("Results"), "Komunikat nie zawiera słowa Results");
        Assert.assertEquals(resultsPage.textCenter.getText(), ("No Results Found"));
    }
}
