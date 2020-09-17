package com.zomburt.utility;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {


    @Test
    void parsePickupTest() {
        String inputString ="pick up knife  ";
        ArrayList<String> commands = Parser.parse(inputString);
        assertEquals("pick up", commands.get(0));
        assertEquals("knife", commands.get(1));
    }

    @Test
    void parsedropTest() {
        String inputString ="drop knife  ";
        ArrayList<String> commands = Parser.parse(inputString);
        assertEquals("drop", commands.get(0));
        assertEquals("knife", commands.get(1));
    }
    @Test
    void parsemoveTest() {
        String inputString ="move east north ";
        ArrayList<String> commands = Parser.parse(inputString);
        assertEquals("move", commands.get(0));
        assertEquals("east", commands.get(1));
    }

}