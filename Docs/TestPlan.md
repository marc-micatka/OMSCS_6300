# Test Plan 
**Author**: Min Zhang, Marc Micatka\
**Last Updated:** 05-March-2020 (version 3)

## 1 Testing Strategy

### 1.1 Overall strategy

To test the individual units or modules separately, Unit Testing will be created by the developers. Unit tests will test what developers implemented, what the functionality should do, and therefore allows the developers the right test that can test this functionality, that can check that the code's correctly implemented functionality.

After individual units or modules pass the test, the interactions among different modules will be tested through Integration Testing by developers.

The next step is to test the complete system as a whole. System testing includes both functional testa and non functional tests. Functional tests will be used to verify the functionality provided by the whole system. Non functional testing will be used to assess different qualities of the system, such as reliability, maintainability, usability, etc. 

Every time we change our system, Regression Testing will be performed to make sure that the changes behave as intended and that the unchanged code is not negatively affected by the modification, by these changes.


### 1.2 Test Selection

Black-box testing is the testing of the software when we look at it as a closed black box without looking at the code.
First step is to identify independently testable features. The following step is to identify what are the relevant inputs once we have these independently testable features. After identifing what are the inputs or the behavior that is worth testing for these features, we're going to derive test specifications. And test case specifications are description of the test cases that we can then use to generate actual test cases.

We will use a specific black box testing technique, the category-partition method. The first step is to identify independently testable features. The second step is to identify categories. Then the next step is to partition categories into choices. Then we need to Identify constraints among choices. After that, we produce and evaluate test case specifications. And finally, the sixth step is to generate test cases from test case specifications.

### 1.3 Adequacy Criterion

We can use  coverage criterion such as Statement Coverege, Control Flow Graphs, Branch Coverage, Condition Coverage to measure the coverage of the test cases.


### 1.4 Bug Tracking

The bug tracking workflow will follow this:
1. Bug discovered and filed by user or tester
2. Bug is assigned to the appropriate developer by the project manager with applicable priority
3. Once developer fixes it, or gives a workaround for it, it is re-assigned to tester for verification
4. Tester verifies, if it is the bug and closes it, or can re-open it if it still exists. If reopened, it goes the entire cycle again.

### 1.5 Technology

JUnit will be used to perform unit testing. It is a unit testing framework for the Java programming language. To run the JUnit test, Junit provides assertion library which is used to evaluate the test result. Annotations of JUnit are used to run the test method. JUnit is also used to run the Automation suite having multiple test cases. It is used by developers to implement unit testing in Java, and accelerate programming speed and increase the quality of code.

