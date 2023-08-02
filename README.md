# Carolina Akari

<p float ="left">
  <img src ="https://user-images.githubusercontent.com/89208624/257716083-f9cd1f05-2d7d-4014-a481-da8d1692a9c4.png" width="350">
  <img src ="https://user-images.githubusercontent.com/89208624/257716093-2ded5f73-d46d-4a66-ab87-fb079a5325db.png" width="350">
</p>

## Description

A Carolina-themed Akari puzzle game built using Java, JavaFX, and Maven. The Model-View-Controller (MVC) pattern was used to develop the user interface. 

### How to Play

The gameboard consists of a square grid divided into cells. Some cells may be empty, while others contain a number from 0 to 4. Each number represents the number of neighboring cells that must have light bulbs adjacent to them. However, there are certain rules that the user must follow which includes:
1.  Each numbered cell must have the same exact number of neighboring cells with light bulbs as indicated by the number.
2. Light bulbs cannot block the path of light, which means they cannot obstruct the illumination of light.
3.  Light bulbs cannot be placed in adjacent cells horizontally or vertically, they can only be placed diagonally adjacent of each other.

If a user violates any of the rules, the light bulb will turn into a tarheel as demonstrated in the image above.

### Functions

Functions include {"Next Puzzle", "Previous Puzzle", "Reset Puzzle", "Random Puzzle"}
1. Next Puzzle allows user to go from first to the second (next) puzzle out of the five puzzles, if user at the last puzzle (fifth puzzle) then the function will cause user to stay at the fifth puzzle since there is no next puzzle after the last one.
2. Previous Puzzle allows user to go from second to first (previous) puzzle, if user is on the first puzzle they will stay at first puzzle when clicking previous puzzle since there is no previous puzzle from the first one.
3. Reset Puzzle allows user to clear the whole board on the puzzle they are currently one.
4. Random Puzzle gives the user the option to select a random puzzle to work on.

When user completes puzzle, meaning all cells numbered from 0 to 4 are turned green and no light bulbs turn into tarheels, the user will receive a "You Win! Game Complete" message.
  
