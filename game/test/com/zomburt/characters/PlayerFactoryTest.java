package com.zomburt.characters;

import com.zomburt.Mode;
import com.zomburt.combat.Weapon;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PlayerFactoryTest {

    ArrayList<Weapon> playerInventory = new ArrayList<>();
    Player player1 = new Player("<Hunter>", 200, 0, playerInventory);
    Player player2 = new Player("<Master Hunter>", 100, 0);
    
    @Test
    public void testCreatePlayer_EASY_positive(){
        Player player3 = PlayerFactory.createPlayer(Mode.EASY);
        Weapon[] weapons = {};
        weapons = player3.getInventory().toArray(weapons);
        assertTrue(Arrays.stream(Weapon.values()).anyMatch(weapons[0]::equals));
        player3.getInventory().clear();
        assertEquals(player1.toString(), player3.toString());
    }

    @Test
    public void testCreatePlayer_HARD_positive(){
        assertEquals(player2.toString(), PlayerFactory.createPlayer(Mode.HARD).toString());
    }

    @Test
    public void testCreatePlayer_EASY_negative(){
       assertNotEquals(player1.toString(), PlayerFactory.createPlayer(Mode.HARD).toString());
       assertEquals(null, PlayerFactory.createPlayer(Mode.MEDIAN));
    }

    @Test
    public void testCreatePlayer_HARD_negative() {
      assertNotEquals(player2.toString(), PlayerFactory.createPlayer(Mode.EASY).toString());
      assertEquals(null, PlayerFactory.createPlayer(Mode.MEDIAN));
    }

}