package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(css = "div.success.show")
    WebElement notificationRenamePlaylist;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void playlist () {
        WebElement playlist = findWebElement(By.cssSelector(".playlist:nth-child(3)"));
        actions.doubleClick(playlist).perform();
    }

    public static final String NEW_PLAYLIST_NAME = "New Name";
    public void setPlaylistInputField () {
        WebElement playlistInputField = findWebElement(By.cssSelector("[name='name']"));
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL, "New Name", Keys.BACK_SPACE));
        playlistInputField.sendKeys(NEW_PLAYLIST_NAME);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    public boolean isNotificationRenamePlaylistDisplayed() {
        return notificationRenamePlaylist.isDisplayed();
    }
}
