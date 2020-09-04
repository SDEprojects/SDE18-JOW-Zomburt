package com.zomburt;

import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GameEngine {

  Character player;
  GameStatus gameStatus = new GameStatus();
  Scene currentScene;

  public void run() throws FileNotFoundException, InterruptedException, Exception {
      Boolean win = false, lose = false;

//      gameStatus.start();
      currentScene = new Scene("west entrance");

      Scanner in = new Scanner(System.in);
      System.out.println("What is your name?");
      player = new Character(in.nextLine());

      System.out.println();

//      intro();

      while (win == false || lose == false) {
        System.out.print(" > ");
        String input = in.nextLine();
        if (input.isEmpty()) {
          continue;
        }
        runCommands(input);
      }
  }

  public void quit() throws FileNotFoundException, InterruptedException {
    gameStatus.lose();
    System.exit(0);
  }

  public void intro() throws InterruptedException {
    String intro = player.getName() + ", In the distant year of 2021, an advanced infectious airborne \n" +
            "disease has turned the population into Divoc Zombies. \n" +
            "All that remains is you and a few of your fellow Faction members. \n" +
            "You must navigate through three heavily infected areas to find \n" +
            "the magical antidote and locate your friends and family to free them \n" +
            "from a life of Divoc Suffering. \n" +
            "If you fail, you will become a Divoc .";

    char[] chars = intro.toCharArray();
    // Print a char from the array, then sleep for 1/25 second
    for (int i = 0; i < chars.length; i++) {
      System.out.print(chars[i]);
      Thread.sleep(25);
    }

    System.out.println();
  }

  public void runCommands(String input) throws Exception {
    ArrayList<String> commands = Parser.parse(input.toLowerCase().trim());
    if(commands == null)
      System.out.println("That's not a valid command. For a list of available commands input \" help\"");
    else if(commands.get(0).contains("move")) {
      try {
        move(commands.get(1));
      } catch (Exception e) {
        System.out.println("Movement commands must be two words in length... Pick a direction!");
      }
    }
    else if(commands.get(0).contains("help"))
      help();
    else if(commands.get(0).contains("quit") || commands.get(0).contains("exit"))
      quit();
    else if(commands.get(0).contains("search") || commands.get(0).contains("look"))
      search();
    else if(commands.get(0).contains("inv"))
      System.out.println(player.getName()+ "'s inventory is " + player.getInventory());
    else
      System.out.println(Arrays.toString(commands.toArray()));
  }

  public void search(){
    System.out.println("Upon searching the room you see a skull");

  }

  public static void help() {
    System.out.println("These are some commands you can perform: \n" +
            "-move <direction>-\n" +
            "-inv <view inventory>-" +
            "-use <item>-\n" +
            "-pick up <item>-\n" +
            "-drop <item>-\n" +
            "-look/search-\n" +
            "-open/close- door-\n" +
            "-quit");
  }

  public void move(String moveDir) throws Exception {
    JSONObject moveSet = (JSONObject) currentScene.getMovement();
    String sceneCheck = (String) moveSet.get(moveDir);
    System.out.println("sceneCheck: " + sceneCheck);
    if (sceneCheck != null) {
      currentScene = new Scene(sceneCheck);
      System.out.println(currentScene.toString()); // remove when not testing
      System.out.println(currentScene.getFlavorText());
    }
    else {
      System.out.println("You can't go that way...  Please try another cardinal direction.");
      System.out.println("Your available moves are: " + moveSet);
    }
  }
}











