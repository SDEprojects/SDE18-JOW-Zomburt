package com.zomburt.combat;

import com.zomburt.characters.Characters;
import com.zomburt.characters.Player;
import com.zomburt.characters.Zombie;
import com.zomburt.gui.GameApp;
import com.zomburt.utility.GameStatus;
import com.zomburt.utility.Parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Combat {

  public static void combat(Player player, Zombie zombie) throws FileNotFoundException, InterruptedException, Exception {
//    GameApp.getInstance().appendToCurActivity("You have encountered a zombie! Prepare yourself");
    int score = player.getScore();
    while (player.getHealth() > 0 && zombie.getHealth() > 0) {
      GameApp.getInstance().appendToCurActivity(" > ");
      String input = GameApp.getInstance().getInput();
      if (input.isEmpty()) {
        continue;
      }
      combatCommands(input, player, zombie);
    }
    if(zombie.getHealth() <= 0)
      score+=10;
      player.setScore(score);
      GameApp.getInstance().appendToCurActivity("Congratulations! You've killed the " + zombie.getName() + " and are able to progress.");
  }

  public static void combatCommands(String input, Player player, Zombie zombie) throws Exception {
    ArrayList<String> commands = Parser.parse(input.toLowerCase().trim());
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
    int playerDamage = new Random().nextInt(20) + 1;
    int ZombieDamage = new Random().nextInt(20) + 1;
    if(player.getInventory().contains("NERF BLASTER")) {
      playerDamage += 2;
      if (player.getInventory().contains("IMPROVED NERF DART"))
        playerDamage += 2;
    }

    if (player.getHealth() > 0 && zombie.getHealth() > 0) {
      GameApp.getInstance().appendToCurActivity(player.getName() + " attack.....");
      zombie.loseHealth(playerDamage);
      GameApp.getInstance().appendToCurActivity(zombie.getName() + " sustained damage of: " + ZombieDamage);
      if(zombie.getHealth() < 0)
        zombie.setHealth(0);
      GameApp.getInstance().appendToCurActivity(zombie.getName() + " current Health is: " + zombie.getHealth());
    }

    if (player.getHealth() > 0 && zombie.getHealth() > 0) {
      GameApp.getInstance().appendToCurActivity(zombie.getName() + " attacks.....");
      player.loseHealth(playerDamage);
      GameApp.getInstance().appendToCurActivity(player.getName() + " sustained damage of: " + playerDamage);
      GameApp.getInstance().appendToCurActivity(player.getName() + " current Health is: " + player.getHealth());
    }
    if (player.getHealth() <= 0)
      quit();

    GameApp.getInstance().appendToCurActivity(player.getName()+"'s health: \n" + player.getHealth() + zombie.getName()+"'s health: " + zombie.getHealth() + "   ");
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
