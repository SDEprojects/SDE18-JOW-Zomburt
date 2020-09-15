package com.zomburt;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

class EasyTest {
    JSONObject mapJson;
    @BeforeEach
    void setUp() {
        String path = "./game/assets/store.json";
        Easy easy = Easy.getInstance();
        Object map = easy.createMap(path);
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