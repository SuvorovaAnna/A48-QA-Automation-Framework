package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DeletePlaylistPage extends BasePage{

    public DeletePlaylistPage(WebDriver driver) {
        super(driver);
    }

    public void openPlaylist() {
        WebElement emptyPlaylist = findWebElement(By.cssSelector(".playlist:nth-child(3)"));
        clickToElement(emptyPlaylist);
    }

    public void clickDeletePlaylistButton() throws InterruptedException {
        WebElement deletePlaylist = findWebElement(By.cssSelector(".btn-delete-playlist"));
        clickToElement(deletePlaylist);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
    }

    public String DeletePlaylistMsg(){
        WebElement notificationMsg = findWebElement(By.cssSelector("div.success.show"));
        return notificationMsg.getText();
    }

}
