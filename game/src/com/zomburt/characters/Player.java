package com.zomburt.characters;

import com.zomburt.combat.Weapon;

import java.io.Serializable;
import java.util.ArrayList;

public class Player extends Characters implements Serializable {
    private int score;

    public Player(String name, int health, int score) {
        super(name, health);
        setScore(score);
    }

    public Player(String name, int health, int score, ArrayList<Weapon> inventory){
        super(name, health, inventory);
        setScore(score);
    }

    public int getScore() {return score;}

    public void setScore(int score) {
        this.score = score;
    }
}
