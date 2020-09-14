package com.zomburt.characters;

import java.util.ArrayList;
import java.util.Random;

public class Zombie extends Characters {

  public Zombie(String name, int health) {
    super(name, health);
  }

  public Zombie(String name, int health, ArrayList<String> inventory){
    super(name, health, inventory);
  }

  public String dropItem(){
    Random rand = new Random();
    int n = rand.nextInt(getInventory().size());
    String drop = getInventory().get(n);
    removeInventory(getInventory().get(n));
    return drop;
  }
}


