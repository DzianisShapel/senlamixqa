Feature: user is able to interact with cart

  Scenario Outline: User adds item to the cart, is able to view it and delete

    Given logged in with <username> and <password> user
    When add <item> to cart
    Then can view <item> in the cart
    And  delete <item> from cart

    Examples:
      | item                | username      | password     |
      | Sauce Labs Backpack | standard_user | secret_sauce |