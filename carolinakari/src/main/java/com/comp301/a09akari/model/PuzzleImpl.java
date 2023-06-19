package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {
  private int arrayPuzzle[][];
  private int height;
  private int width;

  public PuzzleImpl(int[][] board) {
    if (board == null) {
      throw new IllegalArgumentException("Null error.");
    }
    this.arrayPuzzle = board.clone();
    this.height = board.length;
    this.width = board[0].length;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r < 0 || c < 0 || r > width || c > height) {
      throw new IndexOutOfBoundsException("Out of bounds.");
    }
    int temp = arrayPuzzle[r][c];
    if (temp <= 4) {
      return CellType.CLUE;
    } else if (temp == 5) {
      return CellType.WALL;
    } else {
      return CellType.CORRIDOR;
    }
  }

  @Override
  public int getClue(int r, int c) {
    if (r < 0 || c < 0 || r > width || c > height) {
      throw new IndexOutOfBoundsException("Out of bounds.");
    } else if (getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException("Not Clue.");
    } else {
      return arrayPuzzle[r][c];
    }
  }
}
