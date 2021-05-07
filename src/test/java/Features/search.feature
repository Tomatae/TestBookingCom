Feature: testing search process

  Scenario Outline: Check if the results are shown correctly

    Given user is on main page
    When user goes to <destination>, moves in <moveIn> days, moves out in <moveOut> days, there will be <Adults> adults and <Kids> kids, user needs <Rooms> rooms, the kids ages are <Kid0Age>
    And clicks on check button
    Then user is navigated to the result page

    Examples:
    | destination | moveIn | moveOut | Adults | Kids | Rooms | Kid0Age |
    | Москва | 1 | 7 | 2 | 1 | 1 | 7 |
    | Сочи | 5 | 10 | 8 | 1 | 2 | 3 |
    | Санкт-Петербург | 3 | 8 | 5 | 1 | 3 | 4 |
    | Репино | 4 | 9 | 3 | 1 | 4 | 9 |