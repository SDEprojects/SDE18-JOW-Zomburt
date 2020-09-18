package com.zomburt.characters;

import com.zomburt.Mode;
import com.zomburt.combat.Weapon;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ZombieFactoryTest {

    ArrayList<Weapon> zombieInventory = new ArrayList<>();


    @Test
    public void testCreateZombie_EASY_positive() {
        Zombie zombie1 = ZombieFactory.createZombie(Mode.EASY);
        assertTrue(Arrays.stream(ZombieTypes.values()).map(e -> e.getName()).anyMatch(zombie1.getName()::equals));
        assertTrue(Arrays.stream(ZombieTypes.values()).map(e -> Integer.toString(e.getHealth())).anyMatch(Integer.toString(zombie1.getHealth())::equals));
        System.out.println(zombie1.getName() + ", " + zombie1.getHealth());
    }

    @Test
    public void testCreateZombie_HARD_positive() {
        Zombie zombie2 = ZombieFactory.createZombie(Mode.HARD);
        assertTrue(Arrays.stream(ZombieTypes.values()).map(e -> e.getName()).anyMatch(zombie2.getName()::equals));
        assertTrue(Arrays.stream(ZombieTypes.values()).map(e -> Integer.toString(e.getHealth()*2)).anyMatch(Integer.toString(zombie2.getHealth())::equals));
        assertTrue(Arrays.stream(Weapon.values()).anyMatch(zombie2.getInventory().get(0)::equals));
        assertEquals(1, zombie2.getInventory().size());
        System.out.println(zombie2.getName() + ", " + zombie2.getHealth() + ", " + zombie2.getInventory().stream().collect(Collectors.toList()));
    }

    @Test
    public void testCreateZombie_EASY_negative() {
        assertEquals(null, ZombieFactory.createZombie(Mode.MEDIAN));
    }

    @Test
    public void testCreateZombie_HARD_negative(){
        assertEquals(null, ZombieFactory.createZombie(Mode.MEDIAN));
    }

}