Feature: Main Feature

Scenario Outline: cbTest1
  Given Landing on homepage
  When Selecting an Environment "<envt>" and toggle cheat "<cheatStatus>"
  And Selecting a game "<game>"
  Then I verify that the game is loading
  Examples:
    | envt | cheatStatus | game                           | jurisdiction |
    | QA   | off         | Legendary Larry                | default      |