package com.zomburt;

import com.zomburt.characters.Characters;
import com.zomburt.characters.ZombieFactory;
import com.zomburt.combat.Weapon;
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

public class Easy extends Level {
    private static Easy easy = null;
    private static FileWriter file;
    private Easy(Mode mode) {
        super(mode);
    }
    public static Easy getInstance() {
        if (easy == null) {
            easy = new Easy(Mode.EASY);
        }
        return easy;
    }
    @Override
    public Characters createPlayer() {
        Characters player = new Characters(100);
        return player;
    }

    public Object createMap(String path) {
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
            int numberZombie = RandomCreate.randNum(Mode.EASY);
            int numberWeapon = RandomCreate.randNum(Mode.HARD);
            // add new zombies to feature
            for (int i = 0; i < numberZombie; i++) {
                features.add(ZombieFactory.createZombie(Mode.EASY).getName());
            }
            // add new weapons to roomLoot
            for (int i = 0; i < numberWeapon; i++) {
                int randWeapon = rand.nextInt(Weapon.values().length);
                roomLoot.add(Weapon.values()[randWeapon].getName());
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
