package com.zomburt.utility;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void testParse() {
        String input = "drink water";

        String expected = "move east";
        ArrayList<String> list = new ArrayList<>();
        String actual = Parser.parse(input).get(0).toString();
        System.out.println(actual);
        assertEquals(null, actual);


    }

}