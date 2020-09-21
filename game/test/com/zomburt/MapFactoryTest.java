package com.zomburt;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.util.Set;

class MapFactoryTest<string> {
    private static Mode mode;
    private static FileWriter file;
    private static MapFactory generateMap = null;
    JSONObject mapJson;
    private String path = "./game/assets/JSON_Objects/store.json";

    @Test
    void createEasyMap() {
        generateMap = new MapFactory(Mode.EASY);
        Object map = generateMap.createMap(path);
        mapJson = (JSONObject) map;
        Set<String> keys = mapJson.keySet();
        for (String key : keys) {
            JSONObject location = (JSONObject) mapJson.get(key);
            JSONArray features = (JSONArray) location.get("feature");
            JSONArray roomLoot = (JSONArray) location.get("roomLoot");
            System.out.println(features);
            System.out.println(roomLoot);
        }
    }

    @Test
    void createHardMap() {
        generateMap = new MapFactory(Mode.HARD);
        Object map = generateMap.createMap(path);
        mapJson = (JSONObject) map;
        Set<String> keys = mapJson.keySet();
        for (String key : keys) {
            JSONObject location = (JSONObject) mapJson.get(key);
            JSONArray features = (JSONArray) location.get("feature");
            JSONArray roomLoot = (JSONArray) location.get("roomLoot");
            System.out.println(features);
            System.out.println(roomLoot);
        }
    }
}