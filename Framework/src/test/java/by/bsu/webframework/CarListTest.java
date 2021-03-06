package by.bsu.webframework;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import java.util.List;

public class CarListTest {

    private WebDriver driver;
    private CarList page;

    @Before
    public void browserSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        page = new CarList(driver);
        driver.manage().window().maximize();
    }

//    @Test
//    public void currencyChangeShouldAffectPrices() throws InterruptedException {
//        String priceBeforeCurrencyChange = page.getPriceOfTheFirstCarInList();
//        page.openCurrencyWindow().changeDifferentCurrency();
//        String priceAfterCurrencyChange = page.getPriceOfTheFirstCarInList();
//        Assert.assertNotEquals(priceAfterCurrencyChange, priceBeforeCurrencyChange);
//    }
//
//    @Test
//    public void cantBookWithEmptyFields(){
//        page.goToCarPage().goToBookingPage().tryToBook();
//        String errorMessage = page.getErrorMessage();
//        Assert.assertEquals(errorMessage, "Пропущенные поля показаны красным...");
//    }

    @After
    public void browserTearDown() {
        if (driver != null) {
            driver.close();
        }
    }
}
