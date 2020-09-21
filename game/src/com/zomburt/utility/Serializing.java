package com.zomburt.utility;
import com.zomburt.gamestate.GameState;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serializing implements Serializable {
    public void saveGameSate(GameState gameState){
        try {
            FileOutputStream fileOut = new FileOutputStream("./game/assets/save_game.ser");
            ObjectOutputStream save = new ObjectOutputStream(fileOut);
            save.writeObject(gameState);
            save.close();
            fileOut.close();
            System.out.println("Current game data has been saved into ./game/assets/save_game.ser");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public GameState loadGameState() {
        GameState gameState = null;
        try {
            FileInputStream fileIn = new FileInputStream("./game/assets/save_game.ser");
            ObjectInputStream reload = new ObjectInputStream(fileIn);
            gameState = (GameState) reload.readObject();
            reload.close();
            fileIn.close();
            System.out.println("Previously saved game has been reloaded from ./game/assets/save_game.ser");
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
        return gameState;
    }
}