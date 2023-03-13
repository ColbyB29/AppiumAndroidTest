Feature: Main Feature

Scenario Outline: cbTest1
  Given Landing on homepage
  When Selecting an Environment "<envt>" and toggle cheat "<cheatStatus>"
  And Selecting a game "<game>"
  Then I verify that the game is loading
  Examples:
    | envt | cheatStatus | game                           | jurisdiction |
    | QA  | off         | Legendary Larry                | default      |

  Scenario Outline: Verify Minor and Major jackpot amounts for all bet levels
    Given Landing on homepage
    When Selecting an Environment "<envt>" and toggle cheat "<cheatStatus>"
    And Selecting a game "<game>"
    Then I verify that the game is loading
    Given Verify Minor and Major Jackpots
    Examples:
      | envt | cheatStatus | game                           | jurisdiction |
      | DGE   | off         | Legendary Larry                | default      |

  Scenario Outline: test
    Given Landing on homepage
    When Selecting an Environment "<envt>" and toggle cheat "<cheatStatus>"
    And Selecting a game "<game>"
    Then I verify that the game is loading
    Given testing
    Examples:
      | envt | cheatStatus | game                           | jurisdiction |
      | DGE   | off         | Legendary Larry                | default      |