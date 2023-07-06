package pl.tourist.tools;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    public static WebDriver getDriver(String name) {
        if (name.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }

        if (name.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver();

        } else {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
    }
}
