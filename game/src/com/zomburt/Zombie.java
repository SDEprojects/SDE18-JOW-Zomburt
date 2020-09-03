package com.zomburt;

import java.util.Random;

public class Zombie extends Character {

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




//  public static void main(String[] args) {
//    Zombie z = new Zombie();
//
//    z.dropItem();
//    System.out.println(z.getName());
//    System.out.println(z.getHealth());
//    System.out.println(z);
//
//
//  }

}
