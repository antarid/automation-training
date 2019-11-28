package by.bsu.webframework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HomePage extends AbstractPage {

    private static final String PAGE_URL = "https://www.rentalcars.com/ru/";
    private static final int WAIT_TIMEOUT_SECONDS = 40;

    @FindBy(xpath = "//*[@id=\"formsubmit\"]")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id=\"pu-country\"]")
    private WebElement countrySelect;

    @FindBy(xpath = "//*[@id=\"pu-city\"]")
    private WebElement citySelect;

    public HomePage(WebDriver driver) {
        super(driver);
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    @Override
    public HomePage openPage() {
        driver.get(PAGE_URL);
        driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        return this;
    }

    public HomePage selectCountry(int index){
        Select select = new Select(countrySelect);
        select.selectByIndex(index);
        return this;
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

    public HomePage clickSearch(){
        JavascriptExecutor executor = (JavascriptExecutor)driver; // java's click method doesn't works fine
        executor.executeScript("arguments[0].click();", searchButton);
        return this;
    }

}
