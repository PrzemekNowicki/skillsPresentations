package pl.testeroprogramowania.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.testeroprogramowania.pages.HotelSearchPage;
import pl.testeroprogramowania.pages.LoggedUserPage;
import pl.testeroprogramowania.pages.ResultsPage;
import pl.testeroprogramowania.pages.SignUpPage;
import pl.testeroprogramowania.testData.User;

public class SignUpTest extends BaseTest {

    @Test
    public void signUp() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        User user = new User(driver);

        hotelSearchPage.clickMyAccount()
                .clickSignUp();

        user.setFirstName("Jan")
                .setLastName("Testowy")
                .setPhone("666666666")
                .completeRandomEmail();
        user.setPassword("Testowe123");

        signUpPage.completeSignUpFormWithRandomEmail(user);

        Assert.assertEquals(loggedUserPage.welcomeTextAfterLogin.getText(), "Hi, Jan Testowy");
        Assert.assertTrue(loggedUserPage.welcomeTextAfterLogin.getText().contains(user.getLastName()), "Komunikat zwrotny nie zawiera nazwiska użytkownika");
    }

    @Test
    public void tryToSignEmptyForm() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        ResultsPage resultsPage = new ResultsPage(driver);

        hotelSearchPage.clickMyAccount()
                .clickSignUp()
                .clickSignUpbutton();

        Assert.assertTrue(resultsPage.headingError.getText().contains("The Email field is required."), "Brak komunikatu dot. email");
        Assert.assertTrue(resultsPage.headingError.getText().contains("The Password field is required."), "Brak komunikatu dot. hasla 1");
        Assert.assertTrue(resultsPage.headingError.getText().contains("The Password field is required."), "Brak komunikatu dot. hasla 2");
        Assert.assertTrue(resultsPage.headingError.getText().contains("The First name field is required."), "Brak komunikatu dot. imienia");
        Assert.assertTrue(resultsPage.headingError.getText().contains("The Last Name field is required."), "Brak omunikatu dot. nazwiska");

        Assert.assertEquals(resultsPage.headingError.getText(), (
                "The Email field is required.\n" +
                        "The Password field is required.\n" +
                        "The Password field is required.\n" +
                        "The First name field is required.\n" +
                        "The Last Name field is required."));
    }

    @Test
    public void emailAlreadyExistError() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        ResultsPage resultsPage = new ResultsPage(driver);
        User user = new User(driver);

        hotelSearchPage.clickMyAccount()
                .clickSignUp();

        user.setFirstName("Jan")
                .setLastName("Testowy")
                .setPhone("666666666")
                .setEmail("test@test.com")
                .setPassword("lato123.");
        signUpPage.completeSignUpFormWithoutRandomEmail(user);
        
        Assert.assertEquals(resultsPage.headingError.getText(), "Email Already Exists.");
        Assert.assertTrue(resultsPage.headingError.getText().contains("Email Already Exists."), "Komunikat zwrotny nie dotyczy adresu Email");
    }
    }
