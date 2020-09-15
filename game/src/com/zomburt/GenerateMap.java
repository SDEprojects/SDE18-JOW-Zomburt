package com.zomburt;

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

public class GenerateMap {
    private static Mode mode;
    private static FileWriter file;
    private static GenerateMap generateMap = null;
    public static int totalNumZombies;

    public GenerateMap(Mode mode){
        this.mode = mode;
    }

    public static GenerateMap getInstance() {
        if (generateMap == null) {
            generateMap = new GenerateMap(GameApp.getInstance().getModeInput());
        }
        return generateMap;
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
            int numberZombie = 0;
            int numberWeapon = 0;
            if (mode == Mode.EASY) {
                numberZombie = RandomCreate.randNum(Mode.EASY);
                numberWeapon = RandomCreate.randNum(Mode.HARD);
            }
            else if (mode == Mode.MEDIAN) {
                numberZombie = RandomCreate.randNum(Mode.MEDIAN);
                numberWeapon = RandomCreate.randNum(Mode.MEDIAN);
            }
            else if (mode == Mode.HARD) {
                numberZombie = RandomCreate.randNum(Mode.HARD);
                numberWeapon = RandomCreate.randNum(Mode.EASY);
            }
            totalNumZombies = numberZombie;
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
