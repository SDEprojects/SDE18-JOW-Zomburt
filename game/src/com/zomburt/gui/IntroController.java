package com.zomburt.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class IntroController {

  @FXML private Button startGame;
  @FXML private TextArea intro;

  public IntroController() {}

  public Button getStartGame() {
    return startGame;
  }

  public TextArea getIntro() {
    return intro;
  }
}
