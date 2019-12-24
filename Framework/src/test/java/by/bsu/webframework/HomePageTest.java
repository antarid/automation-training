package by.bsu.webframework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

public class HomePageTest {

    private WebDriver driver;
    private HomePage page;

    @BeforeTest
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
        browserSetUp();
        page.selectCountry(1);
        List<String> cities1 = page.getCityList();
        page.selectCountry(2);
        List<String> cities2 = page.getCityList();
        Assert.assertNotEquals(cities1, cities2);
        browserTearDown();
    }

    @Test
    public void cantClickOnPreviousDate() {
        browserSetUp();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        String classListBeforeClick = (String) executor.executeScript("return document.querySelector('.ui-datepicker-today').previousSibling.classList.toString()");
        page.openDate().clickOnPreviousDay();
        String classListAfterClick = (String) executor.executeScript("return document.querySelector('.ui-datepicker-today').previousSibling.classList.toString()");
        Assert.assertEquals(classListBeforeClick, classListAfterClick); // classes weren't changed -> nothing happened
    }

    @Test
    public void cantBookCarLessThanInAnHour() throws InterruptedException {
        browserSetUp();
        page.selectCountry(18).selectCity(1).selectTodayPickupDate().selectPickupTime(new Date().getHours() + 1).clickSearch();
        String error = page.getAlertText();
        Assert.assertTrue(error.contains("Получение автомобиля должно быть хотя-бы на 1 час позже"));
        browserTearDown();

    }

    @Test
    public void cantBookCarInPastByInTheSameDay() throws InterruptedException {
        browserSetUp();
        page.selectCountry(18).selectCity(1).selectTodayPickupDate().selectPickupTime(Math.max(0, new Date().getHours() - 5)).clickSearch();
        String error = page.getAlertText();
        Assert.assertTrue(error.contains("Дата получения: до сегодняшней даты"));
        browserTearDown();

    }

    @Test
    public void cantInputEarlierDropDateThanPickupDate() throws InterruptedException {
        browserSetUp();
        page.selectCountry(18).selectCity(1).selectTodayPickupDate().selectDropTime(0).selectTodayDropDate().clickSearch();
        String error = page.getAlertText();
        Assert.assertTrue(error.contains("Дата получения: должны быть до Дата возврата"));
        browserTearDown();

    }

    @Test
    public void canLogin() throws InterruptedException {
        browserSetUp();
        page.openLoginWindow().inputEmail("fijoh12516@mail3x.net").inputPassword("123456").tryLogin();
        String message = page.getSuccessMessage();
        Assert.assertEquals(message, "Добро пожаловать,\n" +
                "вы вошли в сисему.");
        browserTearDown();

    }

    @Test
    public void validatesEmailInSignupForm() throws InterruptedException {
        browserSetUp();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        page.goToSignupForm().inputSingupEmail("invalidEmail").inputSingupPassword("validPassword123");
        String classListBeforeSubmit = (String) executor.executeScript("return document.querySelector('#modalCrmEmail').classList.toString();");
        page.trySignup();
        Thread.sleep(1000);
        String classListAfterSubmit = (String) executor.executeScript("return document.querySelector('#modalCrmEmail').classList.toString();");
        Assert.assertNotEquals(classListAfterSubmit, classListBeforeSubmit);
        browserTearDown();

    }

    @Test
    public void cantBookInOffHours() throws InterruptedException {
        browserSetUp();
        page.selectCountry(18).selectCity(5).selectPlace(1).selectPickupTime(0).clickSearch();
        String error = page.getErrorMessage();
        Assert.assertTrue(error.contains("Извините"));
        browserTearDown();

    }

    @AfterTest
    public void browserTearDown() {
        if (driver != null) {
            driver.close();
        }
    }
}
