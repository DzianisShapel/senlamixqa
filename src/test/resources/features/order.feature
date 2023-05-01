Feature: user is able to order the product

  Scenario Outline: User orders the product
    Given logged in with <username> and <password> user
    When add <item> to cart
    Then can view <item> in the cart
    And  is able to complete ordering <item> with <first-name> and <last-name> and <postal-code>


    Examples:
      | item                | first-name  | last-name   | postal-code | username      | password     |
      | Sauce Labs Backpack | fucking     |  cucumber   | 230003      | standard_user | secret_sauce |