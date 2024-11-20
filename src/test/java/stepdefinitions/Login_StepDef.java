package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.SettingsPage;
import utilities.ConfigurationReader;
import utilities.Driver;
import utilities.SeleniumUtils;


public class Login_StepDef {

    WebDriver driver = Driver.getDriver();
    LoginPage loginPage = new LoginPage();
    SettingsPage settingsPage = new SettingsPage();

    @Given("User is navigated to crater.com")
    public void user_is_navigated_to_crater_com() {
        driver.get(ConfigurationReader.getPropertyValue("craterUrl"));

    }

    @When("User enters {string} in the username field")
    public void user_enters_in_the_username_field(String username) {

        SeleniumUtils.sendkeysWithActionsClass(loginPage.emailInput,username);

    }

    @And("user enters {string} in password field")
    public void user_enters_in_password_field(String password) {
        SeleniumUtils.sendkeysWithActionsClass(loginPage.passwordInput,password);

    }

    @And("user clicks on the login button")
    public void user_clicks_on_the_login_button() {
        loginPage.loginButton.click();

    }

    @Then("User is able to access the {string} and it will be displayed")
    public void user_is_able_to_access_the_and_it_will_be_displayed(String string) {
        Assert.assertTrue(settingsPage.settingsLabel.isDisplayed());

    }

}
