package com.zomburt;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;

public class Universe {
    public static HashMap<String, Scene> world = new HashMap<>();
    private String level;

//    public Universe(String level) throws Exception {
//        JSONObject obj = null;
//        switch(level.toUpperCase()){
//            case "easy":
//                obj = (JSONObject) new JSONParser().parse(new FileReader("./game/assets/store.json")); //easy map
//                break;
//            case "hard":
//                obj = (JSONObject) new JSONParser().parse(new FileReader("./game/assets/store.json")); //hard map
//                break;
//        }
//        JSONObject jo = (JSONObject) obj;
//        for (Object sceneName : jo.keySet()) {
//            world.put((String) sceneName, new Scene((String) sceneName));
//            int random = ThreadLocalRandom.current().nextInt(1); // randomly generate 0 or 1
//            if(random == 1)
//                ZombieFactory.createZombie(level);
//        }
//    }

    public Universe() throws Exception {
        JSONObject obj = (JSONObject) new JSONParser().parse(new FileReader("./game/assets/store.json"));
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

    public String getLevel() {return level;};

}
