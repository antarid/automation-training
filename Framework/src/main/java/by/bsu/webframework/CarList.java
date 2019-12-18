package by.bsu.webframework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CarList extends AbstractPage {
    private static final String PAGE_URL = "https://www.rentalcars.com/SearchResults.do?enabler=&country=%d0%91%d0%b5%d0%bb%d0%b0%d1%80%d1%83%d1%81%d1%8c&doYear=2020&city=%d0%92%d0%b8%d1%82%d0%b5%d0%b1%d1%81%d0%ba&driverage=on&doFiltering=true&dropCity=%d0%92%d0%b8%d1%82%d0%b5%d0%b1%d1%81%d0%ba&org.apache.struts.taglib.html.TOKEN=031e51471093d98c7e169d089de78ca3&driversAge=30&filterTo=49&fromLocChoose=true&dropLocationName=%d0%92%d0%b8%d1%82%d0%b5%d0%b1%d1%81%d0%ba+(%d0%92%d1%81%d0%b5+%d0%bc%d0%b5%d1%81%d1%82%d0%b0)+&dropCountryCode=&doMinute=0&countryCode=&puYear=2020&locationName=%d0%92%d0%b8%d1%82%d0%b5%d0%b1%d1%81%d0%ba+(%d0%92%d1%81%d0%b5+%d0%bc%d0%b5%d1%81%d1%82%d0%b0)+&puMinute=0&doDay=25&searchType=allareasgeosearch&filterFrom=0&puMonth=4&dropLocation=-1&doHour=11&dropCountry=%d0%91%d0%b5%d0%bb%d0%b0%d1%80%d1%83%d1%81%d1%8c&puDay=21&puHour=10&location=-1&doMonth=4&filterName=CarCategorisationSupplierFilter";
    private static final int WAIT_TIMEOUT_SECONDS = 40;

    @FindBy(xpath = "//*[@id=\"rch-select-currency\"]")
    private WebElement openCurrencyWindowButton;

    @FindBy(xpath = "//*[@id=\"cur_RON\"]")
    private WebElement differentCurrency;

    @FindBy(xpath = "")
    private WebElement linkToNewCar;

    public CarList(WebDriver driver) {
        super(driver);
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    @Override
    public CarList openPage() {
        driver.get(PAGE_URL);
        driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        return this;
    }

    public CarList openCurrencyWindow() {
        return (CarList) this.clickBySelector(openCurrencyWindowButton);
    }

    public CarList changeDifferentCurrency() {
        return (CarList) this.clickBySelector(differentCurrency);
    }

    public String getPriceOfTheFirstCarInList() {
        return this.waitForElement(By.xpath("/html/body/div[5]/div/div[2]/div[9]/table/tbody/tr[2]/td[3]/span[2]")).getText();
    }

    public CarList goToCarPage() {
        String buttonSelector = ".carResultRow_OfferInfo-btn-primary";

        this.waitForElement(By.cssSelector(buttonSelector));
        return (CarList) this.clickBySelector(driver.findElement(By.cssSelector(buttonSelector)));
    }

    public CarList goToBookingPage() {
        driver.close();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        String buttonSelector = "#addPolicyButton";

        this.waitForElement(By.cssSelector(buttonSelector));
        return (CarList) this.clickBySelector(driver.findElement(By.cssSelector(buttonSelector)));
    }

    public CarList tryToBook() {
        String buttonSelector = "//*[@id=\\\"btn-submit-dd\\\"]";

        this.waitForElement(By.xpath(buttonSelector));
        this.clickBySelector(buttonSelector);
        return this;
    }

    public String getErrorMessage() {
        return this.waitForElement(By.xpath("//*[@id=\"errorTop\"]/p")).getText();
    }

}
