package com.zomburt;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("What is your name?");
        Character player = new Character(in.nextLine());

        System.out.println("In the distant year of 2021, an advanced infectious airborne disease has turned the population into Divoc Zombies. \n" +
                "All that remains is you and a few of your fellow Faction members. \n" +
                "You must navigate through three heavily infected Zombie areas to find the magical antidote and locate your friends and family to free them from a Zombie life of Divoc Suffering. \n" +
                "If you fail, you will become a Divoc Zombie.");
        System.out.println(player);
    }
}
