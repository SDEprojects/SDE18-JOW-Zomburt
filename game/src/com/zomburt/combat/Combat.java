package com.zomburt.combat;

import com.zomburt.*;
import com.zomburt.Character;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Combat {

  private Character player;
  private Zombie zombie;


  public void combatSetup(Character player, Zombie zombie){
    this.player = player;
    this.zombie = zombie;

    System.out.println();
  }

  public void combat(Character player, Zombie zombie) throws FileNotFoundException, InterruptedException, Exception {
    combatSetup(player, zombie);
    Scanner fn = new Scanner(System.in);
    while (player.getHealth() > 0 || zombie.getHealth() >0) {
      String input = fn.nextLine();
      if (input.isEmpty()) {
        continue;
      }
      combatCommands(input);
    }
  }

  public void combatCommands(String input) throws Exception {
    ArrayList<String> commands = Parser.parse(input.toLowerCase().trim());
    if(commands == null)
      System.out.println("That's not a valid command. For a list of available commands input \" help\"");
    else if(commands.get(0).contains("help"))
      help();
    else if(commands.get(0).contains("quit") || commands.get(0).contains("exit"))
      quit();
    else if(commands.get(0).contains("fight"))
      fight();
    else
      System.out.println(Arrays.toString(commands.toArray()));
  }

  public void fight() {
    Random playerDamage = new Random();
    Random ZombieDamage = new Random();

    System.out.println("You attack.....");
    zombie.loseHealth(playerDamage.nextInt(50) + 1);
    System.out.println("Zombie sustained damage of: " + ZombieDamage);
    System.out.println("Zombie current Health is: " + zombie.getHealth());

    System.out.println("Zombie attacks.....");
    player.loseHealth(playerDamage.nextInt(50) + 1);
    System.out.println("You sustained damage of: " + playerDamage);
    System.out.println("You current Health is: " + player.getHealth());

    System.out.print("Your Health: "  + player.getHealth() + " Zombie: " + zombie.getHealth() + "   ");
  }

  public void runaway(){

  }

  public void quit() throws FileNotFoundException, InterruptedException {
    GameStatus loser = new GameStatus();
    loser.lose();
    System.exit(0);
  }

  public static void help() {
    System.out.println("These are some commands you can perform: \n" +
            "-inv <view inventory>-" +
            "-use <item>-\n" +
            "fight \n" +
            "Run Away \n" +
            "-quit");
  }


}
