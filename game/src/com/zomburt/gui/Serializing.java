package com.zomburt.gui;

import com.zomburt.GameEngine;
import com.zomburt.Scene;
import com.zomburt.characters.Player;

import java.io.*;

public class Serializing implements Serializable {
    private static Scene currentScene;
    private static Player currentPlayer;

    public void saveGame(){
        try {
            FileOutputStream fileOut = new FileOutputStream("./game/assets/save_game.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            currentPlayer = GameEngine.player;
            currentScene = GameEngine.currentScene;
            out.writeObject(currentPlayer);
            out.writeObject(currentScene);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in ./game/assets/save_game.ser");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void reloadGame() {
        try {
            FileInputStream fileIn = new FileInputStream("./game/assets/save_game.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            currentPlayer = (Player) in.readObject();
            currentScene = (com.zomburt.Scene) in.readObject();
            GameEngine.player = currentPlayer;
            GameEngine.currentScene = currentScene;
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException e2) {
            System.out.println("Player class not found");
            e2.printStackTrace();
            return;
        }
    }
}