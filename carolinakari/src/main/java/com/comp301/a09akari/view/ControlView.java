package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class ControlView implements FXComponent {
  private Model model;
  private ClassicMvcController controller;

  public ControlView(Model model, ClassicMvcController controller) {
    this.model = model;
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox layout = new HBox();
    layout.getStyleClass().add("control-style");

    Button next = new Button("Next Puzzle");
    layout.getChildren().add(next);
    next.setOnAction(
        (ActionEvent event) -> {
          controller.clickNextPuzzle();
        });

    Button prev = new Button("Previous Puzzle");
    layout.getChildren().add(prev);
    prev.setOnAction(
        (ActionEvent event) -> {
          controller.clickPrevPuzzle();
        });

    Button reset = new Button("Reset Puzzle");
    layout.getChildren().add(reset);
    reset.setOnAction(
        (ActionEvent event) -> {
          controller.clickResetPuzzle();
        });

    Button random = new Button("Random Puzzle");
    layout.getChildren().add(random);
    random.setOnAction(
        (ActionEvent event) -> {
          controller.clickRandPuzzle();
        });

    return layout;
  }
}
