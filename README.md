# Requirements: What app should do.
1. The game starts at position 0 which is outside the board and it finishes when a player reaches the last square that is 100.
2. The player rolls a single 6 sided dice (die) numbered from 1 to 6 to move their token by the number of squares indicated by the die rolled.
3. The player must roll the exact number to reach the final square. For example, if a player
needs a 3 to reach square 100 and rolls a 5, the player stays at the current location.

# Use case: Scenarios with steps and alternate paths for a specific goal

1. Move Token on role of a Dice until it reaches 100
2. At the start of a Game the Player Token starts at 0 which is outside the Board (requirement 1)
3. Player rolls a Dice (requirement 2)
4. Dice randomly turns up a number between 1-6 inclusive (requirement 2)
5. Player Token moves the number of Squares indicated by Dice (requirement 2)
6. If the Token goes beyond 100, go back to the original position. (requirement 3)
7. Go to step 2 until Player Token is not at 100  (requirement 2)
8. Player Token Wins at Square 100 (requirement 2)
