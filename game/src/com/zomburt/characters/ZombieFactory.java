package com.zomburt.characters;

import com.zomburt.Mode;
import com.zomburt.combat.Weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class ZombieFactory {

    public static Zombie createZombie(Mode mode){
        Zombie zombie = null;
        int rand1 = ThreadLocalRandom.current().nextInt(ZombieTypes.values().length);
        int rand2 = ThreadLocalRandom.current().nextInt(Weapon.values().length);
        ZombieTypes randomZombie = ZombieTypes.values()[rand1];
        Weapon randomWeapon = Weapon.values()[rand2];
        ArrayList<Weapon> inventory = new ArrayList<Weapon>(Arrays.asList(randomWeapon));
        switch(mode) {
            case EASY:
                zombie = new Zombie(randomZombie.getName(), randomZombie.getHealth());
                break;
            case HARD:
                zombie = new Zombie(randomZombie.getName() , randomZombie.getHealth(), inventory);
                for(Weapon weapon : inventory) {
                    zombie.updateHealth(weapon.getDamage());
                }
                break;
        }

        return zombie;
    }

}
