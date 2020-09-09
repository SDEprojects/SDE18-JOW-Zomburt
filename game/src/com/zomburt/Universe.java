package com.zomburt;

import java.util.HashMap;

public class Universe {

    public static HashMap<String, String> worldItems = new HashMap<>();
    public static String sceneName = "";

    public Universe() {
        // want this to be a Java data structure that contains all scenes
        // graph?  which enables directional movement?
        // want to reuse what we have rather than starting afresh though...
        // could we just have an item state?
    }

//    public StringBuilder universeMap() {
//        // want this to provide the 'toString' function - potentially can be formatted into a 'map' of the world
//    }

//    public static void persistantItems(String item, Scene scene) {
//        sceneName = scene.getSceneName();
//        System.out.println("sceneName: " + sceneName);
//        worldItems.put(sceneName, item);
//        System.out.println("hashMap: " + worldItems);
//    }

    public String getItem(String sceneInQuestion) {
        if (worldItems.get(sceneInQuestion) != null) {
            return worldItems.get(sceneInQuestion);
        } else {
            return "No item present";
        }
    }
    public void setItem(String item, Scene scene) {
        sceneName = scene.getSceneName();
        worldItems.put(sceneName, item);
    }

}


// - - - -

//package com.zomburt;
//
//public class SceneFactory {
//
//    static Scene createScene(String name) throws Exception {
//        return new Scene("name");
//    }
//
//    public static ArrayList<Scene> createUniverse(String name) {
//        // reach into api for scene data based on name
//    }
//
//     @Override (kind of) of toString
//    public static StringBuilder universeMap(ArrayList<Scene> currentGame) {
//        StringBuilder result = new StringBuilder(
//                "So far your universe contains the following scenes: \n ");
//        for (Scene i : currentGame) {
//            if (sceneDiscovered) {
//                result.append(Scene.name);
//                result.append(" \n ");
//            }
//        }
//        return result;
//    }
//
//}
