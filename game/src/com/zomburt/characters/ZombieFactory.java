package com.zomburt.characters;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ZombieFactory {

    public static Zombie createZombie(String level){
        Zombie zombie = null;
        String[] names = {"Biter", "Cold Body", "Creeper", "Dead one", "Floater", "Geek", "Lamebrain", "Lurker", "Monster",
                "Roamer", "Rotter", "Skin Eater", "Walker"};
        int rand1 = ThreadLocalRandom.current().nextInt(names.length);
        int[] healthValues = {30, 60};
        ArrayList<String> inventory = new ArrayList<>();
        String[] weapons = {"Knife", "Bat", "Club", "Brick"};
        int rand2 = ThreadLocalRandom.current().nextInt(weapons.length);
        inventory.add(weapons[rand2]);

        switch(level.toUpperCase()) {
            case "EASY":
                zombie = new Zombie(names[rand1], healthValues[0]);
                break;
            case "HARD":
                zombie = new Zombie(names[rand1], healthValues[1], inventory);
        }

        return zombie;
    }

}
