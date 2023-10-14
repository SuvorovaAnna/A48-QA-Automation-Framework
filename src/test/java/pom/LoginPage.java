package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void provideEmail(String email) {
        WebElement emailField = findWebElement(By.cssSelector("input[type='email']"));
        clickToElement(emailField);
        sendKeysToElement(emailField, email);
    }

    public void providePassword(String password) {
        WebElement passwordField = findWebElement(By.cssSelector("input[type='password']"));
        clickToElement(passwordField);
        sendKeysToElement(passwordField, password);
    }

    public void clickLoginButton(){
        WebElement loginButton = findWebElement(By.cssSelector("button[type='submit']"));
        clickToElement(loginButton);
    }


}
