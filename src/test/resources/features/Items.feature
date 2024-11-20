@crater
Feature: Items Functionality
  As a user, I want to be able to direct items on weather they should be added or deleted

  Background:
    Given User is navigated to crater.com
    When User enters "entityadmin@primetechschool.com" in the username field
    And user enters "primetech@school" in password field
    And user clicks on the login button
    Then User is able to access the "Setting Page" and it will be displayed

  @regression
  Scenario: User should be able to add or delete items
    When user clicks on Items Page
    Then user should be successfully navigated to items page
    When user clicks on + Add Item
    Then user should be successfully navigated to New Items page
    When user enters "Ben's New Baseball Bat" into the Name input
    And  user enters "3500" into Price input
    And  user selects "Dollars" within Unit field
    And  user enters "This Bat is from Ben's inventory." into the Description text field
    When user clicks on the Save Item button
    Then user should be able to see a flash message "Success!" with a close button to the right
    And  user should be able to verify the flash message disappears within 5 seconds or less
    And  user should be navigated to the Items page
    And  user should be able to view new item being displayed within the Items table
    And  user should be able to see entered information which is Name, Unit, Price and Description within the applications database
