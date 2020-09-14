package com.zomburt;

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
    public GameCharacter createPlayer() {
        Character player1 = new Character(100);
        return player1;
    }

}
