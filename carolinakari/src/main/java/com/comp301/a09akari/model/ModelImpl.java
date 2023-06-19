package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  private PuzzleLibrary library;
  private Puzzle activePuzzle;
  private boolean lamps[][];
  private List<ModelObserver> observerList;
  private int puzzleIndex;
  private int height;
  private int width;

  public ModelImpl(PuzzleLibrary library) {
    if (library == null) {
      throw new IllegalArgumentException("Null error.");
    }
    this.library = library;
    this.activePuzzle = library.getPuzzle(puzzleIndex);
    this.puzzleIndex = 0;
    this.height = activePuzzle.getHeight();
    this.width = activePuzzle.getWidth();
    this.lamps = new boolean[height][width];
    this.observerList = new ArrayList<>();
  }

  @Override
  public void addLamp(int r, int c) {
    if (r >= height || c >= width) {
      throw new IndexOutOfBoundsException("Out of bounds.");
    } else if (activePuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException("Not Corridor type.");
    } else if (activePuzzle.getCellType(r, c) == CellType.CORRIDOR) {
      lamps[r][c] = true;
      notifyObservers();
    }
  }

  @Override
  public void removeLamp(int r, int c) {
    if (r >= height || c >= width) {
      throw new IndexOutOfBoundsException("Out of bounds.");
    } else if (activePuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException("Not corridor type.");
    } else if (activePuzzle.getCellType(r, c) == CellType.CORRIDOR) {
      lamps[r][c] = false;
      notifyObservers();
    }
  }

  @Override
  public boolean isLit(int r, int c) {
    if (r >= height || c >= width) {
      throw new IndexOutOfBoundsException("Out of bounds.");
    } else if (activePuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException("Not corridor type.");
    }
    if (lamps[r][c] == true) {
      return true;
    }
    for (int i = r - 1; i >= 0; i--) {
      if (activePuzzle.getCellType(i, c) != CellType.CORRIDOR) {
        break;
      } else {
        if (isLamp(i, c)) {
          return true;
        }
      }
    }
    for (int i = r + 1; i < height; i++) {
      if (activePuzzle.getCellType(i, c) != CellType.CORRIDOR) {
        break;
      } else {
        if (isLamp(i, c)) {
          return true;
        }
      }
    }
    for (int i = c - 1; i >= 0; i--) {
      if (activePuzzle.getCellType(r, i) != CellType.CORRIDOR) {
        break;
      } else {
        if (isLamp(r, i)) {
          return true;
        }
      }
    }
    for (int i = c + 1; i < width; i++) {
      if (activePuzzle.getCellType(r, i) != CellType.CORRIDOR) {
        break;
      } else {
        if (isLamp(r, i)) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (r >= height || c >= width) {
      throw new IndexOutOfBoundsException("Out of bounds.");
    } else if (activePuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException("Not corridor type.");
    }
    return lamps[r][c];
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (r >= height || c >= width) {
      throw new IndexOutOfBoundsException("Out of bounds.");
    } else if (activePuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException("Not corridor type.");
    }
    if (lamps[r][c] == false) {
      return false;
    }
    for (int i = r - 1; i >= 0; i--) {
      if (activePuzzle.getCellType(i, c) != CellType.CORRIDOR) {
        break;
      } else {
        if (isLamp(i, c)) {
          return true;
        }
      }
    }
    for (int i = r + 1; i < height; i++) {
      if (activePuzzle.getCellType(i, c) != CellType.CORRIDOR) {
        break;
      } else {
        if (isLamp(i, c)) {
          return true;
        }
      }
    }
    for (int i = c - 1; i >= 0; i--) {
      if (activePuzzle.getCellType(r, i) != CellType.CORRIDOR) {
        break;
      } else {
        if (isLamp(r, i)) {
          return true;
        }
      }
    }
    for (int i = c + 1; i < width; i++) {
      if (activePuzzle.getCellType(r, i) != CellType.CORRIDOR) {
        break;
      } else {
        if (isLamp(r, i)) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return activePuzzle;
  }

  @Override
  public int getActivePuzzleIndex() {
    if (puzzleIndex < 0) {
      puzzleIndex = 0;
    }
    if (puzzleIndex > 4) {
      puzzleIndex = 4;
    }
    return puzzleIndex;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    this.puzzleIndex = index;
    this.activePuzzle = this.library.getPuzzle(puzzleIndex);
    this.height = this.activePuzzle.getHeight();
    this.width = this.activePuzzle.getWidth();
    this.lamps = new boolean[height][width];
    notifyObservers();
  }

  @Override
  public int getPuzzleLibrarySize() {
    return library.size();
  }

  @Override
  public void resetPuzzle() {
    lamps = new boolean[height][width];
    notifyObservers();
  }

  @Override
  public boolean isSolved() {
    Puzzle tempPuzzle = getActivePuzzle();
    for (int i = 0; i < tempPuzzle.getHeight(); i++) {
      for (int count = 0; count < tempPuzzle.getWidth(); count++) {
        if (tempPuzzle.getCellType(i, count) == CellType.CORRIDOR) {
          if ((isLamp(i, count)) && isLampIllegal(i, count) || isLit(i, count) == false) {
            return false;
          }
        }
        if (tempPuzzle.getCellType(i, count) == CellType.CLUE) {
          if (isClueSatisfied(i, count) == false) {
            return false;
          }
        }
      }
    }
    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    if (r >= height || c >= width) {
      throw new IndexOutOfBoundsException("Out of bounds.");
    } else if (activePuzzle.getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException("Not type clue.");
    }
    int lamps_required = activePuzzle.getClue(r, c);
    int check = 0;
    if (r + 1 < height && (activePuzzle.getCellType(r + 1, c) == CellType.CORRIDOR)) {
      if (isLamp(r + 1, c)) {
        check++;
      }
    }
    if (!(r - 1 < 0) && (activePuzzle.getCellType(r - 1, c) == CellType.CORRIDOR)) {
      if (isLamp(r - 1, c)) {
        check++;
      }
    }
    if (c + 1 < width && (activePuzzle.getCellType(r, c + 1) == CellType.CORRIDOR)) {
      if (isLamp(r, c + 1)) {
        check++;
      }
    }
    if (!(c - 1 < 0) && (activePuzzle.getCellType(r, c - 1) == CellType.CORRIDOR)) {
      if (isLamp(r, c - 1)) {
        check++;
      }
    }
    return (check == lamps_required);
  }

  @Override
  public void addObserver(ModelObserver observer) {
    observerList.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    observerList.remove(observer);
  }

  private void notifyObservers() {
    for (ModelObserver observer : observerList) {
      observer.update(this);
    }
  }
}
