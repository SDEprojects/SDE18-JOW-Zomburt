package com.zomburt.combat;

public enum Weapon {
    NERF_BLASTER ("Nerf Blaster", 10),
    HOOAh_BAR("Hooah! bar", 10),
    Gas("Gas", 0),
    Lighter("lighter", 0),
    GasLighter("Gas and Lighter", 100),
    M16("M16", 20),
    HAND_GRENADE("Hand Grenade", 40),
    AMMO("Ammo", 0),
    M16_AMMO("M16 and Ammo", 50),
    KNIFE("Butcher Knife", 20),
    FORK("Fork", 5);

    private final String name;
    private final int damage;

    private Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return "Weapon{" +
            "name='" + name + '\'' +
            ", damage=" + damage +
            '}';
    }
}

