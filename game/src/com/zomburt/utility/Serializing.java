package com.zomburt.utility;

import com.zomburt.GameEngine;
import com.zomburt.Mode;
import com.zomburt.Scene;
import com.zomburt.Universe;
import com.zomburt.characters.Player;

import java.io.*;

public class Serializing implements Serializable {
    private static Scene currentScene;
    private static Player currentPlayer;
    private static Mode currentMode;
    private static String currentName;
    private static Universe currentUniverse;

    public void saveGame(){
        try {
            FileOutputStream fileOut = new FileOutputStream("./game/assets/save_game.ser");
            ObjectOutputStream save = new ObjectOutputStream(fileOut);
          //  currentMode = GameEngine.mode;
            currentName = GameEngine.realName;
            currentPlayer = GameEngine.player;
            currentScene = GameEngine.currentScene;
            currentUniverse = GameEngine.gameUniverse;

         //   save.writeObject(currentMode);
            save.writeObject(currentName);
            save.writeObject(currentPlayer);
            save.writeObject(currentScene);
            save.writeObject(currentUniverse);
            save.close();
            fileOut.close();
            System.out.println("Current game data has been saved into ./game/assets/save_game.ser");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void reloadGame() {
        try {
            FileInputStream fileIn = new FileInputStream("./game/assets/save_game.ser");
            ObjectInputStream reload = new ObjectInputStream(fileIn);
        //    currentMode = (Mode) reload.readObject();
            currentName = (String) reload.readObject();
            currentPlayer = (Player) reload.readObject();
            currentScene = (com.zomburt.Scene) reload.readObject();
            currentUniverse = (Universe) reload.readObject();
        //    GameEngine.mode = currentMode;
            GameEngine.realName = currentName;
            GameEngine.player = currentPlayer;
            GameEngine.currentScene = currentScene;
            GameEngine.gameUniverse = currentUniverse;
            reload.close();
            fileIn.close();
            System.out.println("Previously saved game has been reloaded from ./game/assets/save_game.ser");
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return;
        }
    }
}