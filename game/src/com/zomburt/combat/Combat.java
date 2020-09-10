package com.zomburt.combat;

import com.zomburt.Character;
import com.zomburt.GameStatus;
import com.zomburt.Parser;
import com.zomburt.Zombie;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Combat {

  public static void combat(Character player, Zombie zombie) throws FileNotFoundException, InterruptedException, Exception {
    Scanner fn = new Scanner(System.in);
    System.out.println();
//    System.out.println("You have encountered a zombie! Prepare your self");
    while (player.getHealth() > 0 && zombie.getHealth() > 0) {
      System.out.print(" > ");
      String input = fn.nextLine();
      System.out.println();
      if (input.isEmpty()) {
        continue;
      }
      combatCommands(input, player, zombie);
    }
    if(zombie.getHealth() <= 0)
      System.out.println("Congratulations you've beaten the zombie and are able to progress.");
  }

  public static void combatCommands(String input, Character player, Zombie zombie) throws Exception {
    ArrayList<String> commands = Parser.parse(input.toLowerCase().trim());
    if (commands == null)
      System.out.println("That's not a valid command. For a list of available commands input \" help\"");
    else if (commands.get(0).contains("help"))
      help();
    else if (commands.get(0).contains("inv"))
      System.out.println(player.getName()+ "'s inventory is " + player.getInventory());
    else if (commands.get(0).contains("quit") || commands.get(0).contains("exit"))
      quit();
    else if (commands.get(0).contains("fight"))
      fight(player, zombie);
    else
      System.out.println("You are in combat that isn't a valid command");
  }

  public static void fight(Character player, Zombie zombie) throws FileNotFoundException, InterruptedException {
    int playerDamage = new Random().nextInt(50) + 1;
    int ZombieDamage = new Random().nextInt(50) + 1;
    if(player.getInventory().contains("NERF BLASTER")) {
      playerDamage += 2;
      if (player.getInventory().contains("IMPROVED NERF DART"))
        playerDamage += 2;
    }

    if (player.getHealth() > 0 && zombie.getHealth() > 0) {
      System.out.println("You attack.....");
      zombie.loseHealth(playerDamage);
      System.out.println("Zombie sustained damage of: " +  ZombieDamage);
      if(zombie.getHealth() < 0)
        zombie.setHealth(0);
      System.out.println("Zombie current Health is: " + zombie.getHealth());
    }

    if (player.getHealth() > 0 && zombie.getHealth() > 0) {
      System.out.println("Zombie attacks.....");
      player.loseHealth(playerDamage);
      System.out.println("You sustained damage of: " + playerDamage);
      System.out.println("You current Health is: " + player.getHealth());
    }
    if (player.getHealth() <= 0)
      quit();

    System.out.println("Your health: "  + player.getHealth() + "       Zombie's health: " + zombie.getHealth() + "   ");
  }

//  public void runaway(){
//
//  }

  public static void quit() throws FileNotFoundException, InterruptedException {
    GameStatus loser = new GameStatus();
    loser.lose();
    System.exit(0);
  }

  public static void help() {
    System.out.println("These are some commands you can perform: \n" +
            "-inv <view inventory>-\n" +
//            "-use <item>-\n" +
            "-fight- \n" +
//            "run away \n" +
            "-quit-");
  }


}
