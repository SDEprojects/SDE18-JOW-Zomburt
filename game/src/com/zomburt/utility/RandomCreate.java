package com.zomburt.utility;

import com.zomburt.Mode;
import com.zomburt.characters.ZombieTypes;
import com.zomburt.combat.Weapon;

import java.util.Random;

public class RandomCreate {
    static int upperBound = Weapon.values().length;
    static int lowerBound = ZombieTypes.values().length;

    public static int randNum(Mode mode) {
        int finalNum = 0;
        // easy to create larger number
        if (mode == Mode.HARD) {
            Random rand = new Random();
            int rand1 = rand.nextInt(upperBound);
            if (rand1 > 0) {
                finalNum = rand.nextInt(lowerBound);
            }
        }
        // median to create larger number
        else if (mode == Mode.MEDIAN) {
            Random rand = new Random();
            int rand1 = rand.nextInt(upperBound/2);
            if (rand1 > 0) {
                finalNum = rand.nextInt(lowerBound/2);
            }
        }
        // hard to create larger number
        else if (mode == Mode.EASY) {
            Random rand = new Random();
            int rand1 = rand.nextInt(4);
            if (rand1 > 0) {
                finalNum = rand.nextInt(4);
            }
        }
        return finalNum;
    }
}
