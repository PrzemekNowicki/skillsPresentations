package pl.testeroprogramowania.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.testeroprogramowania.tools.DriverFactory;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = DriverFactory.getDriver("edge");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }


}
