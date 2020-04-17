# Extra Requirements
**Author:** Marc Micatka\
**Last Updated:** 16-February-2020

## Game Requirements
 1. The word entered by the user must contain only letters from the board that are each adjacent to the next (horizontally, vertically, or diagonally) and a single letter on the board may not be used twice.
 2. The word entered by the user does not need to be checked against a dictionary (for simplicity, we will assume the player enters only real words that are spelled correctly).
 3. The User may choose to re-roll the board at a cost of 5 points. The board will be re-created in the same way it is generated at the start of each game, replacing each letter. The timer will not be reset or paused. The player’s score may go into negative values.
 4. Each word will score 1 point per letter (‘Qu’ will count as 2 letters), and the cost for each board reset will be subtracted.
 5. Whenever the board is generated, or re-generated it will meet the following criteria:
	 * The board will consist of a square full of letters. The square should have 	the number of letters, both horizontally and vertically, indicated by the size of the square board from the game settings (4x4, 5x5, 6x6, 7x7, or 8x8).
	 * 1/5 (rounded up) of the letters will be vowels (a,e,i,o,u). 4/5 will be consonants.
	 * The letter Q will be displayed as ‘Qu’ (so that Q never appears alone).
	 * The location and particular letters should be randomly selected from a distribution of letters reflecting the weights of letters from the settings. A letter with a weight of 5 should be 5 times as likely to be chosen as a letter with a weight of 1 (assuming both are consonants or both are vowels). In this way, more common letters can be set to appear more often.
	 * A letter may appear on the board more than once.
## User Requirements
1. There is only one user using the application on any device.
2. The user interface must be responsible and intuitive.
3. The user experience must be fluid and the user must not experience any considerable lag between actions and the response of the application.

## System Requirements
1. This application only runs on a system running Android OS.
2. This application is run by a single system.
