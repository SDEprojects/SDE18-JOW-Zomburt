package com.zomburt.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public static ArrayList<String> parse(String input){
        ArrayList<String> options = new ArrayList<>(Arrays.asList(new String[]{"hint", "fight", "move", "pick up", "drop", "use", "look", "open", "help", "quit", "exit", "search", "inv", "check"}));
        ArrayList<String> commands;
                if(input.startsWith("pick up"))
                    commands = new ArrayList<>(Arrays.asList(input.split("(?<=up)")));
                else if(input.startsWith("drop"))
                    commands = new ArrayList<>(Arrays.asList(input.split("(?<=drop)")));
                else
                    commands = new ArrayList<>(Arrays.asList(input.split(" ")));
        return !options.contains(commands.get(0)) || commands.size() == 0 ? null : reduceArray(commands);
    }

    public static ArrayList<String> reduceArray(ArrayList<String>  arr){
        List<String> list = new ArrayList<String>(arr);
        list.removeAll(Arrays.asList("", null));  //removes empty and null elements
        list.replaceAll(x -> x.trim());
        return (ArrayList<String>) list;
    }
}
