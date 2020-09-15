package com.zomburt;

import com.zomburt.characters.Characters;
import com.zomburt.characters.ZombieFactory;
import com.zomburt.combat.Combat;
import com.zomburt.combat.Weapon;
import com.zomburt.gui.GameApp;
import com.zomburt.utility.GameStatus;
import com.zomburt.utility.Parser;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class GameEngine {
  public static Characters player;
  GameStatus gameStatus = new GameStatus();
  Scene currentScene;
  Boolean newScene = true;
  Boolean win = false;
  Universe gameUniverse = new Universe();

  public GameEngine() throws Exception {
  }

  public void run() throws Exception {
      gameStatus.start();
      currentScene = new Scene("parking lot");

      GameApp.getInstance().appendToCurActivity("What is your name?");
      player = new Characters(GameApp.getInstance().getInput(), 50);
      GameApp.getInstance().appendToCurActivity("\n" + player.getName() + ", ");
//      GameApp.getInstance().appendToCurActivity("Which level do you want to play: Easy or Hard?");
//      gameUniverse = new Universe(GameApp.getInstance().getInput());
      while (win == false) {
        if (newScene)
          GameApp.getInstance().appendToCurActivity(currentScene.getFlavorText());
          Thread.sleep(200);
        if (currentScene.getFeature().contains("zombie"))
          Combat.combat(player, ZombieFactory.createZombie(Mode.EASY));
        GameApp.getInstance().appendToCurActivity(" > ");
        String input = GameApp.getInstance().getInput();

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
    if (commands == null) {
      GameApp.getInstance().appendToCurActivity("That's not a valid command. For a list of available commands input \" help\"");
    } else if (commands.get(0).contains("move")) {
      try {
        move(commands.get(1));
      } catch (Exception e) {
        JSONObject dirCheck = (JSONObject) currentScene.getMovement();
        try {
          dirCheck.get(commands.get(1));
          if (dirCheck.get(commands.get(1)).equals("")) {
            GameApp.getInstance().appendToCurActivity("Not an available direction. Try again.");
          }
        } catch (Exception e2) {
          GameApp.getInstance().appendToCurActivity("Movement commands must be two recognized words in length... Pick a cardinal direction!");
        }
      }
    } else if (commands.get(0).contains("help"))
      help();
    else if (commands.get(0).contains("quit") || commands.get(0).contains("exit"))
      quit();
    else if (commands.get(0).contains("drop") || commands.get(0).contains("pick up")) {
      if (commands.size() > 1) {
        Weapon item = null;
        for (Weapon weapon : Weapon.values()) {
          if (weapon.getName().toUpperCase().equals(commands.get(1).toUpperCase())) {
            item = weapon;
          }
        }
        itemHandler(commands.get(0), item);
      }
    } else if (commands.get(0).contains("search"))
      search();
    else if (commands.get(0).contains("look"))
      look();
    else if (commands.get(0).contains("inv")) {
      GameApp.getInstance().appendToCurActivity(player.getName() + "'s inventory is " + player.getInventory());
    } else if (commands.get(0).contains("hint")) {
      hint();
    } else {
      GameApp.getInstance().appendToCurActivity(Arrays.toString(commands.toArray()));
    }
  }

  public void itemHandler(String action, Weapon s) {
    if (action.equals("drop")) {
      if (player.getInventory().contains(s)) {
        player.removeInventory(s);
        currentScene.addRoomLoot(s);
        GameApp.getInstance().appendToCurActivity("You've dropped " + s);
        GameApp.getInstance().appendToCurActivity("The room currently contains: " + currentScene.getRoomLoot());
      } else {
        GameApp.getInstance().appendToCurActivity("You don't have that item");
      }
    }
    if (action.equals("pick up")) {
      if (currentScene.getRoomLoot().contains(s)) {
        player.addInventory(s);
        currentScene.removeRoomLoot(s);
        GameApp.getInstance().appendToCurActivity("You've successfully picked up: " + s);
        GameApp.getInstance().appendToCurActivity("HINT: type 'inv' to see your inventory");
        GameApp.getInstance().appendToCurActivity("The room currently contains: " + currentScene.getRoomLoot());
      } else {
        GameApp.getInstance().appendToCurActivity("That item isn't here");
      }
    }
  }

  public void quit() throws FileNotFoundException, InterruptedException {
    gameStatus.lose();
//    System.exit(0);
  }

  public void search(){
    currentScene.getSearch();
  }

  public static void help() {
    GameApp.getInstance().appendToCurActivity("\n  These are some commands you can perform: \n" +
        "    -move <direction>-\n" +
        "    -inv <view inventory>-\n" +
        "    -pick up <item>-\n" +
        "    -drop <item>-\n" +
        "    -look/search-\n" +
        "    -quit\n");
  }

  public void move(String moveDir) throws Exception {
    JSONObject moveSet = (JSONObject) currentScene.getMovement();
    String sceneCheck = (String) moveSet.get(moveDir);
    if (sceneCheck.equals("victory")) {
      GameApp.getInstance().appendToCurActivity("Oh wow.  Did you survive?  I guess you make it out of the store then...");
      win = true;
      gameStatus.win();
    }
    else if (sceneCheck.length() > 0) {
      GameApp.getInstance().appendToCurActivity("You move to the " + sceneCheck);
      currentScene = gameUniverse.getScene(sceneCheck);
      newScene = true;
    }
    else {
      GameApp.getInstance().appendToCurActivity("You can't go that way...  Please try another cardinal direction.");
    }
  }

  public void look() {
    currentScene.getLook();
  }

  public void hint() {
    JSONObject moves = (JSONObject) currentScene.getMovement();
    GameApp.getInstance().appendToCurActivity("Nearby rooms are: ");
    for (Object move : moves.values())
      if (move != "o") {
        GameApp.getInstance().appendToCurActivity("    " + move);
      }
    GameApp.getInstance().appendToCurActivity("\n");
  }
}
