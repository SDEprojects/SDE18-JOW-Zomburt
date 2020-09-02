package com.zomburt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameEnd {

  public void start() throws FileNotFoundException, InterruptedException {
    File loseFile = new File("C:\\StudentWork\\IntmJ\\Zomburt\\game\\assets\\GameStartFile.txt");
    Scanner scan = new Scanner(loseFile);
    while (scan.hasNextLine()) {
      System.out.println(scan.nextLine());
      Thread.sleep(90);
    }
  }

    public void win() throws FileNotFoundException, InterruptedException {
      File loseFile = new File("C:\\StudentWork\\IntmJ\\Zomburt\\game\\assets\\GameWinFile.txt");
      Scanner scan = new Scanner(loseFile);
      while (scan.hasNextLine()) {
        System.out.println(scan.nextLine());
        Thread.sleep(90);
      }
    }

  public void lose() throws FileNotFoundException, InterruptedException {
    File loseFile = new File("C:\\StudentWork\\IntmJ\\Zomburt\\game\\assets\\GameLoseFile.txt");
    Scanner scan = new Scanner(loseFile);
    while (scan.hasNextLine()) {
      System.out.println(scan.nextLine());
      Thread.sleep(90);
    }
  }
}
