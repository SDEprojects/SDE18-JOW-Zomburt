package com.zomburt.utility;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class ParserTest {

    @Test
    public void testParse_negative_wrongCommand() {
        String input1 = " drink water  ";
        String input2 = " gO sWimMiNg        ";
        String input3 = "asdfaklsdjflhewfnasfd234r32098590235r23r82093r-32";
        assertNull(Parser.parse(input1));
        assertNull(Parser.parse(input2));
        assertNull(Parser.parse(input3));
    }

    @Test
    public void testParse_negative_emptyString() {
         String input = "";
         assertNull(Parser.parse(input));
        }

    @Test
    public void testParse_negative_nullInput() {
        String input = null;
        assertThrows(NullPointerException.class, ()->{
            Parser.parse(input);
        });
    }



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
        String inputString = "move east north ";
        ArrayList<String> commands = Parser.parse(inputString);
        assertEquals("move", commands.get(0));
        assertEquals("east", commands.get(1));
    }

}