package pl.testeroprogramowania.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SignUpTest {
    @Test
    public void signUp() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
String lastname = "Testowy";
//Losowanie liczby
int randomNumber = (int) (Math.random()*1000);
String emailTestNumber = "testNumber" + randomNumber + "@testy.com";

        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);

        //driver.findElements(By.xpath("//a[contains(text(), '  Sign Up')]")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        //lub
        driver.findElements(By.xpath("//a[contains(text(), '  Sign Up')]")).get(1).click();

        driver.findElement(By.name("firstname")).sendKeys("Przemek");
        driver.findElement(By.name("lastname")).sendKeys("Testowy");
        driver.findElement(By.name("phone")).sendKeys("123456789");
        driver.findElement(By.name("email")).sendKeys(emailTestNumber);
        driver.findElement(By.name("password")).sendKeys("Testowo");
        driver.findElement(By.name("confirmpassword")).sendKeys("Testowo");
        driver.findElements(By.xpath("//button[@class]")).get(2).click();

        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));

        Assert.assertTrue(heading.getText().contains(lastname), "Komunikat zwrotny nie zawiera nazwiska użytkownika");
        Assert.assertEquals(heading.getText(), "Hi, Przemek Testowy");

        driver.quit();

    }

    @Test
    public void tryToSignEmptyForm() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);

        //driver.findElements(By.xpath("//a[contains(text(), '  Sign Up')]")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        //lub
        driver.findElements(By.xpath("//a[contains(text(), '  Sign Up')]")).get(1).click();

        driver.findElements(By.xpath("//button[@class]")).get(2).click();

        WebElement headingError = driver.findElement(By.xpath("//div[@class='alert alert-danger']"));

        Assert.assertTrue(headingError.getText().contains("The Email field is required."));
        Assert.assertTrue(headingError.getText().contains("The Password field is required."));
        Assert.assertTrue(headingError.getText().contains("The Password field is required."));
        Assert.assertTrue(headingError.getText().contains("The First name field is required."));
        Assert.assertTrue(headingError.getText().contains("The Last Name field is required."));

        Assert.assertEquals(headingError.getText(),
                "The Email field is required.\n" +
                "The Password field is required.\n" +
                "The Password field is required.\n" +
                "The First name field is required.\n" +
                "The Last Name field is required.");

        Assert.assertTrue(headingError.getText().contains("The Email field is required"), "Email cummunity is not exist");

        driver.quit();
    }

    @Test
    public void emailAlreadyExistError() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);

        //driver.findElements(By.xpath("//a[contains(text(), '  Sign Up')]")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        //lub
        driver.findElements(By.xpath("//a[contains(text(), '  Sign Up')]")).get(1).click();

        driver.findElement(By.name("firstname")).sendKeys("Przemek");
        driver.findElement(By.name("lastname")).sendKeys("Testowy");
        driver.findElement(By.name("phone")).sendKeys("123456789");
        driver.findElement(By.name("email")).sendKeys("testy@testy.com");
        driver.findElement(By.name("password")).sendKeys("Testowo");
        driver.findElement(By.name("confirmpassword")).sendKeys("Testowo");
        driver.findElements(By.xpath("//button[@class]")).get(2).click();

        WebElement heading = driver.findElement(By.xpath("//div[@class='alert alert-danger']"));

        Assert.assertEquals(heading.getText(), "Email Already Exists.");
        Assert.assertTrue(heading.getText().contains("Email Already Exists."), "Komunikat zwrotny nie dotyczy adresu Email");

        driver.quit();
    }


    }
