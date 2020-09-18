package com.zomburt.characters;

import com.zomburt.combat.Weapon;
import com.zomburt.gui.GameApp;

import java.io.Serializable;
import java.util.ArrayList;

public class Characters implements Serializable {
    private String name;
    private int health = 50;
    private ArrayList<Weapon> inventory = new ArrayList<Weapon>();
    public Characters(){}
    public Characters (int health) {
        setHealth(health);
    }
    public Characters(String name, int health) {
        setName(name);
        setHealth(health);
    }

    public Characters(String name, int health, ArrayList<Weapon> inventory) {
        setName(name);
        setHealth(health);
        setInventory(inventory);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void loseHealth(int damage) {
        this.health -= damage;
    }

    public void updateHealth(int amount) {
        this.health += amount;
    }

    public ArrayList<Weapon> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Weapon> inventory) {this.inventory = inventory;}

    public void addInventory(Weapon item) {
        if (this.inventory.size() < 3 ) {
            this.inventory.add(item);
        }
        else {
            GameApp.getInstance().appendToCurActivity("You can only add maximum of 3 weapon!\n");
        }
    }

    public void removeInventory(Weapon item) {
        this.inventory.remove(item);
    }

    @Override
    public String toString() {
        return "com.zomburt.characters.Character{" +
                "name='" + name + '\'' +
                ", health=" + health +
             //   ", inventory=" + inventory +
                '}';
    }
}
