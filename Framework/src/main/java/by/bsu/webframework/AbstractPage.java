package by.bsu.webframework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

    protected WebDriver driver;

    protected abstract AbstractPage openPage();

    protected AbstractPage(WebDriver webDriver) {
        this.driver = webDriver;
    }

    protected AbstractPage clickBySelector(WebElement selector) {
        JavascriptExecutor executor = (JavascriptExecutor) driver; // java's click method doesn't works fine
        executor.executeScript("arguments[0].click();", selector);

        return this;
    }

    protected AbstractPage clickBySelector(final String selector) {
        WebElement element = driver.findElement(By.xpath(selector));
        return this.clickBySelector(element);
    }

    protected WebElement waitForElement(By by){
        return new WebDriverWait(driver, 40)
                .until(ExpectedConditions
                        .presenceOfElementLocated(by));
    }
}
