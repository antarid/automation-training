package by.bsu.webdriver.page;




import by.bsu.webdriver.page.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class HomePageTest {
    private WebDriver driver;
    private HomePage page;

    @BeforeClass
    public void browserSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        page = new HomePage(driver);
        driver.manage().window().maximize();
    }

    @Test
    public void cantBookCarWithOnlyCountrySelected(){
        page.selectCountry(1);
        page.clickSearch();
        Assert.assertEquals("Место получения - необходимо указать\n" +
                "Город получения - необходимо указать\n", page.getAlertText());
    }

    @Test
    public void countryUpdateShouldUpdateCityList()  throws  InterruptedException{
        page.selectCountry(1);
        List<String> cities1 = page.getCityList();
        page.selectCountry(2);
        List<String> cities2 = page.getCityList();
        Assert.assertNotEquals(cities1, cities2);
    }

    @AfterClass
    public void browserTearDown() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }
}