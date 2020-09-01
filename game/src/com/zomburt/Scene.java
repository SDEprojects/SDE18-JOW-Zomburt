package com.zomburt;

import java.util.ArrayList;
//import zapi;

public class Scene {
    ArrayList<String> items = new ArrayList<String>();
    ArrayList<String> enemies = new ArrayList<String>();
    String flavorText = "";
    String path = "Zomburt.scenes.default";

    public Scene(String name) {
        // generate instance of scene
        path = "Zomburt.scenes." + name;
    }

    public ArrayList<String> getItems() {
        return items;
    }
    private void setItems(ArrayList<String> itemsToAdd, boolean overwrite) {
        if (overwrite) {
            items = itemsToAdd;
        } else {
            for (String i : itemsToAdd) {
                items.add(i);
            }
        }
    }

    String printYAML() {
        System.out.println();
    }

    public String getFlaovrText() {
        return flavorText;
    }
//    private void setFlavorText(String name) {
//        // access API
//    }
}
