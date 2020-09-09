package com.zomburt;

import com.zomburt.combat.Combat;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GameEngine {

  public static Character player;
  GameStatus gameStatus = new GameStatus();
  Scene currentScene;
  Boolean newScene = true;
  Boolean win = false;
  Universe gameUniverse = new Universe();

  public GameEngine() throws Exception {
  }

  public void run() throws Exception {

//      gameStatus.start();
      currentScene = new Scene("parking lot");

      Scanner in = new Scanner(System.in);
      System.out.println("What is your name?");
      player = new Character(in.nextLine());

      System.out.print("\n" + player.getName() + ", ");

//      intro();

      while (win == false) {
        if (newScene)
          System.out.println(currentScene.getFlavorText());
        if (currentScene.getFeature().contains("zombie"))
          Combat.combat(player, new Zombie());
        System.out.print(" > ");
        String input = in.nextLine();
        if (input.isEmpty()) {
          newScene = false;
          continue;
        }
        runCommands(input);
      }
  }

  public void runCommands(String input) throws Exception {
    newScene = false;
    ArrayList<String> commands = Parser.parse(input.toLowerCase().trim());
    if (commands == null)
      System.out.println("That's not a valid command. For a list of available commands input \" help\"");
    else if (commands.get(0).contains("move")) {
      try {
        System.out.println(commands.get(1));
        move(commands.get(1));
      } catch (Exception e) {
        JSONObject dirCheck = (JSONObject) currentScene.getMovement();
        try {
          dirCheck.get(commands.get(1));
          if (dirCheck.get(commands.get(1)).equals(""))
            System.out.println("Not an available direction. Try again.");
        } catch (Exception e2) {
          System.out.println("Movement commands must be two recognized words in length... Pick a cardinal direction!");
        }
      }
    }
    else if (commands.get(0).contains("help"))
      help();
    else if (commands.get(0).contains("quit") || commands.get(0).contains("exit"))
      quit();
    else if (commands.get(0).contains("drop") || commands.get(0).contains("pick up"))
      itemHandler(commands.get(0), commands.get(1).toUpperCase());
    else if (commands.get(0).contains("search"))
      search();
    else if (commands.get(0).contains("look"))
      look();
    else if (commands.get(0).contains("inv"))
      System.out.println(player.getName()+ "'s inventory is " + player.getInventory());
    else if (commands.get(0).contains("hint"))
      hint();
    else
//      System.out.println("final else block reached");
      System.out.println(Arrays.toString(commands.toArray()));
  }

  public void itemHandler(String action, String s) {
    if (action.equals("drop")) {
      if (player.getInventory().contains(s)) {
        player.removeInventory(s);
        currentScene.addRoomLoot(s);
        System.out.println("current roomLoot: " + currentScene.getRoomLoot());
      } else {
        System.out.println("You don't have that item");
      }
    }
    if (action.equals("pick up")) {
      if (currentScene.getRoomLoot().contains(s)) {
        player.addInventory(s);
        currentScene.removeRoomLoot(s);
        System.out.println("current roomLoot: " + currentScene.getRoomLoot());
//        System.out.println(player.getInventory());
      } else
        System.out.println("That item isn't here");
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
            "If you fail, you will become a Divoc. \n";

    char[] chars = intro.toCharArray();
    // Print a char from the array, then sleep for 1/25 second
    for (int i = 0; i < chars.length; i++) {
      System.out.print(chars[i]);
      Thread.sleep(25);
    }
    System.out.println();
  }

  public void search(){
    currentScene.getSearch();
  }

  public static void help() {
    System.out.println("\n  These are some commands you can perform: \n" +
            "    -move <direction>-\n" +
            "    -inv <view inventory>-\n" +
//            "    -use <item>-\n" +
            "    -pick up <item>-\n" +
            "    -drop <item>-\n" +
            "    -look/search-\n" +
//            "    -open/close- door-\n" +
            "    -quit\n");
  }

  public void move(String moveDir) throws Exception {
    JSONObject moveSet = (JSONObject) currentScene.getMovement();
    String sceneCheck = (String) moveSet.get(moveDir);
    if (sceneCheck.equals("victory")) {
      win = true;
      gameStatus.win();
    }
    else if (sceneCheck != null) {
      System.out.println("You move to the " + sceneCheck + ".");
      currentScene = gameUniverse.getScene(sceneCheck);
//      System.out.println(currentScene.toString()); // remove when not testing
//      System.out.println(currentScene.getFlavorText());
      newScene = true;
    }
    else {
      System.out.println("You can't go that way...  Please try another cardinal direction.");
    }
  }

  public void look() {
    currentScene.getLook();
  }

  public void hint() {
    JSONObject moves = (JSONObject) currentScene.getMovement();

    System.out.println("Nearby rooms are: ");
    for (Object move : moves.values())
      if (move != "")
        System.out.print("    " + move);
    System.out.println();
//    System.out.println(moves.keySet());
  }


}
