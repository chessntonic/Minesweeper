# Minesweeper


- The game is text-based.
- It's played on two-dimensional arrays and is designed within the MineSweeper class.
- The user determines the size of the matrix (number of rows and columns).
- A quarter of the total number of elements in the array (elementCount / 4) is randomly placed with mines. For example, if the matrix is 4x3 in size, the number of tiles is calculated using the formula (rowCount * columnCount), and the size is 12. In this case, the number of mines is set to 
  12 / 4 = 3.
- The user selects a point on the matrix and enters it as row and column values.
- It is checked whether the selected point is within the boundaries of the array, and if the condition is not met, the user is asked for a coordinate again.
- If there is a mine at the selected coordinates, the user loses the game.
- If there is no mine at the selected coordinates, all adjacent positions are checked (right, left, up, down, top-left diagonal, top-right diagonal, bottom-right diagonal, bottom-left diagonal), and the total number of mines around that point is written to that point. If no mines are around    the coordinate, the value "0" is assigned.
- If the user selects all points without hitting any mines, they win the game.
- When the game ends, the solution map is shown to the user, and the result (win/lose) is printed to the terminal with an appropriate message.
