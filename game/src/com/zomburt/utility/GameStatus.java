package com.zomburt.utility;

import com.zomburt.GameEngine;
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

  public void win() throws FileNotFoundException, InterruptedException {
    GameApp.getInstance().getGameController().getVictory().setVisible(true);
    GameApp.getInstance().getGameController().getVictory().setImage(new Image("file:./game/resource/win.jpg"));
    GameApp.getInstance().getGameController().getInput().setDisable(true);
    GameApp.getInstance().getGameController().getEnter().setDisable(true);
    GameApp.getInstance().updateGameStatusWON();
    GameEngine.recordGameResults();
  }

  public void lose() throws FileNotFoundException, InterruptedException {
    GameApp.getInstance().getGameController().getVictory().setVisible(true);
    GameApp.getInstance().getGameController().getVictory().setImage(new Image("file:./game/resource/lose.jpg"));
    GameApp.getInstance().getGameController().getInput().setDisable(true);
    GameApp.getInstance().getGameController().getEnter().setDisable(true);
    GameApp.getInstance().updateGameStatusLost();
    GameEngine.recordGameResults();
  }

  }
