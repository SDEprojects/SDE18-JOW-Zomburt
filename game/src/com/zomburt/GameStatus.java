package com.zomburt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameStatus {

  public void start() throws FileNotFoundException, InterruptedException {
    File startFile = new File("..\\Zomburt\\game\\assets\\GameStartFile.txt");
    Scanner scan = new Scanner(startFile);
    while (scan.hasNextLine()) {
      System.out.println(scan.nextLine());
      Thread.sleep(90);
    }
  }

  public void win() throws FileNotFoundException, InterruptedException {
    File winFile = new File("..\\Zomburt\\game\\assets\\GameWinFile.txt");
    Scanner scan = new Scanner(winFile);
    while (scan.hasNextLine()) {
      System.out.println(scan.nextLine());
      Thread.sleep(90);
    }
  }

  public void lose() throws FileNotFoundException, InterruptedException {
    File loseFile = new File("..\\Zomburt\\game\\assets\\GameLoseFile.txt");
    Scanner scan = new Scanner(loseFile);
    while (scan.hasNextLine()) {
      System.out.println(scan.nextLine());
      Thread.sleep(90);
    }
  }


  public void yaml() throws FileNotFoundException, InterruptedException {
      File yamlFile = new File("..\\Zomburt\\game\\assets\\zapi.yaml");
      Scanner scan = new Scanner(yamlFile);
      while (scan.hasNextLine()) {
        System.out.println(scan.nextLine());
        Thread.sleep(90);
      }
    }
  }
