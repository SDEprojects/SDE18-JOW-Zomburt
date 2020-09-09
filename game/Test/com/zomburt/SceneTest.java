package com.zomburt;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SceneTest {

    Scene currentScene;

    @Before
    public void setUp() throws Exception {
        currentScene = new Scene("west entrance");
    }

    @Test
    public void updateRoomLootTest() throws Exception {
        currentScene.updateRoomLoot("testymctesttest", "discount bin");
        currentScene.removeRoomLoot("testymctesttest", "west entrance");
    }

}