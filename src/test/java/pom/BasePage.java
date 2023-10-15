package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

public class BasePage {

    private WebDriver driver;
    Actions actions;
    Wait<WebDriver> wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    public void clickToElement(WebElement element) {
        element.click();
    }

    public void sendKeysToElement(WebElement element, String text) {
        element.sendKeys(text);
    }

    public WebElement findWebElement(By locator) {
       return driver.findElement(locator);
    }

    public void waitUntilWebElementDisplayed(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

}
