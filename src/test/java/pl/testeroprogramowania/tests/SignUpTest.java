package pl.testeroprogramowania.tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.testeroprogramowania.pages.HotelSearchPage;
import pl.testeroprogramowania.pages.ResultsPage;
import pl.testeroprogramowania.pages.SignUpPage;

public class SignUpTest extends BaseTest {

    @Test
    public void signUp() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        ResultsPage resultsPage = new ResultsPage(driver);

        hotelSearchPage.clickMyAccount();
        hotelSearchPage.clickSignUp();

        signUpPage.completeFirstName("Jan");
        signUpPage.completeLastName("Testowy");
        signUpPage.completePhone("555333222");
        signUpPage.completeRandomEmail();
        signUpPage.completePassword("HasloTestowe");
        signUpPage.completeConfirmPassword("HasloTestowe");
        signUpPage.clickSignUpbutton();


        Assert.assertEquals(resultsPage.welcomeTextAfterLogin.getText(), "Hi, Jan Testowy");
        Assert.assertTrue(resultsPage.welcomeTextAfterLogin.getText().contains("Test"), "Komunikat zwrotny nie zawiera nazwiska użytkownika");

    }

    @Test
    public void tryToSignEmptyForm() {

        SignUpPage signUpPage = new SignUpPage(driver);
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        ResultsPage resultsPage = new ResultsPage(driver);

        hotelSearchPage.clickMyAccount();
        hotelSearchPage.clickSignUp();
        signUpPage.clickSignUpbutton();

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
        Assert.assertTrue(resultsPage.headingError.getText().contains("The Email field is required"), "Email cummunity is not exist");
    }

    @Test
    public void emailAlreadyExistError() {

        SignUpPage signUpPage = new SignUpPage(driver);
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        ResultsPage resultsPage = new ResultsPage(driver);

        hotelSearchPage.clickMyAccount();
        hotelSearchPage.clickSignUp();

        signUpPage.completeFirstName("Jan");
        signUpPage.completeLastName("Testowy");
        signUpPage.completePhone("555333222");
        signUpPage.completeEmail("email@email.com");
        signUpPage.completePassword("HasloTestowe");
        signUpPage.completeConfirmPassword("HasloTestowe");
        signUpPage.clickSignUpbutton();

        Assert.assertEquals(resultsPage.headingError.getText(), "Email Already Exists.");
        Assert.assertTrue(resultsPage.headingError.getText().contains("Email Already Exists."), "Komunikat zwrotny nie dotyczy adresu Email");
    }
    }
