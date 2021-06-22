Feature: Test automation assignment

  Write UI automated tests for the following test scenarios. You can use the programming
  language and tools that you prefer.
  The purpose of writing these tests is to show your automation craftsmanship. Try to come up
  with solid solutions and present something that you are proud of. You can take the time you
  need, it's not about speed of execution or cutting corners, it's about the quality of the work.

  Background: Search Query
    Given I Open www.airbnb.com
    And Select "Rome, Italy" as a location
    And Pick a Check-In date "one week after the current date"
    And Pick a Check-Out date "one week after Check-In" date
    And select the number of guests as 2 adults, 1 child, 0 infants
    And click Search

  @Test-1
  Scenario: Verify that the results match the search criteria
    Then verify that Results Header guest count is correct
    And verify that Results Header dates are correct
    And verify that Results Guest Capacity >= 3


  @Test-2 @CloseBrowserTab
  Scenario: Verify that the results and details page match the extra filters
    When open More Filters
    And select 5 bedrooms
    And click "Pool" in the Filter modal
    When click Show Stays
    Then verify that displayed properties bedrooms >= 5
    When I click the 1st search result the property is open in new tab
    And I open the Amenities Pop-up for the Property
    Then "Pool" is present in the Amenities Pop-up

  @Test-3
  Scenario: Verify that a property is displayed on the map correctly
    Given the details for the 1st property in the results list are saved
    Then the property is displayed on the map
    When hovering above the property, the color of the map pin changes
    When click on the map pin of the selected property
    Then the map popup for the selected property is displayed
    And map pop price matches the search result price





