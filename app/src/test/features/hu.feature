Feature: UH from Tetris application
  Background:
    Given We open the app

  Scenario: Open the App
    When We start the app
    Then We see an initial Screen

  Scenario: Open Modo Clasico
    Given We start the app
    When We start modo clasico
    Then We are playing modo clasico

  Scenario: Open Muerte Subita
    Given We start the app
    When We start muerte subita
    Then We are playing muerte subita

  Scenario: Game Over
    Given We start modo clasico
    When We put the last piece
    Then We are game over

  Scenario: Change Settings
    Given We Open Settings
    When We change any color piece
    Then We change settings


