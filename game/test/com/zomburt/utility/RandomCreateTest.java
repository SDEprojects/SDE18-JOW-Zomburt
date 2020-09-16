package com.zomburt.utility;

import com.zomburt.Mode;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RandomCreateTest {
    private  Mode mode;
    @BeforeEach
    void setUp() {
    }

    @Test
    void randNumEasyModeTest() {
        mode = Mode.EASY;
        int randomNum = RandomCreate.randNum(mode);
        Assert.assertTrue(randomNum < 2);
    }

    @Test
    void randNumMedianModeTest() {
        mode = Mode.MEDIAN;
        int randomNum = RandomCreate.randNum(mode);
        Assert.assertTrue(randomNum < 13/2);
    }

    @Test
    void randNumHardModeTest() {
        mode = Mode.HARD;
        int randomNum = RandomCreate.randNum(mode);
        Assert.assertTrue(randomNum < 13);
    }
}