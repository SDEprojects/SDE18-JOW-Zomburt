package com.zomburt;

import com.zomburt.characters.ZombieTypes;
import com.zomburt.combat.Weapon;
import com.zomburt.gui.GameApp;
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
    ArrayList<ZombieTypes> feature;
    ArrayList<Weapon> roomLoot;

    public Scene(String locationName) throws Exception {
        setSceneObject(locationName);
        setSceneName(locationName);
    }

    public void setSceneObject(String locationName) throws Exception {
        Object store;
        if (locationName == "parking lot")
            store = new JSONParser().parse(new FileReader("./game/assets/ParkingLot.json"));
        else
            store = new JSONParser().parse(new FileReader("./game/assets/store2.json"));
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
        GameApp.getInstance().appendToCurActivity(look);
    }
    public void setLook() {
        look = (String) sceneObj.get("look");
    }

    public void getSearch() {
        GameApp.getInstance().appendToCurActivity(search);
        if (!getRoomLoot().isEmpty())
            for (Weapon i : getRoomLoot())
                GameApp.getInstance().appendToCurActivity("  " + i);
    }
    public void setSearch() {
        search = (String) sceneObj.get("search");
    }

    public ArrayList<ZombieTypes> getFeature() {
        return feature;
    }
    public void setFeature() {
        feature = new ArrayList<ZombieTypes>();
        // class cast exception
        @SuppressWarnings("unchecked")
        List<String> strFeature = (List<String>) sceneObj.get("feature");
        for (ZombieTypes zombieTypes : ZombieTypes.values()) {
            for (String item : strFeature) {
                if (zombieTypes.getName().toUpperCase().equals(item.toUpperCase())) {
                    feature.add(zombieTypes);
                }
            }
        }
    }

    public void setFeatures(ArrayList<ZombieTypes> list) {
        this.feature = list;
    }

    public ArrayList<Weapon> getRoomLoot() {
        return roomLoot;
    }
    public void setRoomLoot() {
        roomLoot = new ArrayList<Weapon>();
        @SuppressWarnings("unchecked")
        List<String> strRoomLoot = (List<String>) sceneObj.get("roomLoot");
        for (Weapon weapon : Weapon.values()) {
            for (String item : strRoomLoot) {
                if (weapon.getName().toUpperCase().equals(item.toUpperCase())) {
                    roomLoot.add(weapon);
                }
            }
        }
    }

    public void addRoomLoot(Weapon lootItem) {
        if (getRoomLoot().contains(lootItem)) {
            GameApp.getInstance().appendToCurActivity("Item already in that room");
        } else {
            getRoomLoot().add(lootItem);
        }
    }

    public void removeRoomLoot(Weapon lootItem) {
        if (getRoomLoot().contains(lootItem)) {
            getRoomLoot().remove(lootItem);
        } else {
            GameApp.getInstance().appendToCurActivity("Can't remove that item from the game-world");
        }
    }

    public void updateRoomLootJSON() throws Exception {
        Object store2 = new JSONParser().parse(new FileReader("./game/assets/store.json"));
        JSONObject joStore2 = (JSONObject) store2;

        JSONObject a = (JSONObject) joStore2.get("discount bin");
        JSONArray b = (JSONArray) a.get("roomLoot");

        b.add("candy bar");
        GameApp.getInstance().appendToCurActivity("b: " + b);

        //Write JSON file
        try (FileWriter file = new FileWriter("./game/assets/store2.json", true)) {
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
