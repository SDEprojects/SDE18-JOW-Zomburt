package com.zomburt.utility;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SoundTest {

    Sound sound1 = new Sound("win");
    Sound sound2 = new Sound("lose");
    Sound sound3 = new Sound("combat");
    Sound sound4 = new Sound("intro");


    @Test
    public void testChooseFile() {
        assertTrue(sound1.chooseFile("win").equals("./game/assets/sounds/applause.wav"));
        assertTrue(sound2.chooseFile("lose").equals("./game/assets/sounds/Super Mario Lose Life.wav"));
        assertTrue(sound3.chooseFile("combat").equals("./game/assets/sounds/punch.wav"));
        assertTrue(sound4.chooseFile("intro").equals("./game/assets/sounds/suspense.wav"));

    }
}