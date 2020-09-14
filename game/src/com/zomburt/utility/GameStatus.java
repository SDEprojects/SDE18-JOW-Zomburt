package com.zomburt.utility;

import com.zomburt.gui.GameApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameStatus {

  public void start() throws FileNotFoundException, InterruptedException {
    File startFile = new File("../Zomburt/game/assets/GameStartFile.txt");
    Scanner scan = new Scanner(startFile).useDelimiter("\\s+");
    while (scan.hasNextLine()) {
      GameApp.getInstance().appendToCurActivity(scan.nextLine());
      Thread.sleep(90);
    }

//    try {
//      Stream<String> content = Files.lines(Paths.get("../Zomburt/game/assets/GameStartFile.txt"));
//      content.collect(Collectors.toList()).forEach(e->{
//        GameApp.getInstance().appendToCurActivity(e);
//        try {
//          Thread.sleep(90);
//        } catch (InterruptedException interruptedException) {
//          interruptedException.printStackTrace();
//        }
//      });
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
  }

  public void win() throws FileNotFoundException, InterruptedException {
    File winFile = new File("../Zomburt/game/assets/GameWinFile.txt");
    Scanner scan = new Scanner(winFile);
    while (scan.hasNextLine()) {
      GameApp.getInstance().appendToCurActivity(scan.nextLine());
      Thread.sleep(90);
    }
  }

  public void lose() throws FileNotFoundException, InterruptedException {
    File loseFile = new File("../Zomburt/game/assets/GameLoseFile.txt");
    Scanner scan = new Scanner(loseFile);
    while (scan.hasNextLine()) {
      GameApp.getInstance().appendToCurActivity(scan.nextLine());
      Thread.sleep(90);
    }
  }


  public void yaml() throws FileNotFoundException, InterruptedException {
      File yamlFile = new File("../Zomburt/game/assets/zapi.yaml");
      Scanner scan = new Scanner(yamlFile);
      while (scan.hasNextLine()) {
        GameApp.getInstance().appendToCurActivity(scan.nextLine());
        Thread.sleep(90);
      }
    }
  }
