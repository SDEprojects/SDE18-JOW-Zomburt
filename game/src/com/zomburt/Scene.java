package com.zomburt;

import java.util.ArrayList;

public class Scene {
    ArrayList<String> items = new ArrayList<String>();
    ArrayList<String> enemies = new ArrayList<String>();
    String flavorText = "";

    public Scene(String name) {
        // generate instance of scene
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

    public String getFlaovrText() {
        return flavorText;
    }
    private void setFlavorText(String name) {
        // access API
    }
}
