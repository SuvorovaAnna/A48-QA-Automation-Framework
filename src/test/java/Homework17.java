import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Homework17 extends BaseTest {
    @Test
    public void addSongToPlaylist() throws InterruptedException {

        String url = "https://qa.koel.app/";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(url);
        WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
        WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        emailField.click();
        emailField.sendKeys("anna.suvorova@testpro.io");

        passwordField.click();
        passwordField.sendKeys("te$t$tudent");

        loginButton.click();
        Thread.sleep(5000);

        WebElement searchField = driver.findElement(By.cssSelector("input[type='search']"));
        searchField.sendKeys("Grav");
        Thread.sleep(5000);

        WebElement viewAllButton = driver.findElement(By.xpath("/html//section[@id='searchExcerptsWrapper']//section[@class='songs']//button[1]"));
        viewAllButton.click();
        Thread.sleep(5000);

        WebElement selectFirstSong = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//div[@class='item-container']/table[@class='items']//td[.='1']"));
        selectFirstSong.click();
        Thread.sleep(5000);

        WebElement addToButton = driver.findElement(By.cssSelector(".btn-add-to"));
        addToButton.click();
        Thread.sleep(5000);

        WebElement newPlayList = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//section[@class='new-playlist']/form/input[@required='required']"));
        newPlayList.click();
        newPlayList.sendKeys("New Playlist");
        Thread.sleep(5000);

        WebElement saveButton = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//section[@class='new-playlist']/form/button[@title='Save']"));
        saveButton.click();
        //Thread.sleep(5000);

        WebElement notificationMsg = driver.findElement(By.cssSelector("div[class='success show']"));

        Assert.assertTrue(notificationMsg.isDisplayed());
        driver.quit();
    }
}
