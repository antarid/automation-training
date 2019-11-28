package by.bsu.webdriver.page;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HomePage {
    private final String HOMEPAGE_URL = "https://www.rentalcars.com/ru/";
    private final int WAIT_TIMEOUT_SECONDS = 30;

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.get(HOMEPAGE_URL);
        PageFactory.initElements(this.driver, this);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
    }

    @FindBy(xpath = "//*[@id=\"formsubmit\"]")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id=\"pu-country\"]")
    private WebElement countrySelect;

    @FindBy(xpath = "//*[@id=\"pu-city\"]")
    private WebElement citySelect;

    public void selectCountry(int index){
        Select select = new Select(countrySelect);
        select.selectByIndex(index);
    }

    public List<String> getCityList() throws InterruptedException{
        Select select = new Select(citySelect);
        Thread.sleep(1000);
        return select.getOptions().stream().map(option -> option.getText()).collect(Collectors.toList());
    }

    public String getAlertText(){
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        return text;
    }

    public void clickSearch(){
        JavascriptExecutor executor = (JavascriptExecutor)driver; // java's click method doesn't works fine
        executor.executeScript("arguments[0].click();", searchButton);
    }

}