package com.zomburt;

import java.util.ArrayList;

public class Scene {
    String flavorText;
    Object movement;
    ArrayList<String> roomLoot;
    ArrayList<String> feature;

    public Scene() {
        // no arg
        flavorText = "";
        movement = new Object();
        roomLoot = new ArrayList<String>();
        feature = new ArrayList<String>();
    }

    public Scene(String locationName) {

    }

    public ArrayList<String> getRoomLoot() {
        return roomLoot;
    }
    private void setRoomLoot(ArrayList<String> itemsToAdd, boolean overwrite) {
        if (overwrite) {
            roomLoot = itemsToAdd;
        } else {
            for (String i : itemsToAdd) {
                roomLoot.add(i);
            }
        }
    }


}
