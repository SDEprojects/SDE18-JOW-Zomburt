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

  public void win() throws FileNotFoundException, InterruptedException {
    GameApp.getInstance().getGameController().getVictory().setVisible(true);
    GameApp.getInstance().getGameController().getVictory().setImage(new Image("file:./game/resource/win.jpg"));
  }

  public void lose() throws FileNotFoundException, InterruptedException {
    GameApp.getInstance().getGameController().getVictory().setVisible(true);
    GameApp.getInstance().getGameController().getVictory().setImage(new Image("file:./game/resource/lose.jpg"));
  }

  }
