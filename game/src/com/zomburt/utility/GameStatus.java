package com.zomburt.utility;

import com.zomburt.gui.GameApp;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameStatus {

  public void start() throws FileNotFoundException, InterruptedException {
    File startFile = new File("../Zomburt/game/assets/intro.txt");
    Scanner scan = new Scanner(startFile);
    while (scan.hasNextLine()) {
      GameApp.getInstance().appendToCurActivity(scan.nextLine());
      Thread.sleep(90);
    }
  }

  public void win() {
    GameApp.getInstance().getGameController().getVictory().setVisible(true);
    GameApp.getInstance().getGameController().getVictory().setImage(new Image("file:./game/assets/pictures/win.jpg"));
    GameApp.getInstance().getGameController().getInput().setDisable(true);
    GameApp.getInstance().getGameController().getEnter().setDisable(true);
    GameApp.getInstance().updateGameStatusWON();
    GameApp.getEngine().recordGameResults();
    Sound.playSound("win");
  }

  public void lose() {
    GameApp.getInstance().getGameController().getVictory().setVisible(true);
    GameApp.getInstance().getGameController().getVictory().setImage(new Image("file:./game/assets/pictures/lose.jpg"));
    GameApp.getInstance().getGameController().getInput().setDisable(true);
    GameApp.getInstance().getGameController().getEnter().setDisable(true);
    GameApp.getInstance().updateGameStatusLost();
    GameApp.getEngine().recordGameResults();
    Sound.playSound("lose");
  }

}
