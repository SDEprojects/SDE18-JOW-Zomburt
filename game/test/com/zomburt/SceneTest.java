package com.zomburt;

import com.zomburt.combat.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

class SceneTest {
    List<Weapon> weaponList = new ArrayList<>();
    List<Weapon> roomLoot = new ArrayList<>();
    public Scene currentScene;
    {
        try {
            currentScene = new Scene("pharmacy");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @BeforeEach
    void setUp() {
        weaponList.addAll(currentScene.getRoomLoot());
        weaponList.add(Weapon.AMMO);
        weaponList.add(Weapon.HAND_GRENADE);
        weaponList.add(Weapon.Gas);
        currentScene.addRoomLoot(Weapon.AMMO);
        currentScene.addRoomLoot(Weapon.HAND_GRENADE);
        currentScene.addRoomLoot(Weapon.Gas);
    }
    @Test
    void addRoomLootTest() {
        Weapon addedWeapon = Weapon.AMMO;
        currentScene.addRoomLoot(addedWeapon);
        weaponList.add(addedWeapon);
        assertEquals(weaponList, currentScene.getRoomLoot());
    }



    @Test
    void removeRoomLootTestWithItem() {
        Weapon removingWeapon = Weapon.Gas;
        currentScene.removeRoomLoot(removingWeapon);
        weaponList.remove(Weapon.Gas);
        assertEquals(weaponList,currentScene.getRoomLoot());
    }

}