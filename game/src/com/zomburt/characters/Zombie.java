package com.zomburt.characters;

import com.zomburt.combat.Weapon;

import java.util.ArrayList;
import java.util.Random;

public class Zombie extends Characters {

  public Zombie(String name, int health) {
    super(name, health);
  }

  public Zombie(String name, int health, ArrayList<Weapon> inventory){
    super(name, health, inventory);
  }
}


