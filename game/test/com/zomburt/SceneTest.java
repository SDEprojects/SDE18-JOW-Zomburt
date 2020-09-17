package com.zomburt;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.io.IOException;

class SceneTest {


    JSONObject sceneObj;


    @BeforeEach
    void setUp() {
        Object store = null;
        String locationName = "pharmacy";
        try {
            store = new JSONParser().parse(new FileReader("./game/assets/store.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject joStore = (JSONObject) store;
        sceneObj = (JSONObject) joStore.get(locationName);
    }

    @Test
    public void setSearchTest() {
        String search;
        search = (String) sceneObj.get("search");
        assertEquals("Some listerine sparkles in the shadow of the mostly empty shelving, and there may be something else there...", search);

    }


    @Test
    public void setLookTest(){
        String look;
        look = (String) sceneObj.get("look");
        assertEquals("You see some multi-colored loofas and realize you haven't showered since this all started... months ago..", look);
    }
}