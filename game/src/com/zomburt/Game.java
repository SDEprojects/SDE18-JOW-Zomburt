package com.zomburt;

import java.io.FileNotFoundException;

public class Game {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        GameEngine newGame = new GameEngine();
        newGame.run();
    }
}