## 2 Test Cases
### UI/UX Tests
<table>
<tbody>
<tr>
<th>Test Number</th>
<th>Test Case</th>
<th>Purpose</th>
<th>Steps</th>
<th>Expected Result</th>
<th>Actual Result</th>
<th>Pass/Fail Information</th>
</tr>
<tr>
<td>1</td>
<td>Able to view "Play Game" from Main Menu</td>
<td>Addresses Use Case 1.1</td>
<td>1. Open Application<br />2. Check for "Play Game" button</td>
<td>1. "Play Game" button is visible and able to be clicked</td>
<td>Able to view "Play Game" from Main Menu</td>
<td>Pass</td>
</tr>
<tr>
<td>2</td>
<td>User is shown game board at start of game</td>
<td>Addresses Use Case 1.2</td>
<td>1. Open Application<br />2. Click "Play Game" button<br />3. Check that application displays game board in a random state</td>
<td>1. Game board is displayed<br />2. Game board size matches attribute boardSize<br />3. Letter distribution is 1/5 (rounded up) vowels<br />4. Letter distribution is 4/5 consonants<br />5. Letter distribution is according to letter weights</td>
<td>User is shown game board at start of game</td>
<td>Pass</td>
</tr>
<tr>
<td>3</td>
<td>User has 0 points at start of game</td>
<td>Addresses Use Case 1.2</td>
<td>1. Open Application<br />2. Click "Play Game" button<br />3. Check that application displays game board in a random state</td>
<td>1. Game board is displayed2. Attribute gamePoints is equal to 0</td>
<td>User has 0 points at start of game</td>
<td>Pass</td>
</tr>
<tr>
<td>4</td>
<td>Timer displays at start of game</td>
<td>Addresses Use Case 1.2</td>
<td>1. Open Application<br />2. Click "Play Game" button<br />3. Check that application displays game timer</td>
<td>1. Game board is displayed2. Attribute countdownTimer is visible</td>
<td>Timer displays at start of game</td>
<td>Pass</td>
</tr>
<tr>
<td>5</td>
<td>Timer displays correct time at start of game</td>
<td>Addresses Use Case 1.2</td>
<td>1. Open Application<br />2. Click "Play Game" button<br />3. Check that application displays game timer with correct time</td>
<td>1. Game board is displayed<br />2. Attribute countdownTimer is initially equal to attribute gameLength</td>
<td>Timer displays correct time at start of game</td>
<td>Pass</td>
</tr>
<tr>
<td>6</td>
<td>Timer counts down correctly throughout game</td>
<td>Addresses Use Case 1.2</td>
<td>1. Open Application<br />2. Click "Play Game" button<br />3. Check that application displays game timer with correct time<br />4. Wait 1 minute.5. Check that game timer is equal to (initial time - 1 min).</td>
<td>1. Game board is displayed<br />2. Attribute countdownTimer is initially equal to attribute gameLength<br />3. After 1 minute, attribute countdownTimer is equal to gameLength - 1 minute</td>
<td>Timer counts down correctly throughout game</td>
<td>Pass</td>
</tr>
<tr>
<td>7</td>
<td>User can enter words</td>
<td>Addresses Use Case 1.3</td>
<td>1. Open Application<br />2. Click "Play Game" button<br />3. Select 2 or more adjacent letters on board</td>
<td>1. Word is accepted by game<br />2. gamePoints accumulate according to requirements<br />3. Word is stored in list for statistics</td>
<td>User can enter words</td>
<td>Pass</td>
</tr>
<tr>
<td>8</td>
<td>User cannot enter words that are only 1 letter</td>
<td>Addresses Use Case 1.3</td>
<td>1. Open Application<br />2. Click "Play Game" button<br />3. Select only 1 letter on board</td>
<td>1. Word is not accepted by game<br />2. gamePoints do not accumulate<br />3. Word is not stored in list for statistics</td>
<td>User cannot enter words that are only 1 letter</td>
<td>Pass</td>
</tr>
<tr>
<td>9</td>
<td>User cannot enter words that are non-adjacent</td>
<td>Addresses Use Case 1.3</td>
<td>1. Open Application<br />2. Click "Play Game" button<br />3. Select letters that are non-adjacent</td>
<td>1. Word is unable to be selected<br />2. gamePoints do not accumulate<br />3. Word is not stored in list for statistics</td>
<td>User cannot enter words that are non-adjacent</td>
<td>Pass</td>
</tr>
<tr>
<td>10</td>
<td>User cannot use a single letter twice in the same word</td>
<td>Addresses Use Case 1.3</td>
<td>1. Open Application<br />2. Click "Play Game" button<br />3. Select letter twice for use in the same word</td>
<td>1. Word is unable to be selected<br />2. gamePoints do not accumulate<br />3. Word is not stored in list for statistics</td>
<td>User cannot use a single letter twice in the same word</td>
<td>Pass</td>
</tr>
<tr>
<td>11</td>
<td>User cannot enter word after time has run out</td>
<td>Addresses Use Case 1.3</td>
<td>1. Open Application<br />2. Click "Play Game" button<br />3. Wait until countdownTimer = 0<br />4. Select 2 or more adjacent letters on board</td>
<td>1. Word is unable to be selected<br />2. gamePoints do not accumulate<br />3. Word is not stored in list for statistics</td>
<td>User cannot enter word after time has run out</td>
<td>Pass</td>
</tr>
<tr>
<td>12</td>
<td>Entered words are scored appropriately</td>
<td>Addresses Use Case 1.3</td>
<td>1. Open Application<br />2. Click "Play Game" button<br />3. Select 2 or more adjacent letters on board<br />4. Repeat step 3 with all possible word combinations</td>
<td>1. Words are accepted by game<br />2. gamePoints accumulate according to requirements<br />3. Words are stored in list for statistics</td>
<td>Entered words are scored appropriately</td>
<td>Pass</td>
</tr>
<tr>
<td>13</td>
<td>User can reroll board</td>
<td>Addresses Use Case 1.4</td>
<td>1. Open Application<br />2. Click "Play Game" button<br />3. Click "Reroll Board" button</td>
<td>1. Game board is displayed<br />2. "Reroll Board" button is displayed and able to be clicked<br />3. New board is generated<br />4. gamePoints are updated by -5 points<br />5. countdownTimer is not affected</td>
<td>User can reroll board</td>
<td>Pass</td>
</tr>
<tr>
<td>14</td>
<td>User can leave game early</td>
<td>Addresses Use Case 1.5</td>
<td>1. Open Application<br />2. Click "Play Game" button<br />3. Click "Quit Game" button</td>
<td>1. Game board is displayed<br />2. "Quit Game" button is displayed and able to be clicked<br />3. Game displays current score</td>
<td>User can leave game early</td>
<td>Pass</td>
</tr>
<tr>
<td>15</td>
<td>User can view final score</td>
<td>Addresses Use Case 1.6</td>
<td>1. Open Application<br />2. Click "Play Game" button<br />3. Click "Quit Game" button</td>
<td>1. Game board is displayed<br />2. Game displays current score<br />3. Current score is equal to gamePoints attribute</td>
<td>User can view final score</td>
<td>Pass</td>
</tr>
<tr>
<td>16</td>
<td>User can view final score</td>
<td>Addresses Use Case 1.6</td>
<td>1. Open Application<br />2. Click "Play Game" button<br />3. Enter valid word<br />4. Click "Quit Game" button</td>
<td>1. Game board is displayed<br />2. Game displays current score<br />3. Current score is equal to gamePoints attribute</td>
<td>User can view final score</td>
<td>Pass</td>
</tr>
<tr>
<td>17</td>
<td>User can view final score</td>
<td>Addresses Use Case 1.6</td>
<td>1. Open Application<br />2. Click "Play Game" button<br />3. Click "Reroll Board" button<br />4. Click "Quit Game" button</td>
<td>1. Game board is displayed<br />2. Game displays current score<br />3. Current score is equal to gamePoints attribute</td>
<td>User can view final score</td>
<td>Pass</td>
</tr>
<tr>
<td>18</td>
<td>User can navigate to main menu after viewing final score</td>
<td>Addresses Use Case 1.7</td>
<td>1. Open Application<br />2. Click "Play Game" button<br />3. Click "Quit Game" button</td>
<td>1. Game board is displayed<br />2. "Quit Game" button is displayed and able to be clicked<br />3. Game displays current score<br />4. "Main Menu" button is displayed and able to be clicked<br />5. Main Menu is shown</td>
<td>User can navigate to main menu after viewing final score</td>
<td>Pass</td>
</tr>
<tr>
<td>19</td>
<td>User can navigate to main menu after viewing final score</td>
<td>Addresses Use Case 1.7</td>
<td>1. Open Application<br />2. Click "Play Game" button<br />3. Wait until countdownTimer == 0</td>
<td>1. Game board is displayed<br />2. "Quit Game" button is displayed and able to be clicked<br />3. Game displays current score<br />4. "Main Menu" button is displayed and able to be clicked<br />5. Main Menu is shown</td>
<td>User can navigate to main menu after viewing final score</td>
<td>Pass</td>
</tr>
<tr>
<td>20</td>
<td>Able to view "View Statistics" from Main Menu</td>
<td>Addresses Use Case 2.1</td>
<td>1. Open Application<br />2. Check for "View Statistics" button<br />3. Click "View Statistics" button</td>
<td>1. "View Statistics" button is visible and able to be clicked<br />2. View changes to "View Statistics" page</td>
<td>Able to view "View Statistics" from Main Menu</td>
<td>Pass</td>
</tr>
<tr>
<td>21</td>
<td>User can view Game Score Statistics</td>
<td>Addresses Use Case 2.2</td>
<td>1. Open Application<br />2. Check for "View Statistics" button<br />3. Click "View Statistics" button<br />4. Check for "Game Score Statistics" button<br />5. Click "Game Score Statistics" button</td>
<td>1. "View Statistics" button is visible and able to be clicked<br />2. "Game Score Statistics" button is visible and able to be clicked<br />3. Game Score statistics show correct summary of games played</td>
<td>User can view Game Score Statistics - Statistics are Incomplete</td>
<td>Pass</td>
</tr>
<tr>
<td>22</td>
<td>User can view individual Game Statistics</td>
<td>Addresses Use Case 2.3</td>
<td>1. Open Application<br />2. Check for "View Statistics" button<br />3. Click "View Statistics" button<br />4. Check for "Game Score Statistics" button<br />5. Click "Game Score Statistics" button<br />6. Click on specific game in table</td>
<td>1. "View Statistics" button is visible and able to be clicked<br />2. "Game Score Statistics" button is visible and able to be clicked<br />3. Game Score statistics show correct summary of games played according to game database<br />4. Pop up appears showing specific game settings<br />5. Game settings displayed are correct according to game database.</td>
<td>User cannot view individual Game Statistics</td>
<td>Pass</td>
</tr>
<tr>
<td>23</td>
<td>User can view Word Statistics</td>
<td>Addresses Use Case 2.4</td>
<td>1. Open Application<br />2. Check for "View Statistics" button<br />3. Click "View Statistics" button<br />4. Check for "Word Statistics" button<br />5. Click "Word Statistics"</td>
<td>1. "View Statistics" button is visible and able to be clicked<br />2. "Word Statistics" button is visible and able to be clicked<br />3. Word statistics show correct summary of words played according to game database</td>
<td>User can view Word Statistics - Statistics are Incomplete</td>
<td>Pass</td>
</tr>
<tr>
<td>24</td>
<td>Game Score Statistics calculate correctly</td>
<td>Addresses Use Case 2.4</td>
<td>1. Open Applicaiton<br />2. Play Game, Enter Words, note score<br />3. Exit Game, Click "View Statistics"<br />4. Click "Game Score Statistics"</td>
<td>1. Most recent game score is listed correctly in descending order.</td>
<td>Most recent game score is listed correctly in descending numeric order.</td>
<td>Pass</td>
</tr>
<tr>
<td>25</td>
<td>Word Score Statistics Calculate correctly</td>
<td>Addresses Use Case 2.4</td>
<td>1. Open Applicaiton<br />2. Play Game, Enter Words, note score<br />3. Exit Game, Click "View Statistics"<br />4. Click "Word Score Statistics"</td>
<td>1. Most recent word score is listed correctly in descending order. <br />Word is show with correct point totals.</td>
<td>Most recent word score is listed correctly in descending order.&nbsp;<br />Word is show with correct point totals.&nbsp;</td>
<td>Pass</td>
</tr>
<tr>
<td>26</td>
<td>Able to view "Change Settings" from Main Menu</td>
<td>Addresses Use Case 3.1</td>
<td>1. Open Application<br />2. Check for "Change Settings" button</td>
<td>1. "Change Settings" button is visible and able to be clicked</td>
<td>Able to view "Change Settings" from Main Menu</td>
<td>Pass</td>
</tr>
<tr>
<td>27</td>
<td>User can view "Change Settings"</td>
<td>Addresses Use Case 3.1</td>
<td>1. Open Application<br />2. Check for "Change Settings" button<br />3. Click "Change Settings" button</td>
<td>1. "Change Settings" button is visible and able to be clicked<br />2. "Change Settings" page is displayed</td>
<td>
<p>User can view "Change Settings".&nbsp;</p>
</td>
<td>Pass</td>
</tr>
<tr>
<td>28</td>
<td>User can change game length</td>
<td>Addresses Use Case 3.2</td>
<td>1. Open Application<br />2. Check for "Change Settings" button<br />3. Click "Change Settings" button<br />4. "Game Length" entry field is able to be changed<br />5. New Game Length value is entered<br />6. Settings are saved</td>
<td>1. "Change Settings" button is visible and able to be clicked<br />2. "Change Settings" page is displayed<br />3. gameLength attribute is updated to match new entered value.</td>
<td>User can change game length. Game length is udpated dynamically on Change Settings screen and is passed persistently through screens.</td>
<td>Pass</td>
</tr>
<tr>
<td>29</td>
<td>User can change board size</td>
<td>Addresses Use Case 3.2</td>
<td>1. Open Application<br />2. Check for "Change Settings" button<br />3. Click "Change Settings" button<br />4. "Board Size" entry field is able to be changed<br />5. New Board Size value is entered<br />6. Settings are saved</td>
<td>1. "Change Settings" button is visible and able to be clicked<br />2. "Change Settings" page is displayed<br />3. boardSize attribute is updated to match new entered value.</td>
<td>User can change board size.&nbsp;Board Size&nbsp;is udpated dynamically on Change Settings screen and is passed persistently through screens.</td>
<td>Pass</td>
</tr>
<tr>
<td>30</td>
<td>User can change letter weights</td>
<td>Addresses Use Case 3.2</td>
<td>1. Open Application<br />2. Check for "Change Settings" button<br />3. Click "Change Settings" button<br />4. "Letter Weights" entry field is able to be changed<br />5. New letter weights values are entered<br />6. Settings are saved</td>
<td>1. "Change Settings" button is visible and able to be clicked<br />2. "Change Settings" page is displayed<br />3. letterWeights attribute is updated to match new entered values.</td>
<td>User can change letter weights.&nbsp;Letter Weights&nbsp;are&nbsp;udpated dynamically on Change Settings screen and&nbsp;are passed persistently through screens.</td>
<td>Pass</td>
</tr>
<tr>
<td>31</td>
<td>Compatibility testing</td>
<td>Ensure that the developed product is compatible with different hardware platforms</td>
<td>1. Select different hardware platforms<br />2. Run Application</td>
<td>The Application functions consistently</td>
<td>&nbsp;The Application functions consistently</td>
<td>Pass</td>
</tr>
<tr>
<td>32</td>
<td>Usability testing</td>
<td>Test performance of the game</td>
<td>1. Open Application<br />2. Play a game</td>
<td>The performance of the game should be such that users do not&nbsp; experience any considerable lag between their actions<br /> and the response of the application.</td>
<td>No noticeable lag between screens or actions.</td>
<td>Pass</td>
</tr>
<tr>
<td>33</td>
<td>GUI testing</td>
<td>Test user interface</td>
<td>1. Open Application<br />2. Press different buttons</td>
<td>The Application is responsive and intuitive</td>
<td>The Application is responsive and intuitive. Layouts change dynamically to accomodate screen size differences. Portrait and Landscape modes are defined appropriately (all but PlayGame).&nbsp;</td>
<td>Pass</td>
</tr>
<tr>
<td>34</td>
<td>Test Landscape User Interface</td>
<td>Test user interface</td>
<td>1. Open Application<br />2.Rotate device<br />3. Navigate to different screens<br />4. Rotate device</td>
<td>The view updates responsively and intuitively to keep all content on screen.</td>
<td>Main Screen, Change Settings and View Statistics all respond fluidly.<br />Play Game - Screen is locked to portrait.</td>
<td>Pass</td>
</tr>
<tr>
<td>35</td>
<td>Test Persistence</td>
<td>Test that app data persists through multiple sessions</td>
<td>1. Open Application<br />2. Navigate to settings<br />3. Change settings, close app.<br />4. Reopen app, navigate to settings</td>
<td>Settings are maintained as they were last set by user - not restored to defaults.;</td>
<td>
<p>Settings persists through app closures and game plays.&nbsp;</p>
<p>Resetting settings in Change Settings works appropriately.</p>
</td>
<td>Pass</td>
</tr>
</tbody>
</table>

