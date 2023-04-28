package pl.testeroprogramowania.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HotelSearch {
    @Test
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']")).click();
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Dubai");
        //Zlokowalizowanie elementu będącego podpowiedzią i kliknięcie w niego przy użyciu złożonego xpath
        driver.findElement(By.xpath("//*[@id=\"select2-drop\"]//span")).click();
        driver.findElement(By.name("checkin")).sendKeys("01/05/2023");
        driver.findElement(By.name("checkout")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='30']"))
                .stream()
                //alternatywa jest wyrazenie lambda (el -> el.isDisplayed)
                .filter(WebElement::isDisplayed)
                .findFirst()
                // alternatywa jest wyrażenie lambda: (el -> el.click)
                .ifPresent(WebElement::click);
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[text()=' Search']")).click();

        // Wyciągnięcie listy zwróconych hoteli
        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class, 'list_title')]//b"))
                .stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());

        System.out.println(hotelNames.size());
        // Wypisanie nazw hoteli w konsoli
        System.out.println(hotelNames);
        //lub można: (wyswietlone nazwy jeden ppod drugim - bardziej prawidlowe rozwiazanie)
        hotelNames.forEach(el -> System.out.println(el));
        //lub nie używając wyrażenia lambda
        hotelNames.forEach(System.out::println);

        //ASERCJE
        Assert.assertEquals("Jumeirah Beach Hotel",hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower",hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));
        driver.quit();
    }

    @Test
    public void noFoundHotelResult() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

        // wybranie daty OD bedącej zawsze datą jutrzejszą

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();

        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String tomorrowAsString = dateFormat.format(tomorrow);

        driver.findElement(By.name("checkin")).sendKeys(tomorrowAsString);


        //wybranie daty DO innym sposobem, zawsze przyszły miesiąc 15tego

        driver.findElement(By.name("checkout")).click();
        driver.findElement(By.xpath("//div[10]/div[1]//tr[1]/th[3]")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='15']"))
                .stream()
                //alternatywa jest wyrazenie lambda (el -> el.isDisplayed)
                .filter(WebElement::isDisplayed)
                .findFirst()
                // alternatywa jest wyrażenie lambda: (el -> el.click)
                .ifPresent(WebElement::click);
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[text()=' Search']")).click();

        WebElement textCenter = driver.findElement(By.xpath("//h2[@class='text-center']"));

        Assert.assertTrue(textCenter.getText().contains("Results"), "Komunikat nie zawiera słowa Results");
        Assert.assertEquals(textCenter.getText(), ("No Results Found"));
        driver.quit();
    }



}
