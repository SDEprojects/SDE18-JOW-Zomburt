package com.zomburt;

public class Zombie extends Character{
//  private inventory inventory = new inventory();

  public Zombie(){
    name = "Zombie";
//    setHealth();
    inventory.add("Hat");
  }

  public Zombie(String name){

  }

  public void fight(){
    int fightHealth = health;
    int damage = 0;
    if(health > 0) {
      fightHealth -= damage;
    }
  }


  public static void main(String[] args) {
    Zombie z = new Zombie("com.zomburt.Zombie");
    z.inventory.add("Hat1");
    System.out.println(z.name);
    System.out.println(z.health);
  }

}
