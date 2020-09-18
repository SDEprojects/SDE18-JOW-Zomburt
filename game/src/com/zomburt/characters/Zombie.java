package com.zomburt.characters;

import com.zomburt.combat.Weapon;

import java.util.ArrayList;

public class Zombie extends Characters {
  public Zombie() {
    super();
  }
  public Zombie(String name, int health) {
    super(name, health);
  }

  public Zombie(String name, int health, ArrayList<Weapon> inventory){
    super(name, health, inventory);
  }

  @Override
  public String toString() {
    return super.toString();
  }
}