### Unit Tests
<table>
<tbody>
<tr>
<th>Test Number</th>
<th>Test Case</th>
<th>Purpose</th>
<th>Steps</th>
<th>Expected Result</th>
<th>Actual Result</th>
<th>Pass/Fail Information</th>
</tr>
<tr>
<td>36</td>
<td>Generate Random Board</td>
<td>Test generateInitialBoard() functionality</td>
<td>1. Generate 10000 random initial boards at each board size with equal weights<br />2. Measure letter distribution</td>
<td>1. Game board size matches attribute boardSize<br />2. Letter distribution is 1/5 (rounded up) vowels<br />3. Letter distribution is 4/5 consonants<br />4. Letter distribution is according to letter weights</td>
<td>Letter distribution is 1/5 (rounded up) vowelsLetter distribution is according to weights (equal distribution)</td>
<td>Pass</td>
</tr>
<tr>
<td>37</td>
<td>Generate Random Board</td>
<td>Test generateInitialBoard() functionality</td>
<td>1. Generate 10000 random initial boards at each board size with unequal weights<br />2. Measure letter distribution</td>
<td>1. Game board size matches attribute boardSize<br />2. Letter distribution is 1/5 (rounded up) vowels<br />3. Letter distribution is 4/5 consonants<br />4. Letter distribution is according to letter weights</td>
<td>Letter distribution is 1/5 (rounded up) vowelsLetter distribution is according to weights (unequal distribution) <br />-Letter with weight 5 is 5 times more frequent than letters with weight 1.</td>
<td>Pass</td>
</tr>
<tr>
<td>38</td>
<td>Decrement Game Points</td>
<td>Test addRerollPoints() functionality</td>
<td>1. Query Game Points<br />2. Reroll Board<br />3. Re-query Game Points</td>
<td>1. Final game points should be initial game points - 5.</td>
<td>Final game points is initial game points - 5.</td>
<td>Pass</td>
</tr>
</tbody>
</table>
