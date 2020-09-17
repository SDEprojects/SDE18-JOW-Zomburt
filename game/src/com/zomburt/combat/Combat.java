package com.zomburt.combat;

import com.zomburt.GameEngine;
import com.zomburt.MapFactory;
import com.zomburt.characters.Player;
import com.zomburt.characters.Zombie;
import com.zomburt.gui.GameApp;
import com.zomburt.utility.GameStatus;
import com.zomburt.utility.Parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Combat {

    public static void combat(Player player, Zombie zombie) throws FileNotFoundException, InterruptedException, Exception {
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
        if (zombie.getHealth() <= 0)
            score += zombieValue;
        player.setScore(score);
        MapFactory.totalNumZombies -= 1;
        GameEngine.currentScene.removeFeature(zombie);

        GameApp.getInstance().appendToCurActivity("Congratulations! You've killed the " + zombie.getName() + " and are able to progress.");
    }

    public static void combatCommands(String input, Player player, Zombie zombie) throws Exception {
        ArrayList<String> commands = Parser.parse(input.toLowerCase().trim());
        GameApp.getInstance().updateUI();
        if (commands == null)
            GameApp.getInstance().appendToCurActivity("Invalid command! For a list of available commands input \" help\"");
        else if (commands.get(0).contains("help"))
            help();
        else if (commands.get(0).contains("inv"))
            GameApp.getInstance().appendToCurActivity(player.getName() + "'s inventory is " + player.getInventory());
        else if (commands.get(0).contains("quit") || commands.get(0).contains("exit"))
            quit();
        else if (commands.get(0).contains("fight"))
            fight(player, zombie);
        else if (commands.get(0).contains("pick up")) {
            String weaponName = commands.get(1);
            boolean isValidWeapon = false;
            if (!weaponName.isEmpty()) {
                for (Weapon weapon: GameEngine.currentScene.getRoomLoot()) {
                if (weapon.getName().toLowerCase().equals(weaponName.toLowerCase())) {
                    GameEngine.itemHandler(commands.get(0), weapon );
                    isValidWeapon = true;
                    break;
                }
             }
            }
            if (!isValidWeapon) {
                GameApp.getInstance().appendToCurActivity("Invalid command! Try again!");
            }

        }
        else {
            GameApp.getInstance().appendToCurActivity("Invalid command! There is a <zombie> here and you have to fight!");
        }
    }

    public static void fight(Player player, Zombie zombie) throws FileNotFoundException, InterruptedException {
        int playerDamage = 20;
        int zombieDamage = zombie.getHealth();
        if (zombie.getInventory().size() > 0) {
            zombieDamage += zombie.getInventory().get(0).getDamage();
        }
        if (player.getInventory().size() > 0) {
            for (Weapon weapon : player.getInventory()) {
                if (weapon.getDamage() > playerDamage) {
                    playerDamage = weapon.getDamage();
                }
            }
        }
        if (player.getHealth() > 0 && zombie.getHealth() > 0) {
            GameApp.getInstance().appendToCurActivity(player.getName() + " attack.....");
            zombie.loseHealth(playerDamage);
            GameApp.getInstance().appendToCurActivity(zombie.getName() + " sustained damage of: " + playerDamage);
            if (zombie.getHealth() < 0) {
                if (zombie.getHealth() < 0) {
                    zombie.setHealth(0);
                    if (zombie.getInventory().size() > 0) {
                        player.addInventory(zombie.getInventory().get(0));
                        zombie.getInventory().clear();
                        GameApp.getInstance().updateZombie();
                    }
                }
                GameApp.getInstance().updateUI();
            }
        }

        if (player.getHealth() > 0 && zombie.getHealth() > 0) {
            GameApp.getInstance().appendToCurActivity(zombie.getName() + " attacks.....");
            player.loseHealth(zombieDamage);
            GameApp.getInstance().appendToCurActivity(player.getName() + " sustained damage of: " + zombieDamage);
        }
        if (player.getHealth() <= 0) {
            if (player.getHealth() < 0) {
                player.setHealth(0);
            }
            quit();
        }
        GameApp.getInstance().appendToCurActivity(player.getName() + "'s health: " + player.getHealth() + "\n" + zombie.getName() + "'s health: " + zombie.getHealth() + "   ");
        GameApp.getInstance().updateZombie();
        GameApp.getInstance().updateUI();
    }

//  public void runaway(){
//
//  }

    public static void quit() throws FileNotFoundException, InterruptedException {
        GameStatus loser = new GameStatus();
        loser.lose();
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
