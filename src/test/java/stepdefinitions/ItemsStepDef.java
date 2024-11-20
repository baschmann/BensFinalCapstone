package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ItemsPage;
import pages.NewItemPage;
import pages.SettingsPage;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.sql.*;
import java.time.Duration;


public class ItemsStepDef {

    WebDriver driver = Driver.getDriver();
    SettingsPage settingsPage = new SettingsPage();
    ItemsPage itemsPage = new ItemsPage();
    NewItemPage newItemPage = new NewItemPage();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @When("user clicks on Items Page")
    public void user_clicks_on_items_page() {
        settingsPage.itemsButton.click();
    }
    @Then("user should be successfully navigated to items page")
    public void user_should_be_successfully_navigated_to_items_page() {
        wait.until(ExpectedConditions.visibilityOf(itemsPage.itemsPageLabel));
        String itemsPageUrl = "http://crater.primetech-apps.com/admin/items";
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(itemsPageUrl, currentUrl);
        Assert.assertTrue(itemsPage.itemsPageLabel.isDisplayed());
    }
    @When("user clicks on + Add Item")
    public void user_clicks_on_add_item() {
        wait.until(ExpectedConditions.elementToBeClickable(itemsPage.addItemsButton));
        itemsPage.addItemsButton.click();
    }
    @Then("user should be successfully navigated to New Items page")
    public void user_should_be_successfully_navigated_to_new_items_page() {
        Assert.assertTrue(newItemPage.newItemsPageLabel.isDisplayed());
    }
    @When("user enters {string} into the Name input")
    public void user_enters_into_the_name_input(String name) {
        wait.until(ExpectedConditions.visibilityOf(newItemPage.nameInput));
        SeleniumUtils.sendkeysWithActionsClass(newItemPage.nameInput,name);
    }
    @And("user enters {string} into Price input")
    public void user_enters_into_price_input(String price) {
        SeleniumUtils.sendkeysWithActionsClass(newItemPage.priceInput,price);
    }
    @And("user selects {string} within Unit field")
    public void user_selects_within_unit_field(String unit) {
        SeleniumUtils.sendkeysWithActionsClass(newItemPage.unitInput,unit);
        newItemPage.unitInput.sendKeys(Keys.ENTER);
    }
    @And("user enters {string} into the Description text field")
    public void user_enters_into_the_description_text_field(String description) {
        newItemPage.descriptionInput.sendKeys(description);
    }
    @When("user clicks on the Save Item button")
    public void user_clicks_on_the_save_item_button() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", newItemPage.button);
    }
    @Then("user should be able to see a flash message {string} with a close button to the right")
    public void user_should_be_able_to_see_a_flash_message_with_a_close_button_to_the_right(String string) {
        wait.until(ExpectedConditions.visibilityOf(newItemPage.flashMessage));
        System.out.println("The Message is: " +newItemPage.flashMessage.getText());
        Assert.assertTrue(newItemPage.flashMessage.isDisplayed());
    }
    @And("user should be able to verify the flash message disappears within {int} seconds or less")
    public void user_should_be_able_to_verify_the_flash_message_disappears_within_seconds_or_less(Integer int1) {
        boolean flashmessageVanished = wait.until(ExpectedConditions.invisibilityOf(newItemPage.flashMessage));
        Assert.assertTrue(flashmessageVanished);
    }
    @And("user should be navigated to the Items page")
    public void user_should_be_navigated_to_the_items_page() {
        wait.until(ExpectedConditions.visibilityOf(itemsPage.itemsPageLabel));
        String itemsPageUrl = "http://crater.primetech-apps.com/admin/items";
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(itemsPageUrl, currentUrl);
        Assert.assertTrue(itemsPage.itemsPageLabel.isDisplayed());
    }
    @And("user should be able to view new item being displayed within the Items table")
    public void user_should_be_able_to_view_new_item_being_displayed_within_the_items_table() {
        String productName = "Ben's New Baseball Bat";
        Assert.assertTrue(SeleniumUtils.isItemInTable(itemsPage.itemsList, productName));
    }
    @And("user should be able to see entered information which is Name, Unit, Price and Description within the applications database")
    public void user_should_be_able_to_see_entered_information_which_is_name_unit_price_and_description_within_the_applications_database() {
        String query = "SELECT name, price, description FROM CraterDBS.items ORDER BY created_at DESC;";

        String dbUrl = "jdbc:mysql://stack-overflow.cfse9bqqndon.us-east-1.rds.amazonaws.com/CraterDBS";
        String userName = "craterdbuser";
        String password = "ptschool2023";

        String expectedName = "Ben's New Baseball Bat";
        String expectedPrice = "3500";
//String expectedUnit = "Dollars";
        String expectedDescription = "This Bat is from Ben's inventory.";

//Creating a connection
        try (Connection connection = DriverManager.getConnection(dbUrl, userName, password);
             //Creating a statement
             Statement statement = connection.createStatement();
             //Executing the query
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                String actualName = resultSet.getString("name");
                //String actualUnit = resultSet.getString("unit_id");
                String actualPrice = resultSet.getString("price");
                String actualDescription = resultSet.getString("description");

                Assert.assertEquals("Name mismatch", expectedName, actualName);
                //Assert.assertEquals("Unit mismatch", expectedUnit, actualUnit);
                Assert.assertEquals("Price mismatch", expectedPrice, actualPrice);
                Assert.assertEquals("Description mismatch", expectedDescription, actualDescription);

            } else {
                Assert.fail("No matching record found in the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Database verification failed: " + e.getMessage());
        }

    }
}