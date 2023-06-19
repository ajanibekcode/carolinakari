package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PuzzleView implements FXComponent {
  private Model model;
  private ClassicMvcController controller;

  public PuzzleView(Model model, ClassicMvcController controller) {
    this.controller = controller;
    this.model = model;
  }

  @Override
  public Parent render() {
    GridPane gridPuzzle = new GridPane();
    gridPuzzle.getStyleClass().add("puzzle-style");
    gridMap(gridPuzzle);
    return gridPuzzle;
  }

  public void gridMap(GridPane gridPuzzle) {
    for (int column = 0; column < model.getActivePuzzle().getWidth(); column++) {
      for (int row = 0; row < model.getActivePuzzle().getHeight(); row++) {
        StackPane layout = new StackPane();
        Rectangle shape = new Rectangle();
        shape.setStroke(Color.LIGHTBLUE);
        shape.setFill(Color.WHITE);
        shape.setWidth(50);
        shape.setHeight(50);
        layout.getChildren().add(shape);
        gridPuzzle.add(layout, column, row);

        if (model.getActivePuzzle().getCellType(row, column) == CellType.CORRIDOR) {

          if (model.isLit(row, column)) {
            shape.setFill(Color.LIGHTYELLOW);
            if (model.isLamp(row, column)) {
              if (model.isLampIllegal(row, column)) {
                layout.getChildren().add(rameses());
              } else {
                layout.getChildren().add(lightBulb());
              }
            }
          }
          int final_c = column;
          int final_r = row;

          layout.setOnMouseClicked(
              (MouseEvent event) -> {
                controller.clickCell(final_r, final_c);
              });
        } else if (model.getActivePuzzle().getCellType(row, column) == CellType.WALL) {
          shape.setFill(Color.LIGHTBLUE);
        } else if (model.getActivePuzzle().getCellType(row, column) == CellType.CLUE) {
          shape.setFill(Color.LIGHTBLUE);
          if (model.isClueSatisfied(row, column)) {
            shape.setFill(Color.GREEN);
          }
          Label tag = new Label();
          tag.getStyleClass().add("clue-style");
          layout.getChildren().add(tag);
          switch (model.getActivePuzzle().getClue(row, column)) {
            case 0:
              tag.setText("0");
              break;
            case 1:
              tag.setText("1");
              break;
            case 2:
              tag.setText("2");
              break;
            case 3:
              tag.setText("3");
              break;
            case 4:
              tag.setText("4");
              break;
          }
        }
      }
    }
  }

  private ImageView lightBulb() {
    Image bulb = new Image("/light-bulb.png");
    ImageView imagePane = new ImageView();
    imagePane.setFitWidth(50);
    imagePane.setPreserveRatio(true);
    imagePane.setImage(bulb);
    return imagePane;
  }

  private ImageView rameses() {
    Image rameses = new Image("/rameses.png");
    ImageView imagePane = new ImageView();
    imagePane.setFitWidth(50);
    imagePane.setPreserveRatio(true);
    imagePane.setImage(rameses);
    return imagePane;
  }
}
