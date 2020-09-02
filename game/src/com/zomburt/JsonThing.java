package com.zomburt;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonThing {
    public static void main(String[] args) throws Exception {
        // parsing file "JSONExample.json"
        Object obj = new JSONParser().parse(new FileReader("./game/assets/zapi.json"));

        // typecasting obj to JSONObject
        JSONObject jo = (JSONObject) obj;

        // getting firstName and lastName
        JSONObject game = (JSONObject) jo.get("zomburt");
        JSONObject scenes = (JSONObject) game.get("scenes");
        JSONObject store = (JSONObject) scenes.get("store");
        for (Object thing: store.entrySet()) {
            System.out.println(thing);
        }

        System.out.println();
        JSONObject  def = (JSONObject) scenes.get("default");
        String flav = (String) def.get("flavorText");
        System.out.println(flav);
    }
}

