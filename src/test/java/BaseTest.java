import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.UUID;

import static java.sql.DriverManager.getDriver;

public abstract class BaseTest {

    WebDriver driver;
    public static final String QA_URL = "https://qa.koel.app/#!/home";
    //String qaUrl = "https://qa.koel.app/";

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    @Parameters({"qaUrl"})
    public void setup(String url) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(url);
    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterMethod
    public void closeDriver() {
        getDriver().quit();
    }

    public void clickToElement(WebElement element) {
        element.click();
    }

    public void sendKeysToElement(WebElement element, String text) {
        element.sendKeys(text);
    }

    public String generateName() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public void provideEmail(String email) {
        WebElement emailField = getDriver().findElement(By.cssSelector("input[type='email']"));
        clickToElement(emailField);
        sendKeysToElement(emailField, email);
    }

    public void providePassword(String password) {
        WebElement passwordField = getDriver().findElement(By.cssSelector("input[type='password']"));
        clickToElement(passwordField);
        sendKeysToElement(passwordField, password);
    }
    public void openPlaylist() {
        WebElement emptyPlaylist = getDriver().findElement(By.cssSelector(".playlist:nth-child(3)"));
        clickToElement(emptyPlaylist);
    }

    public void clickDeletePlaylistButton() throws InterruptedException {
        WebElement deletePlaylist = getDriver().findElement(By.cssSelector(".btn-delete-playlist"));
        clickToElement(deletePlaylist);
       // WebElement deletePlaylistSubmit = getDriver().findElement(By.cssSelector("button[class='ok'"));
        //clickToElement(deletePlaylistSubmit);
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
       // Thread.sleep(2000);
    }

    public String DeletePlaylistMsg(){
        WebElement notificationMsg = getDriver().findElement(By.cssSelector("div.success.show"));
        return notificationMsg.getText();
    }
}
