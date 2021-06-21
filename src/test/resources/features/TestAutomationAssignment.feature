Feature: Test automation assignment

    Write UI automated tests for the following test scenarios. You can use the programming
    language and tools that you prefer.
    The purpose of writing these tests is to show your automation craftsmanship. Try to come up
    with solid solutions and present something that you are proud of. You can take the time you
    need, it's not about speed of execution or cutting corners, it's about the quality of the work.

    Background: Search Query
        Given I Open www.airbnb.com
        And Select "Rome, Italy" as a location
        And Pick a Check-In date "1 month the current date"
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
        Then verify that properties displayed have more than 5 bedrooms
        When I click the 1st search result the property is open in new tab
        And I open the Amenities Pop-up for the Property
        Then "Pool" is present in the Amenities Pop-up





