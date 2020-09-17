package com.zomburt.characters;

import com.zomburt.Mode;
import com.zomburt.combat.Weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerFactory {
    public static Player createPlayer(Mode mode){
        Player player = null;
        int rand2 = ThreadLocalRandom.current().nextInt(Weapon.values().length);
        Weapon randomWeapon = Weapon.values()[rand2];
        ArrayList<Weapon> inventory = new ArrayList<Weapon>(Arrays.asList(randomWeapon));
        switch(mode) {
            case EASY:
                player = new Player("<Hunter>", 100, 0, inventory);
                break;
            case HARD:
                player = new Player("<Master Hunter>", 80, 0);
                break;
        }
        return player;
    }
}
