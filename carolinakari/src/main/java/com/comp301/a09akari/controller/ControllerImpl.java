package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ControllerImpl implements ClassicMvcController {
  private Model model;

  public ControllerImpl(Model model) {
    if (model == null) {
      throw new IllegalArgumentException("Null error.");
    }
    this.model = model;
  }

  @Override
  public void clickNextPuzzle() {
    try {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() + 1);
    } catch (IndexOutOfBoundsException e) {
    }
  }

  @Override
  public void clickPrevPuzzle() {
    try {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() - 1);
    } catch (IndexOutOfBoundsException e) {
    }
  }

  @Override
  public void clickRandPuzzle() {
    List<Integer> randomizer = new ArrayList<>();
    for (int i = 0; i < model.getPuzzleLibrarySize(); i++) {
      randomizer.add(i);
    }
    Collections.shuffle(randomizer);
    model.setActivePuzzleIndex(randomizer.get(randomizer.size() / 2));
  }

  @Override
  public void clickResetPuzzle() {
    model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    if (model.getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR) {
      if (model.isLamp(r, c)) {
        model.removeLamp(r, c);
      } else {
        model.addLamp(r, c);
      }
    }
  }
}
