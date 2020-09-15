package com.zomburt;

import com.zomburt.characters.Characters;

public class Hard extends Level {
    private static Hard hard = null;
    public Hard(Mode mode) {
        super(mode);
    }

    public static Hard getInstance() {
        if (hard == null) {
            hard = new Hard(Mode.HARD);
        }
        return hard;
    }
    @Override
    public Characters createPlayer() {
        Characters player = new Characters(30);
        return player;
    }
    @Override
    public Object createMap(String path) {
        return null;
    }
}
