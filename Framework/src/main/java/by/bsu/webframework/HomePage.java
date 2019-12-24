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

     @FindBy(xpath = "//*[@id=\"doHour\"]")
    private WebElement dropTimeSelect;

     @FindBy(xpath = "//*[@id=\"puHour\"]")
    private WebElement pickupTimeSelect;

@FindBy(xpath = "//*[@id=\"pu-location\"]")
    private WebElement placeSelect;

@FindBy(xpath = "/html/body/div[3]/div[5]/div[3]/h1")
    private WebElement errorMessage;

    @FindBy(xpath = "//*[@id=\"SearchResultsForm\"]/div[2]/div[1]/fieldset[1]/div[1]")
    private WebElement dateSection;

    @FindBy(xpath = "//*[@id=\"rch-select-sign-in\"]")
    private WebElement loginButton;

@FindBy(xpath = "//*[@id=\"crmEmail\"]")
    private WebElement emailInput;


@FindBy(xpath = "//*[@id=\"crmPsw\"]")
    private WebElement passwordInput;

    @FindBy(xpath = "//*[@id=\"modalCrmEmail\"]")
    private WebElement signupEmailInput;

 @FindBy(xpath = "//*[@id=\"CreateAccountmodal_promo\"]/div[2]/input[2]")
    private WebElement signupPasswordInput;



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

    public HomePage selectCountry(int index) throws InterruptedException{
        Thread.sleep(1000);
        Select select = new Select(countrySelect);
        select.selectByIndex(index);
        return this;
    }

public HomePage selectPlace(int index) throws InterruptedException{
        Thread.sleep(1000);
        Select select = new Select(placeSelect);
        select.selectByIndex(index);
        return this;
    }

    public HomePage selectTodayPickupDate(){
        this.openDate().clickOnTodayPickup();
        return this;
    }

    public HomePage selectTodayDropDate(){
        this.clickOnTodayDrop();
        return this;
    }

    public HomePage selectDropTime(int time){
        Select select = new Select(dropTimeSelect);
        select.selectByIndex(time);
        return this;
    }

    public HomePage selectPickupTime(int time){
        Select select = new Select(pickupTimeSelect);
        select.selectByIndex(time);
        return this;
    }

    public HomePage selectCity(int index) throws InterruptedException{
        Thread.sleep(1000);
        Select select = new Select(citySelect);
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
        this.clickBySelector(searchButton);
        return this;
    }

    public HomePage openDate(){
        this.clickBySelector(driver.findElement(By.cssSelector("#SearchResultsForm > div:nth-child(5) > div.datetime-section.cf > fieldset.datetime-section__pu > div.a11y-panel > span")));
        return this;
    }

    public HomePage clickOnTodayDrop() {
        String todaySelector = "td.first.ui-datepicker-current-day.ui-datepicker-today";
        this.waitForElement(By.cssSelector(todaySelector));
        this.clickBySelector(driver.findElement(By.cssSelector(todaySelector)));
        return this;

    }

    public HomePage clickOnTodayPickup(){
        String todaySelector = "td.ui-datepicker-today";
        this.waitForElement(By.cssSelector(todaySelector));
        this.clickBySelector(driver.findElement(By.cssSelector(todaySelector)));
        return this;
    }

    public String getErrorMessage(){
        String selector = ".stage-header>h1";
        this.waitForElement(By.cssSelector(selector)).getText();
        return driver.findElement(By.cssSelector(selector)).getText();
    }

    public HomePage clickOnPreviousDay() {
        JavascriptExecutor executor = (JavascriptExecutor)driver; // java's click method doesn't works fine
        executor.executeScript("document.querySelector('.ui-datepicker-today').previousSibling.click()", dateSection);
        return this;
    }

    public HomePage openLoginWindow(){
        this.clickBySelector(loginButton);
        return  this;
    }

    public HomePage inputEmail(String email){
        emailInput.sendKeys(email);
        return this;
    }
  public HomePage inputPassword(String password){
        passwordInput.sendKeys(password);
        return this;
    }

    public HomePage inputSingupEmail(String email){
        this.waitForElement(By.xpath("//*[@id=\"modalCrmEmail\"]"));
        signupEmailInput.sendKeys(email);
        return this;
    }

    public HomePage inputSingupPassword(String password){
        signupPasswordInput.sendKeys(password);
        return this;
    }

    public HomePage trySignup(){
        this.clickBySelector(driver.findElement(By.cssSelector("button.loyaltyCompSignIn")));
        return this;
    }


    public HomePage goToSignupForm(){
        this.openLoginWindow();
        this.clickBySelector(driver.findElement(By.cssSelector("#createAccountModalOpen")));
        return this;
    }

    public HomePage tryLogin(){
        this.clickBySelector("//*[@id=\"crmLogin\"]");
        return this;
    }

    public String getSuccessMessage(){
        String messageSelector = ".rch-welcome-message";
        this.waitForElement(By.cssSelector(messageSelector));
        return driver.findElement(By.cssSelector(messageSelector)).getText();
    }

}
