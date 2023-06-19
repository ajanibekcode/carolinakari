package com.comp301.a09akari.view;

import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  private static final int SIZE = 700;

  @Override
  public void start(Stage stage) {
    PuzzleLibrary library = new PuzzleLibraryImpl();
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_01));
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_02));
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_03));
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_04));
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_05));
    Model model = new ModelImpl(library);
    ControllerImpl controller = new ControllerImpl(model);
    GameView gameView = new GameView(model, controller);
    Scene scene = new Scene(gameView.render(), SIZE, SIZE);
    scene.getStylesheets().add("main.css");
    stage.setScene(scene);
    model.addObserver(
        (Model mo) -> {
          scene.setRoot(gameView.render());
          stage.sizeToScene();
        });
    stage.setTitle("Akari by Aknazar Janibek");
    stage.show();
  }
}
