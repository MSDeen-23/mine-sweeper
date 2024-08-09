# Problem statement: MineSweeper App
Write a program that simulates a Minesweeper game on a square grid.

- The game should begin by prompting the user for the grid size and the number of mines to be randomly placed on the grid.

- The program should then generate the grid and randomly place the specified number of mines on the grid.

- The user should then be prompted to select a square on the grid to uncover.
    - If the selected square contains a mine, the game is over and the user loses.
    - Otherwise, the selected square is uncovered and reveals a number indicating how many of its adjacent squares contain mines.
    - If an uncovered square has no adjacent mines, the program should automatically uncover all adjacent squares until it reaches squares that do have adjacent mines.

- The game is won when all non-mine squares have been uncovered.

- The program should display the game grid and allow the user to input their choices through a command line interface.

- Additionally, the program should track the user's progress throughout the game, displaying the minefield after each user input.

## Game play

### Sucess example
```
Welcome to Minesweeper!

Enter the size of the grid (e.g. 4 for a 4x4 grid): 
4
Enter the number of mines to place on the grid (maximum is 35% of the total squares): 
3

Here is your minefield:
    1 2 3 4
A _ _ _ _
B _ _ _ _
C _ _ _ _
D _ _ _ _

Select a square to reveal (e.g. A1): D4
This square contains 0 adjacent mines. 

Here is your updated minefield:
    1 2 3 4
A _ _ 2 0
B _ _ 2 0
C _ 2 1 0
D _ 1 0 0

Select a square to reveal (e.g. A1): B1
This square contains 3 adjacent mines. 

Here is your updated minefield:
    1 2 3 4
A _ _ 2 0
B 3 _ 2 0
C _ 2 1 0
D _ 1 0 0

Select a square to reveal (e.g. A1): A1
This square contains 2 adjacent mines. 

Here is your updated minefield:
    1 2 3 4
A 2 _ 2 0
B 3 _ 2 0
C _ 2 1 0
D _ 1 0 0

Select a square to reveal (e.g. A1): D1
This square contains 1 adjacent mines. 

Here is your updated minefield:
    1 2 3 4
A 2 _ 2 0
B 3 _ 2 0
C _ 2 1 0
D 1 1 0 0

Congratulations, you have won the game!
Press any key to play again...
```
Failure example
```
Welcome to Minesweeper!

Enter the size of the grid (e.g. 4 for a 4x4 grid): 
3
Enter the number of mines to place on the grid (maximum is 35% of the total squares): 
3

Here is your minefield:
  1 2 3
A _ _ _
B _ _ _
C _ _ _

Select a square to reveal (e.g. A1): C3
Oh no, you detonated a mine! Game over.
Press any key to play again...
```

Incorrect input example
Failure example
```
Welcome to Minesweeper!

Enter the size of the grid (e.g. 4 for a 4x4 grid): 
2
Minimum size of grid is 2.
Enter the size of the grid (e.g. 4 for a 4x4 grid): 
11
Maximum size of grid is 10.
Enter the size of the grid (e.g. 4 for a 4x4 grid): 
A
Incorect input.
Enter the size of the grid (e.g. 4 for a 4x4 grid): 
3
Enter the number of mines to place on the grid (maximum is 35% of the total squares):
4
Maximum number is 35% of total sqaures.
Enter the number of mines to place on the grid (maximum is 35% of the total squares):
0
There must be at least 1 mine.
Enter the number of mines to place on the grid (maximum is 35% of the total squares):
A
Incorrect input.
Enter the number of mines to place on the grid (maximum is 35% of the total squares):
3
Here is your minefield:
  1 2 3
A _ _ _
B _ _ _
C _ _ _

Select a square to reveal (e.g. A1): A
Incorrect input.
Select a square to reveal (e.g. A1):
```
# Result
# How to Run This Project

