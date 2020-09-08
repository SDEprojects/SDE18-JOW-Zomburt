package com.zomburt.combat;

import com.zomburt.*;
import com.zomburt.Character;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Combat {
  public void combatSetup(){
    System.out.println();
  }

  public void combat(Character player, Zombie zombie) throws FileNotFoundException, InterruptedException, Exception {
    Scanner in = new Scanner(System.in);
    System.out.println("What is your name?");
    player = new Character(in.nextLine());

    System.out.println();

    while (player.getHealth() > 0 || zombie.getHealth() >0) {
      Random playerDamage = new Random();
      player.setHealth(); = player.getHealth()-playerDamage;
      System.out.println("you sustained damage of: " + playerDamage);
      System.out.println("You current Health is: " + player.getHealth());

      Random ZombieDamage = new Random();
      zombie.setHealth() = zombie.getHealth()- Zombie.getHealth();
      System.out.println("you sustained damage of: " + playerDamage);
      System.out.println("You current Health is: " + player.getHealth());

      System.out.print("Your Health: "  + player.getHealth() + " Zombie: " + zombie.getHealth() + "   ");
      String input = in.nextLine();
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
    else
      System.out.println(Arrays.toString(commands.toArray()));
  }

  public void fight(){
    Random characterHit = new Random();
    if (characterHit > 50) {
      int characterDamage = (int)(Math.random()*50)+1;

    }

  }

  public void runaway(){

  }

  public void quit() throws FileNotFoundException, InterruptedException {
    GameStatus.lose();
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

  public static void main(String[] args) throws Exception {
    combat(new Character("Chris"), new Zombie());
  }
}
