package com.zomburt.characters;

import java.util.ArrayList;

public class Player extends Characters{
    public Player(String name, int health) {
        super(name, health);
    }

    public Player(String name, int health, ArrayList<String> inventory){
        super(name, health, inventory);
    }
}
