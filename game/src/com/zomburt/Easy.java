package com.zomburt;

import com.zomburt.characters.GameCharacter;

public class Easy extends Level {
    private static Easy easy = null;

    private Easy(Mode mode) {
        super(mode);
    }

    public static Easy getInstance() {
        if (easy == null) {
            easy = new Easy(Mode.EASY);
        }
        return easy;
    }
    @Override
    public GameCharacter createPlayer() {
        GameCharacter player = new GameCharacter(100);
        return player;
    }
}
