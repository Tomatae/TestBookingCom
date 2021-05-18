Feature: testing search process

  Scenario Outline: Check if the results are shown correctly

    Given user is on main page
    When user fills the search form:
      | destination | <destination> |
      | moveIn | <moveIn> |
      | moveOut | <moveOut> |
      | Adults | <Adults> |
      | Kids | <Kids> |
      | Rooms | <Rooms> |
      | KidsAge | <KidsAge> |
    And clicks on check button
    Then 25 results shown on result page

    Examples:
    | destination | moveIn | moveOut | Adults | Kids | Rooms | KidsAge |
    | Москва | 1 | 7 | 2 | 1 | 1 | 7 |
    | Сочи | 5 | 10 | 8 | 2 | 2 | 3,4 |
    | Санкт-Петербург | 3 | 8 | 4 | 5 | 3 | 4,3,5,7 |
    | Репино | 4 | 9 | 3 | 1 | 4 | 9 |