package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LoginPage {



    public LoginPage() {//This is Constructor of the class
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(xpath = "//input[@name='email']")
    public WebElement emailInput;

    @FindBy(xpath = "//input[@name='password']" )
    public WebElement passwordInput;

    @FindBy(xpath ="//button[text()='Login' and @type='submit']")
    public WebElement loginButton;
}
