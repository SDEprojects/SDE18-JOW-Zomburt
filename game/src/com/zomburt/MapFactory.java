package com.zomburt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zomburt.characters.ZombieFactory;
import com.zomburt.combat.Weapon;
import com.zomburt.gui.GameApp;
import com.zomburt.utility.RandomCreate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Set;

public class MapFactory {
    private Mode mode;
    private FileWriter file;
    private int totalNumZombies;
    private static MapFactory generateMap = null;

    public MapFactory(Mode mode){
        this.mode = mode;
    }

    public int getTotalNumZombies() {
        return totalNumZombies;
    }

    public static MapFactory getInstance() {
        if (generateMap == null) {
            generateMap = new MapFactory(GameApp.getInstance().getModeInput());
        }
        return generateMap;
    }

    public Object createMap(String path) {
        ObjectMapper mapper = new ObjectMapper();
        Object map = null;
        String locationName = null;
        Random rand = new Random();
        try {
            map = new JSONParser().parse(new FileReader(path));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject mapJson = (JSONObject) map;
        Set<String> keys = mapJson.keySet();
        for (String key : keys) {
            JSONObject location = (JSONObject) mapJson.get(key);
            JSONArray features = (JSONArray) location.get("feature");
            JSONArray roomLoot = (JSONArray) location.get("roomLoot");
            // clear out the zombies in the feature;
            features.clear();
            // clear out the roomLoot
            roomLoot.clear();
            int numberZombie = 0;
            int numberWeapon = 0;
            if (mode == Mode.EASY) {
                numberZombie = RandomCreate.randNum(Mode.EASY);
                numberWeapon = RandomCreate.randNum(Mode.HARD);
            }
            else if (mode == Mode.HARD) {
                numberZombie = RandomCreate.randNum(Mode.HARD);
                numberWeapon = RandomCreate.randNum(Mode.EASY);
            }
            totalNumZombies += numberZombie;
            String json;
            // add new zombies to feature
            for (int i = 0; i < numberZombie; i++) {
                try {
                    json = mapper.writeValueAsString(ZombieFactory.createZombie(mode));
                    features.add(json);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
            // add new weapons to roomLoot
            for (int i = 0; i < numberWeapon; i++) {
                int randWeapon = rand.nextInt(Weapon.values().length);
                try {
                    json = mapper.writeValueAsString(Weapon.values()[randWeapon]);
                    roomLoot.add(json);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            file = new FileWriter("./game/assets/store.json");
            file.write(mapJson.toJSONString()) ;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            {
                try {
                    file.flush();
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // read from new file
        try {
            map = new JSONParser().parse(new FileReader(path));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return map;
    }


}
