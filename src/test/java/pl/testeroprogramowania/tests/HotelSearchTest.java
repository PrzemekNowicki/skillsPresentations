package pl.testeroprogramowania.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.testeroprogramowania.pages.HotelSearchPage;
import pl.testeroprogramowania.pages.ResultsPage;
import java.util.List;

public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() {
        ResultsPage resultsPage = new ResultsPage(driver);
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);

        List<String> hotelNames = hotelSearchPage.setCity("Dubai")
                .setDate()
                .searchHotelClick()
                .getHotelNames();

        hotelNames.forEach(el -> System.out.println(el));
        //lub
        hotelNames.forEach(System.out::println);

        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));
    }

    @Test
    public void noFoundHotelResult() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        ResultsPage resultsPage = new ResultsPage(driver);

        hotelSearchPage.setCity("Warsaw")
                .setDate()
                .searchHotelClick();

        Assert.assertTrue(resultsPage.textCenter.getText().contains("Results"), "Komunikat nie zawiera słowa Results");
        Assert.assertEquals(resultsPage.textCenter.getText(), ("No Results Found"));
    }
}
