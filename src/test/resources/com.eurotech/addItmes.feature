Feature: Add items

  Scenario: User adds all itmes to the cart
    Given user enter login page
    When user enters valid credentials
    Then main page opens
    When user adds all items to the cart
    Then amount of products in the cart is '6'