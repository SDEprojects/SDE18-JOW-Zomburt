import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) throws InterruptedException {
        Boolean win = false, lose = false;
        Scanner in = new Scanner(System.in);
        System.out.println("What is your name?");
        Character player = new Character(in.nextLine());

        String intro = "In the distant year of 2021, an advanced infectious airborne \n" +
                "disease has turned the population into Divoc Zombies. \n" +
                "All that remains is you and a few of your fellow Faction members. \n" +
                "You must navigate through three heavily infected Zombie areas to find \n" +
                "the magical antidote and locate your friends and family to free them \n" +
                "from a Zombie life of Divoc Suffering. \n" +
                "If you fail, you will become a Divoc Zombie.";
        char[] chars = intro.toCharArray();
        // Print a char from the array, then sleep for 1/25 second
        for (int i = 0; i < chars.length; i++){
            System.out.print(chars[i]);
            Thread.sleep(25);
        }
        System.out.println();

        while (win == false || lose == false) {
            System.out.print(">");
            ArrayList<String> commands = parse(in.nextLine().toLowerCase().trim());
            if(commands == null)
                System.out.println("That's not a valid command. For a list of available commands input \"help\"");
            else
                System.out.println(Arrays.toString(commands.toArray()));
            }

    }

    public static ArrayList<String> parse(String input){
        ArrayList<String> options = new ArrayList<>(), commands = new ArrayList<>();
        options.addAll(Arrays.asList(new String[]{"move", "pick", "drop", "use", "look","help"}));
        commands.addAll(Arrays.asList(input.split(" ")));
        if(!options.contains(commands.get(0)))
            return null;
        else {
            if (commands.size() > 2) {
                commands = reduceArray(commands);
                System.out.println(Arrays.toString(commands.toArray()));
            }
            return commands;
        }
    }

    public static ArrayList<String> reduceArray(ArrayList<String>  arr){
        List<String> list = new ArrayList<String>(arr);
        list.removeAll(Arrays.asList("", null));
//        if(list.get(0) == "pick" && list.get(1) == "up")
        return (ArrayList<String>) list;
    }
}
