@crater
Feature: Login functionality
  User should be able to login to account and it should work as expected
  @Smoke
  Scenario: User should be able to login with valid credentials
  Given User is navigated to crater.com
  When User enters "entityadmin@primetechschool.com" in the username field
  And user enters "primetech@school" in password field
  And user clicks on the login button
  Then User is able to access the "Setting Page" and it will be displayed