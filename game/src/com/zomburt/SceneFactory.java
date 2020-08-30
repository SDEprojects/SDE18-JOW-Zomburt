package com.zomburt;

import java.util.ArrayList;

public class SceneFactory {

    static Scene createScene(String name) {
        return new Scene(name);
    }

//    public static ArrayList<Scene> createUniverse(String name) {
//        // reach into api for scene data based on name
//    }

    public static StringBuilder universeMap(ArrayList<Scene> currentGame) {
        StringBuilder result = new StringBuilder(
                "So far your universe contains the following scenes: \n ");
//        for (Scene i : currentGame) {
//            if (sceneDiscovered) {
//                result.append(Scene.name);
//                result.append(" \n ");
//            }
//        }
        return result;
    }

}
