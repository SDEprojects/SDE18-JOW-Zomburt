package com.zomburt.characters;

public enum ZombieTypes {
    BITER("Biter", 10),
    COLD_BODY("Cold Body", 15),
    CREEPER("Creep", 15),
    DEAD_ONE("Dead One", 10),
    FLOATER("Floater", 12),
    GEEK("Geek", 15),
    LAMEBRAIN("Lamebrain", 5),
    LURKER("Lurker", 20),
    MONSTER("Monster",40),
    ROAMER("Roamer", 5),
    ROTTER("Rotter", 10),
    SKIN_EATER("Skin Eater", 25),
    WALKER("Walker", 30);

    private final String name;
    private final int health;

    private ZombieTypes(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {
        return "ZombieTypes{" +
            "name='" + name + '\'' +
            ", health=" + health +
            '}';
    }
}
