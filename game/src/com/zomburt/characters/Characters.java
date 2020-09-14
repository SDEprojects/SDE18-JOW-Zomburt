package com.zomburt.characters;

import java.util.ArrayList;

public class Characters {
    private String name;
    private int health = 50;
    private ArrayList<String> inventory = new ArrayList<String>();

    public Characters(String name, int health) {
        setName(name);
        setHealth(health);
    }

    public Characters(String name, int health, ArrayList<String> inventory) {
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

    public void addHealth(int amount) {
        this.health += amount;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<String> inventory) {this.inventory = inventory;}

    public void addInventory(String item) {
        this.inventory.add(item);
    }

    public void removeInventory(String item) {
        this.inventory.remove(item);
    }

    @Override
    public String toString() {
        return "com.zomburt.characters.Character{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", inventory=" + inventory +
                '}';
    }
}
