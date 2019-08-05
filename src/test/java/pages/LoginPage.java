package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;

public class LoginPage {
    public LoginPage() {
        PageFactory.initElements(Driver.get(), this);
    }
    @FindBy(xpath = "//input[@id='login']")
    public WebElement loginBox;
    @FindBy(id = "password")
    public WebElement passwordBox;
    @FindBy(xpath = "//button[.='Log in']")
    public WebElement loginButton;
    public void login(String usernameStr, String passwordStr){
        loginBox.sendKeys(usernameStr);
        passwordBox.sendKeys(passwordStr);
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(Driver.get(), 5);
        wait.until(ExpectedConditions.titleIs("#Inbox - Odoo"));
    }
}