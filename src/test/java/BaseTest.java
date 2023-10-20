import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.UUID;

import static java.sql.DriverManager.getDriver;

public abstract class BaseTest {

    private WebDriver driver;
    String prodUrl = "https://koel.dev/";
    //String qaUrl = "https://qa.koel.app/";
    Actions actions;
    Wait<WebDriver> wait;
    private static final ThreadLocal<WebDriver> TREAD_LOCAL_DRIVER = new ThreadLocal<>();
    public static final String QA_URL = "https://qa.koel.app/#!/home";

    //@BeforeSuite
    //static void setupClass() {
      //  WebDriverManager.chromedriver().setup();
    //}

    @BeforeMethod
    @Parameters({"qaUrl"})
    public void setup(String url) throws MalformedURLException {
        TREAD_LOCAL_DRIVER.set(pickDriver(System.getProperty("browser")));

        TREAD_LOCAL_DRIVER.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


       // pickDriver(System.getProperty("browser"));

        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--remote-allow-origins=*");
        //options.addArguments("--disable-notifications");

        //driver = new ChromeDriver(options);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        actions = new Actions(TREAD_LOCAL_DRIVER.get());
        wait = new WebDriverWait(TREAD_LOCAL_DRIVER.get(), Duration.ofSeconds(10));

        TREAD_LOCAL_DRIVER.get().get(url);
    }

    public WebDriver getDriver() {
        return TREAD_LOCAL_DRIVER.get();
    }

    @AfterMethod
    public void closeDriver() {
        getDriver().quit();
    }

    public String generateName() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public WebDriver pickDriver(String browser) throws MalformedURLException {
        String gridUrl = "http://192.168.86.101:4444";
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        switch (browser) {
//            case "firefox":
//                WebDriverManager.firefoxdriver().setup();
//                driver = new FirefoxDriver();
//                return driver;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-notifications");
                driver = new ChromeDriver(options);
                return driver;
            case "grid-chrome":
                desiredCapabilities.setBrowserName("chrome");
                driver = new RemoteWebDriver(URI.create(gridUrl).toURL(), desiredCapabilities);
                return driver;
//            case "grid-firefox":
//                desiredCapabilities.setCapability("browser", "firefox");
//                driver = new RemoteWebDriver(URI.create(gridUrl).toURL(), desiredCapabilities);
//                return driver;
            case "lambda-test":
                return getLambdaDriver();
            default:
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                return driver;
        }
    }

    public WebDriver getLambdaDriver() throws MalformedURLException {
        String userName = "suvorova755";
        String authKey = "c4RPV0qNZEftd0sp5ytXuvO4gh1EKkrJIhY4Yl3ijqjZaWwGIm";
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platform", "Windows 10");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("version", "106.0");
        capabilities.setCapability("resolution", "1024x768");
        capabilities.setCapability("build", "TestNG With Java");
        capabilities.setCapability("name", this.getClass().getName());
        capabilities.setCapability("plugin", "git-testng");

        return new RemoteWebDriver(new URL("https://" + userName + ":" + authKey + hub), capabilities);
    }
}