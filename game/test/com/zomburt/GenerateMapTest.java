package com.zomburt;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.util.Set;

class GenerateMapTest<string> {
    private static Mode mode;
    private static FileWriter file;
    private static GenerateMap generateMap = null;
    JSONObject mapJson;
    private String path = "./game/assets/store.json";
    @BeforeEach
    void setUp() {
        generateMap = new GenerateMap(Mode.EASY);
        Object map = generateMap.createMap(path);
        mapJson = (JSONObject) map;
    }

    @Test
    void createMap() {
        Set<String> keys = mapJson.keySet();
        for (String key : keys) {
            JSONObject location = (JSONObject) mapJson.get(key);
            JSONArray features = (JSONArray) location.get("feature");
            JSONArray roomLoot = (JSONArray) location.get("roomLoot");
            System.out.println(features.toString());
            System.out.println(roomLoot.toString());
        }
    }
}