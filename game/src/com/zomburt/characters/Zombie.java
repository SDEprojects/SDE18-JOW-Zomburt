package com.zomburt.characters;

import java.util.Random;

public class Zombie extends GameCharacter {

  public Zombie() {
    super("Zombie");
    addInventory("Zombie Teeth");
  }

  public String dropItem(){
    Random rand = new Random();
    int n = rand.nextInt(getInventory().size());
    String drop = getInventory().get(n);
    removeInventory(getInventory().get(n));
    return drop;
  }
}


