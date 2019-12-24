package by.bsu.webframework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import java.util.Date;
import java.util.List;

public class HomePageTest {

    private WebDriver driver;
    private HomePage page;

    @Before
    public void browserSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        page = new HomePage(driver);
        driver.manage().window().maximize();
    }

    @Test
    public void cantBookCarWithOnlyCountrySelected() throws InterruptedException {
        page.selectCountry(1).clickSearch();
        Assert.assertEquals("Место получения - необходимо указать\n" +
                "Город получения - необходимо указать\n", page.getAlertText());
    }

    @Test
    public void countryUpdateShouldUpdateCityList() throws InterruptedException {
        page.selectCountry(1);
        List<String> cities1 = page.getCityList();
        page.selectCountry(2);
        List<String> cities2 = page.getCityList();
        Assert.assertNotEquals(cities1, cities2);
    }

    @Test
    public void cantClickOnPreviousDate() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        String classListBeforeClick = (String) executor.executeScript("return document.querySelector('.ui-datepicker-today').previousSibling.classList.toString()");
        page.openDate().clickOnPreviousDay();
        String classListAfterClick = (String) executor.executeScript("return document.querySelector('.ui-datepicker-today').previousSibling.classList.toString()");
        Assert.assertEquals(classListBeforeClick, classListAfterClick); // classes weren't changed -> nothing happened
    }

    @Test
    public void cantBookCarLessThanInAnHour() throws InterruptedException {
        page.selectCountry(18).selectCity(1).selectTodayPickupDate().selectPickupTime(new Date().getHours() + 1).clickSearch();
        String error = page.getAlertText();
        Assert.assertTrue(error.contains("Получение автомобиля должно быть хотя-бы на 1 час позже"));
    }

    @Test
    public void cantBookCarInPastByInTheSameDay() throws InterruptedException {
        page.selectCountry(18).selectCity(1).selectTodayPickupDate().selectPickupTime(Math.max(0, new Date().getHours() - 5)).clickSearch();
        String error = page.getAlertText();
        Assert.assertTrue(error.contains("Дата получения: до сегодняшней даты"));
    }

    @Test
    public void cantInputEarlierDropDateThanPickupDate() throws InterruptedException {
        page.selectCountry(18).selectCity(1).selectTodayPickupDate().selectDropTime(0).selectTodayDropDate().clickSearch();
        String error = page.getAlertText();
        Assert.assertTrue(error.contains("Дата получения: должны быть до Дата возврата"));
    }

    @Test
    public void canLogin() throws InterruptedException {
        page.openLoginWindow().inputEmail("fijoh12516@mail3x.net").inputPassword("123456").tryLogin();
        String message = page.getSuccessMessage();
        Assert.assertEquals(message, "Добро пожаловать,\n" +
                "вы вошли в сисему.");
    }

    @Test
    public void validatesEmailInSignupForm() throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        page.goToSignupForm().inputSingupEmail("invalidEmail").inputSingupPassword("validPassword123");
        String classListBeforeSubmit = (String) executor.executeScript("return document.querySelector('#modalCrmEmail').classList.toString();");
        page.trySignup();
        Thread.sleep(1000);
        String classListAfterSubmit = (String) executor.executeScript("return document.querySelector('#modalCrmEmail').classList.toString();");
        Assert.assertNotEquals(classListAfterSubmit, classListBeforeSubmit);
    }

    @Test
    public void cantBookInOffHours() throws InterruptedException {
        page.selectCountry(18).selectCity(5).selectPlace(1).selectPickupTime(0).clickSearch();
        String error = page.getErrorMessage();
        Assert.assertTrue(error.contains("Извините"));
    }

    @After
    public void browserTearDown() {
        if (driver != null) {
            driver.close();
        }
    }
}