- **Git Clone:**
  - Clone the repository from [https://github.com/MSDeen-23/mine-sweeper](https://github.com/MSDeen-23/mine-sweeper)

- **Or Download as ZIP:**
  - Download the source code from the GitHub repository as a ZIP file.
  - Unzip the downloaded file to a directory of your choice.

- **Navigate to the Project Directory:**
  ```bash
  cd mine-sweeper
  ```
- **Running as a Java Project**:
  - Requirements: JDK and JRE (Preferred version 17)
    - Compile the java files
      ```bash 
      javac -d out src/main/java/org/gic/minesweeper/*.java src/main/java/org/gic/Main.java
      ```
    - Run the application
      ```bash 
      java -cp out org.gic.Main
      ```
- **Running as a maven project**
  - Requirements: maven
    - Build the project
      ```bash 
      mvn clean install
      ```
    - Run tests (optional, skip if not needed)
      ```bash 
      mvn test (To run the tests skip if not needed)
      ```
    - Run the application
      ```bash 
      mvn exec:java -Dexec.mainClass="org.gic.Main"
      ```
      
# Sample output: WON
```
Welcome to Minesweeper!
Enter the size of the grid (e.g. 4 for a 4x4 grid): 4
Enter the number of mines to place on the grid (maximum is 35% of the total squares): 3
Here is your mineField
  1 2 3 4
A _ _ _ _
B _ _ _ _
C _ _ _ _
D _ _ _ _
Select a square to reveal (e.g. A1): A1
Here is your mineField
  1 2 3 4
A 0 _ _ _
B _ _ _ _
C _ _ _ _
D _ _ _ _
Select a square to reveal (e.g. A1): B1
Here is your mineField
  1 2 3 4
A 0 _ _ _
B 1 _ _ _
C _ _ _ _
D _ _ _ _
Select a square to reveal (e.g. A1): A2
Here is your mineField
  1 2 3 4
A 0 1 _ _
B 1 _ _ _
C _ _ _ _
D _ _ _ _
Select a square to reveal (e.g. A1): B2
Here is your mineField
  1 2 3 4
A 0 1 _ _
B 1 2 _ _
C _ _ _ _
D _ _ _ _
Select a square to reveal (e.g. A1): D1
Here is your mineField
  1 2 3 4
A 0 1 _ _
B 1 2 _ _
C _ _ _ _
D 2 _ _ _
Select a square to reveal (e.g. A1): D4
Here is your mineField
  1 2 3 4
A 0 1 _ _
B 1 2 _ _
C _ _ _ _
D 2 _ _ 0
Select a square to reveal (e.g. A1): C4
Here is your mineField
  1 2 3 4
A 0 1 _ _
B 1 2 _ _
C _ _ _ 0
D 2 _ _ 0
Select a square to reveal (e.g. A1): B4
Here is your mineField
  1 2 3 4
A 0 1 _ _
B 1 2 _ 1
C _ _ _ 0
D 2 _ _ 0
Select a square to reveal (e.g. A1): D3
Here is your mineField
  1 2 3 4
A 0 1 _ _
B 1 2 _ 1
C _ _ _ 0
D 2 _ 2 0
Select a square to reveal (e.g. A1): C3
Here is your mineField
  1 2 3 4
A 0 1 _ _
B 1 2 _ 1
C _ _ 2 0
D 2 _ 2 0
Select a square to reveal (e.g. A1): B3
Here is your mineField
  1 2 3 4
A 0 1 _ _
B 1 2 2 1
C _ _ 2 0
D 2 _ 2 0
Select a square to reveal (e.g. A1): A4
Here is your mineField
  1 2 3 4
A 0 1 _ 1
B 1 2 2 1
C _ _ 2 0
D 2 _ 2 0
Select a square to reveal (e.g. A1): C1
Congratulations, you have won the game!
The minefield was:
  1 2 3 4
A 0 0 X 0
B 0 0 0 0
C 0 X 0 0
D 0 X 0 0
Press any key to continue or press X to exit:
```

# Sample output: LOST
```
Welcome to Minesweeper!
Enter the size of the grid (e.g. 4 for a 4x4 grid): 4
Enter the number of mines to place on the grid (maximum is 35% of the total squares): 3
Here is your mineField
  1 2 3 4 
A _ _ _ _ 
B _ _ _ _ 
C _ _ _ _ 
D _ _ _ _ 
Select a square to reveal (e.g. A1): A1
Here is your mineField
  1 2 3 4 
A 0 _ _ _ 
B _ _ _ _ 
C _ _ _ _ 
D _ _ _ _ 
Select a square to reveal (e.g. A1): A2
Here is your mineField
  1 2 3 4 
A 0 1 _ _ 
B _ _ _ _ 
C _ _ _ _ 
D _ _ _ _ 
Select a square to reveal (e.g. A1): A3
Oh no, you detonated a mine! Game over.
The minefield was:
  1 2 3 4 
A 0 0 X X 
B 0 0 0 0 
C 0 0 0 0 
D 0 X 0 0 
Press any key to continue or press X to exit: 

```