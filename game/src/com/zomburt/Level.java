package com.zomburt;

import com.zomburt.characters.Characters;

public abstract class Level {
    private Mode mode;
    private Object gameMap;
    private Characters player;

    public Level(Mode mode){
        this.mode = mode;
    }


    public abstract Object createMap(String path);

    public abstract Characters createPlayer();

    public Mode getMode() {
        return mode;
    }

    public Object getGameMap() {
        return gameMap;
   }

    public Characters getPlayer() {
        player = createPlayer();
        return player;
    }
}
