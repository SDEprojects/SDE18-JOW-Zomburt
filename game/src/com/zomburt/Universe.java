package com.zomburt;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;

public class Universe {
    public static HashMap<String, Scene> world = new HashMap<>();

    public Universe() throws Exception {
        JSONObject obj = (JSONObject) new JSONParser().parse(new FileReader("./game/assets/store2.json"));
        JSONObject jo = (JSONObject) obj;
        for (Object sceneName : jo.keySet()) {
            world.put((String) sceneName, new Scene((String) sceneName));
        }
    }

    public static HashMap<String, Scene> getWorld() {
        return world;
    }

    public static Scene getScene(String scene){
        return world.get(scene);
    }
}
