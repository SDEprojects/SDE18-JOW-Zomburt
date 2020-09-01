import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public static void runCommands(String input){
        ArrayList<String> commands = parse(input.toLowerCase().trim());
        if(commands == null)
            System.out.println("That's not a valid command. For a list of available commands input \" help\"");
        else if(commands.get(0).contains("help"))
            help();
        else
            System.out.println(Arrays.toString(commands.toArray()));
    }

    public static ArrayList<String> parse(String input){
        ArrayList<String> options = new ArrayList<>(Arrays.asList(new String[]{"move", "pick up", "drop", "use", "look", "open", "help"}));
        ArrayList<String> commands = input.startsWith("pick up")
                ? new ArrayList<>(Arrays.asList(input.split("(?<=up)")))
                : new ArrayList<>(Arrays.asList(input.split(" ")));
        return !options.contains(commands.get(0)) || commands.size() == 0 ? null : reduceArray(commands);
    }

    public static ArrayList<String> reduceArray(ArrayList<String>  arr){
        List<String> list = new ArrayList<String>(arr);
        list.removeAll(Arrays.asList("", null));  //removes empty and null elements
        list.replaceAll(x -> x.trim());
        return (ArrayList<String>) list;
    }

    public static void help() {
        System.out.println("These are some commands you can perform: \n" +
                "-move <direction>-\n" +
                "-use <item>-\n" +
                "-pick up <item>-\n" +
                "-drop <item>-\n" +
                "-look(search) <direction>-\n" +
                "-open(close) door-");
    }
}
