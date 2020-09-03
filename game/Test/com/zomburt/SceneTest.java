package com.zomburt;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SceneTest {
    Scene scene1;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void singleSceneGeneration() throws Exception {
        Scene scene1 = new Scene();
        System.out.println(scene1);
    }
}
