package com.zomburt.combat;

import com.zomburt.GameEngine;
import com.zomburt.GenerateMap;
import com.zomburt.characters.Player;
import com.zomburt.characters.Zombie;
import com.zomburt.gui.GameApp;
import com.zomburt.utility.GameStatus;
import com.zomburt.utility.Parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Combat {

  public static void combat(Player player, Zombie zombie) throws FileNotFoundException, InterruptedException, Exception {
//    GameApp.getInstance().appendToCurActivity("You have encountered a zombie! Prepare yourself");
    int score = player.getScore();
    GameApp.getInstance().updateUI();
    int zombieValue = zombie.getHealth();
    while (player.getHealth() > 0 && zombie.getHealth() > 0) {
      GameApp.getInstance().appendToCurActivity(" > ");
      String input = GameApp.getInstance().getInput();
      if (input.isEmpty()) {
        continue;
      }
      combatCommands(input, player, zombie);
    }
    if(zombie.getHealth() <= 0)
      score += zombieValue;
      player.setScore(score);
      GenerateMap.totalNumZombies -= 1;
      GameEngine.currentScene.removeFeature(zombie);
      GameApp.getInstance().updateUI();
      GameApp.getInstance().appendToCurActivity("Congratulations! You've killed the " + zombie.getName() + " and are able to progress.");
  }

  public static void combatCommands(String input, Player player, Zombie zombie) throws Exception {
    ArrayList<String> commands = Parser.parse(input.toLowerCase().trim());
    GameApp.getInstance().updateUI();
    if (commands == null)
        GameApp.getInstance().appendToCurActivity("That's not a valid command. For a list of available commands input \" help\"");
    else if (commands.get(0).contains("help"))
      help();
    else if (commands.get(0).contains("inv"))
      GameApp.getInstance().appendToCurActivity(player.getName() + "'s inventory is " + player.getInventory());
    else if (commands.get(0).contains("quit") || commands.get(0).contains("exit"))
      quit();
    else if (commands.get(0).contains("fight"))
      fight(player, zombie);
    else
      GameApp.getInstance().appendToCurActivity("You are in combat that isn't a valid command");
  }

  public static void fight(Player player, Zombie zombie) throws FileNotFoundException, InterruptedException {
    int playerDamage = 20; // default damage
    // iterate throught the weapon to get the weapon's maximum damage
    if (player.getInventory().size() > 0) {
      for (Weapon weapon : player.getInventory()) {
        if (weapon.getDamage() > playerDamage) {
          playerDamage = weapon.getDamage();
        }
      }
    }
    int zombieDamage = zombie.getHealth(); // this way to correlated the damage to the mode

    if (player.getHealth() > 0 && zombie.getHealth() > 0) {
      GameApp.getInstance().appendToCurActivity(player.getName() + " attack.....");
      zombie.loseHealth(playerDamage);
      GameApp.getInstance().appendToCurActivity(zombie.getName() + " sustained damage of: " + zombieDamage);
      if(zombie.getHealth() < 0) {
        zombie.setHealth(0);
        GameApp.getInstance().updateUI();
      }
      GameApp.getInstance().appendToCurActivity(zombie.getName() + " current Health is: " + zombie.getHealth());
    }

    if (player.getHealth() > 0 && zombie.getHealth() > 0) {
      GameApp.getInstance().appendToCurActivity(zombie.getName() + " attacks.....");
      player.loseHealth(zombieDamage);
      GameApp.getInstance().appendToCurActivity(player.getName() + " sustained damage of: " + playerDamage);
      GameApp.getInstance().appendToCurActivity(player.getName() + " current Health is: " + player.getHealth());
    }
    if (player.getHealth() <= 0) {
      quit();
    }

    GameApp.getInstance().appendToCurActivity(player.getName()+"'s health: " + player.getHealth() +"\n"+ zombie.getName()+"'s health: " + zombie.getHealth() + "   ");
  }

//  public void runaway(){
//
//  }

  public static void quit() throws FileNotFoundException, InterruptedException {
    GameStatus loser = new GameStatus();
    loser.lose();
   // System.exit(0);
  }

  public static void help() {
    GameApp.getInstance().appendToCurActivity("These are some commands you can perform: \n" +
            "-inv <view inventory>-\n" +
//            "-use <item>-\n" +
            "-fight- \n" +
//            "run away \n" +
            "-quit-");
  }


}
