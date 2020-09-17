package com.zomburt.gui;

import com.zomburt.GameEngine;
import com.zomburt.Mode;
import com.zomburt.Scene;
import com.zomburt.characters.Player;

import java.io.*;

public class Serializing implements Serializable {
    private static Scene currentScene;
    private static Player currentPlayer;
    private static Mode currentMode;

    public void saveGame(){
        try {
            FileOutputStream fileOut = new FileOutputStream("./game/assets/save_game.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
          //  currentMode = GameEngine.mode;
            currentPlayer = GameEngine.player;
            currentScene = GameEngine.currentScene;
         //   out.writeObject(currentMode);
            out.writeObject(currentPlayer);
            out.writeObject(currentScene);
            out.close();
            fileOut.close();
            System.out.println("Current game data has been saved into ./game/assets/save_game.ser");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void reloadGame() {
        try {
            FileInputStream fileIn = new FileInputStream("./game/assets/save_game.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
        //    currentMode = (Mode) in.readObject();
            currentPlayer = (Player) in.readObject();
            currentScene = (com.zomburt.Scene) in.readObject();
        //    GameEngine.mode = currentMode;
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