package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{

    @FindBy(css = "button[type='submit']")
    WebElement loginButton;

    @FindBy(css = "input[type='email']")
    WebElement emailField;

    @FindBy(css = "input[type='password']")
    WebElement passwordField;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage provideEmail(String email) {
        clickToElement(emailField);
        sendKeysToElement(emailField, email);
        return this;
    }

    public LoginPage providePassword(String password) {
        clickToElement(passwordField);
        sendKeysToElement(passwordField, password);
        return this;
    }

    public void clickLoginButton() {
        clickToElement(loginButton);
    }

    public void waitUntilLoginButtonDisplayed() {
        waitUntilWebElementDisplayed(loginButton);
    }
}
