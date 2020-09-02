package com.zomburt;

public class Zombie extends Character{


  public Zombie() {
    health = getHealth();
    name = "Zombie";
    inventory.add("Hat");
  }

//  public int getHealth() {
//    return health;
//  }

  public void setHealth(int health){
    this.health = getHealth();
  }

  public static void main(String[] args) {
    Zombie z = new Zombie();

    System.out.println(z.getHealth());
    System.out.println(z);
  }

}
