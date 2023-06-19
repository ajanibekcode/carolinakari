package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MessageView implements FXComponent {
  private Model model;
  private ClassicMvcController controller;

  public MessageView(Model model, ClassicMvcController controller) {
    this.model = model;
    this.controller = controller;
  }

  @Override
  public Parent render() {
    Label label = new Label("Welcome to Carolina Akari!");
    Label puzzleNumber =
        new Label("Currently playing puzzle " + (model.getActivePuzzleIndex() + 1) + " of " + 5);
    StackPane titleHolder = new StackPane(label);
    StackPane numberHolder = new StackPane(puzzleNumber);
    VBox vbox = new VBox(titleHolder);
    vbox.getChildren().add(numberHolder);
    titleHolder.getStyleClass().add("message-style");
    numberHolder.getStyleClass().add("message-style");
    puzzleNumber.getStyleClass().add("puzzle-number");
    if (model.isSolved()) {
      label.getStyleClass().add("complete-game-style");
      label.setText("You win! Game Complete.");
    }
    return vbox;
  }
}
