Feature: user is able to interact with cart

  Scenario Outline: User add item in cart> view it and delete
    Given logged in user
    When add <item> to cart
    Then can view <item> in the cart
    And  delete <item> from cart


    Examples:
      | item |
      | Sauce Labs Backpack |