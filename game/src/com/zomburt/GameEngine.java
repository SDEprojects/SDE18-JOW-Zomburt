package com.zomburt;

import com.zomburt.characters.*;
import com.zomburt.combat.Combat;
import com.zomburt.combat.Weapon;
import com.zomburt.gui.GameApp;
import com.zomburt.utility.GameStatus;
import com.zomburt.utility.Parser;
import jdk.management.resource.internal.inst.ThreadRMHooks;
import org.json.simple.JSONObject;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class GameEngine implements Serializable{
  public static Player player;
  public static Zombie zombie;
  public static Mode mode;
  public static Universe gameUniverse;
  GameStatus gameStatus = new GameStatus();
  public static Scene currentScene;
  Boolean newScene = true;
  Boolean win = false;
  Random rand = new Random();
  public static String realName;

  public GameEngine() throws Exception {
  }

  public void run() throws Exception {
      gameUniverse = new Universe();
      gameStatus.start();
      currentScene = new Scene("parking lot");

      mode = GameApp.getInstance().getModeInput();
      player = PlayerFactory.createPlayer(mode);
      GameApp.getInstance().appendToCurActivity("What is your name?");
      realName = GameApp.getInstance().getInput();
      GameApp.getInstance().appendToCurActivity("\n" + realName + ", ");

      while (win == false) {
        if (newScene) {
          GameApp.getInstance().appendToCurActivity(currentScene.getFlavorText());
          if (currentScene.getFeature().size() > 0) {
            GameApp.getInstance().appendToCurActivity("Somehow inevitably, a shuffling shadow blocks your path.  The gruesome smell of entitlement thickens the air around you and the one thing all Divoc Zombies can still say changes from a mumble to screech as it sees you: \n\nYou can't make me wear a mask!\n\nThere's no time to rush past it. You are already in combat.  You best FIGHT!\n");
          }
          Thread.sleep(200);
        }
        if(currentScene.getFeature().size() > 0){
            zombie = currentScene.getFeature().get(rand.nextInt(currentScene.getFeature().size()));
            //before fighting
            GameApp.getInstance().updateZombie();
            Combat.combat(player, zombie);
            //after fighting
            GameApp.getInstance().updateZombie();
        }
        GameApp.getInstance().appendToCurActivity(" > ");
        String input = GameApp.getInstance().getInput();
        if (input.isEmpty()) {
          newScene = false;
          continue;
        }
        runCommands(input);
        GameApp.getInstance().updateUI();
      }
  }

  public void runCommands(String input) throws Exception {
    newScene = false;
    ArrayList<String> commands = Parser.parse(input.toLowerCase().trim());
    if (commands == null) {
      GameApp.getInstance().appendToCurActivity("Invalid command. For a list of available commands input \" help\"");
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
      GameApp.getInstance().appendToCurActivity(player.getName() + "'s inventory is " +
              player.getInventory().stream().map(e->e.getName()).collect(Collectors.toList()));
    } else if (commands.get(0).contains("hint")) {
      hint();
    } else if(commands.get(0).contains("check")) {
      for (Scene scene : gameUniverse.world.values()) {
        if (scene.getFeature().size() > 0) {
          GameApp.getInstance().appendToCurActivity(scene.getSceneName() + " :" + scene.getFeature().size() + " zombie(s)!\n");
        }
      }
    } else if(commands.get(0).contains("mode")) {
      GameApp.getInstance().appendToCurActivity("The current game mode: " + GameApp.getInstance().getModeInput());
    }
    else if (commands.get(0).contains("fight")) {
        GameApp.getInstance().appendToCurActivity("There is no zombies approaching you, you better move now!\n");
    }
    else {
      GameApp.getInstance().appendToCurActivity(Arrays.toString(commands.toArray()));
    }
  }

  public static void itemHandler(String action, Weapon weapon) {
     String s = "[" + weapon.getName() + ", " + weapon.getDamage() + "]";
    if (action.equals("drop")) {
      if (player.getInventory().contains(weapon)) {
        player.removeInventory(weapon);
        currentScene.addRoomLoot(weapon);
        GameApp.getInstance().appendToCurActivity(player.getName() + "'ve dropped " + s);
        GameApp.getInstance().appendToCurActivity("The room currently contains: " + currentScene.getRoomLoot());
      } else {
        GameApp.getInstance().appendToCurActivity(player.getName() + " doesn't have that item");
      }
      GameApp.getInstance().updateUI();
    }
    if (action.equals("pick up")) {
      if (currentScene.getRoomLoot().contains(weapon)) {
        if (player.getInventory().size() < 3) {
          player.addInventory(weapon);
          currentScene.removeRoomLoot(weapon);
          GameApp.getInstance().appendToCurActivity(player.getName() + "'ve successfully picked up: " + s);
        } else {
          GameApp.getInstance().appendToCurActivity("You can only have maximum of 3 weapons! ");
        }
      } else {
        GameApp.getInstance().appendToCurActivity("That item isn't here");
      }
      GameApp.getInstance().updateUI();
    }
  }

  public void quit() throws FileNotFoundException, InterruptedException {
    System.exit(0);
  }

  public void search(){
    currentScene.getSearch();
  }

  public void help() {
    GameApp.getInstance().appendToCurActivity("\n  These are some commands you can perform: \n" +
        "    -move <direction>-\n" +
        "    -inv <view inventory>-\n" +
        "    -pick up <item>-\n" +
        "    -drop <item>-\n" +
        "    -look/search-\n" +
        "    -check <current scene and # of zombies>- \n" +
        "    -mode <show current game mode>- \n" +
        "    -quit\n");
  }

  public void move(String moveDir) throws Exception {
    JSONObject moveSet = (JSONObject) currentScene.getMovement();
    String sceneCheck = (String) moveSet.get(moveDir);
    if (sceneCheck.equals("victory") && MapFactory.totalNumZombies == 0) {
      GameApp.getInstance().appendToCurActivity("Oh wow.  Did you survive?  I guess you made it then...");
      win = true;
      gameStatus.win();
    } else if (sceneCheck.equals("victory") && MapFactory.totalNumZombies > 0){
      GameApp.getInstance().appendToCurActivity("There are still zombies somewhere out there. You need to go back and kill all the zombies to win!");
    }
    else if (sceneCheck.length() > 0) {
      GameApp.getInstance().appendToCurActivity("You move to the " + sceneCheck +".\n\n");
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
        GameApp.getInstance().appendToCurActivity("\n" + move);
      }
  }

  public static void recordGameResults(){
    PrintWriter writer = null;
    try {
      writer = new PrintWriter(new FileWriter("game/assets/game_results.txt", true));
    } catch (IOException e) {
      e.printStackTrace();
    }
    String result = "won";
    if(player.getHealth()<=0){
      result = "lost";
    }
    LocalDateTime time = LocalDateTime.now();
    writer.append("<Final score for this game @" + time + ">" + "\n");
    writer.append(realName + ": " + GameApp.getInstance().getModeInput() + " Mode, " + player.getScore() + " points, " + result + " the game \n");
    writer.println();
    writer.close();
  }

}
