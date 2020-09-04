package com.zomburt;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
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
        Object store = new JSONParser().parse(new FileReader("./game/assets/store.json"));
        JSONObject joStore = (JSONObject) store;
        JSONObject sceneObj = (JSONObject) joStore.get(locationName);
        this.sceneObj = sceneObj;
        setFlavorText();
        setMovement();
        setLook();
        setSearch();
//        setFeature();
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

    public String getLook() {
        return look;
    }
    public void setLook() {
        look = (String) sceneObj.get("look");
    }

    public String getSearch() {
        return search;
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
