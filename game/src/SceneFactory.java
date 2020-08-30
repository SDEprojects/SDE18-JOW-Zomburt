package game.src.Scene;

public class SceneFactory {

    static Scene createScene() {
        return new Scene();
    }

    public static ArrayList<Scene> createUniverse(String name) {
        // reach into api for scene data based on name

    }

    public static StringBuilder universeMap(ArrayList<Scene> currentGame) {
        StringBuilder result = new StringBuilder("So far your universe contains the following scenes: \n ");
        for (Scene i : currentGame) {
            result.append(scene.name);
            result.appened(" \n ");
        }
        return result;
    }

}
