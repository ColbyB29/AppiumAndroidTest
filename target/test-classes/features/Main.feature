Feature: Main Feature

Scenario Outline: cbTest1
  Given Landing on homepage
  When Selecting an Environment "<envt>" and toggle cheat "<cheatStatus>"
  And Selecting a game "<game>"
  Then I verify that the game is loading
  Examples:
    | envt | cheatStatus | game                           | jurisdiction |
    | DGE  | off         | Legendary Larry                | default      |

  Scenario Outline: cbTest2
    Given Landing on homepage
    When Selecting an Environment "<envt>" and toggle cheat "<cheatStatus>"
    And Selecting a game "<game>"
    Then I verify that the game is loading
    Given testing
    Examples:
      | envt | cheatStatus | game                           | jurisdiction |
      | DGE   | off         | Legendary Larry                | default      |