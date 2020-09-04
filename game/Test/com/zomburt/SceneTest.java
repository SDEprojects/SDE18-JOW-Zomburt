package com.zomburt;

import org.junit.Before;
import org.junit.Test;

public class SceneTest {
    Scene scene1;
    Scene scene2;
    Object level;

    @Before
    public void setUp() throws Exception {
    }

//    @Test
//    public void noArgSceneGeneration() throws Exception {
//        Scene scene1 = new Scene();
//        System.out.println(scene1);
//    }

    @Test
    public void namedSceneGenerated() throws Exception {
        Scene scene1 = new Scene("west entrance");
        System.out.println(scene1.toString());
        Scene scene2 = new Scene("discount bin");

    }
}
