package com.zomburt;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scene {
    JSONObject sceneObj;
    String sceneName;
    String flavorText;
    Object movement;
    String look;
    String search;
    ArrayList<String> feature;
    ArrayList<String> roomLoot;

//    public Scene() {
//        no arg
//    }

    public Scene(String locationName) throws Exception {
        setSceneObject(locationName);
        setSceneName(locationName);
    }

    public void setSceneObject(String locationName) throws Exception {
        Object store;
        if (locationName == "parking lot")
            store = new JSONParser().parse(new FileReader("./game/assets/parkinglot.json"));
        else
            store = new JSONParser().parse(new FileReader("./game/assets/store.json"));
        JSONObject joStore = (JSONObject) store;
        JSONObject sceneObj = (JSONObject) joStore.get(locationName);
        this.sceneObj = sceneObj;
        setFlavorText();
        setMovement();
        setLook();
        setSearch();
        setFeature();
        setRoomLoot();
    }
    public Object getSceneObj() {
        return sceneObj;
    }

    public String getSceneName() {
        return sceneName;
    }
    public void setSceneName(String locationName) {
        sceneName = locationName;
    }

    public String getFlavorText() {
        return flavorText;
    }
    public void setFlavorText() {
        flavorText = (String) sceneObj.get("flavorText");
    }

    public Object getMovement() {
        return movement;
    }
    public void setMovement() {
        movement = (Object) sceneObj.get("movement");
    }

    public void getLook() {
        System.out.println(look);
    }
    public void setLook() {
        look = (String) sceneObj.get("look");
    }

    public void getSearch() {
        System.out.println(search);
        System.out.println(getRoomLoot());
    }
    public void setSearch() {
        search = (String) sceneObj.get("search");
    }

    public ArrayList<String> getFeature() {
        return feature;
    }
    public void setFeature() {
//        feature = new ArrayList<String> ((String) sceneObj.get("feature"))Arrays.asList(str.split(","));
//        try {
//            String[] strFeature = (String[]) sceneObj.get("feature");
//            feature = new ArrayList<String>(Arrays.asList(strFeature));
//        } catch (Exception e) {
//            feature = null;
//        }
        feature = new ArrayList<String>();
        // class cast exception
        @SuppressWarnings("unchecked")
        List<String> strFeature = (List<String>) sceneObj.get("feature");
        feature.addAll(strFeature);
    }

    public ArrayList<String> getRoomLoot() {
        return roomLoot;
    }
    public void setRoomLoot() {
        roomLoot = new ArrayList<String>();
        @SuppressWarnings("unchecked")
        List<String> strRoomLoot = (List<String>) sceneObj.get("roomLoot");
        roomLoot.addAll(strRoomLoot);
    }

    public void addRoomLoot(String lootItem) {
        if (getRoomLoot().contains(lootItem)) {
            System.out.println("Item already in that room");
        } else {
            getRoomLoot().add(lootItem);
        }
    }

    public void removeRoomLoot(String lootItem) {
        if (getRoomLoot().contains(lootItem)) {
            getRoomLoot().remove(lootItem);
        } else {
            System.out.println("Can't remove that item from the game-world");
        }
    }

    public void updateRoomLootJSON() throws Exception {
        Object store2 = new JSONParser().parse(new FileReader("./game/assets/store.json"));
        JSONObject joStore2 = (JSONObject) store2;

        JSONObject a = (JSONObject) joStore2.get("discount bin");
        JSONArray b = (JSONArray) a.get("roomLoot");

        b.add("candy bar");
        System.out.println("b: " + b);

        //Write JSON file
        try (FileWriter file = new FileWriter("./game/assets/store2.json", true)) {
//            file.append("new line for funsies");
            file.write("\n");
            file.write(joStore2.toJSONString());
            file.write("\n");
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return sceneName.toUpperCase() + " { \n" +
                " sceneObj = " + sceneObj + "; \n" +
                " flavorText = " + flavorText + "; \n" +
                " movement = " + movement + "; \n" +
                " look = " + look + "; \n" +
                " search = " + search + "; \n" +
                " feature = " + feature + "; \n" +
                " roomLoot = " + roomLoot +" \n" +
                '}';
    }

}
