package com.zomburt;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;

public class Game {
    public static HashMap<String, Scene> world = new HashMap<>();

    public static void main(String[] args) throws Exception {
        createUniverse();
//        GameEngine newGame = new GameEngine();
//        newGame.run();
    }
    public static void createUniverse() throws Exception {
        JSONObject obj = (JSONObject) new JSONParser().parse(new FileReader("./game/assets/store.json"));
        JSONObject jo = (JSONObject) obj;
        for (Object sceneName : jo.keySet()) {
            world.put((String) sceneName, new Scene((String) sceneName));
        }
//        Scene currentScene = world.get("east entrance");
//        System.out.println(currentScene);

//        for (Map.Entry<String, Scene> entry : world.entrySet()) {
//            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
//        }
    }
}