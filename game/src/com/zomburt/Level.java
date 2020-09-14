package com.zomburt;

import com.zomburt.characters.GameCharacter;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public abstract class Level {
    private Mode mode;
    private Object gameMap;
    private GameCharacter player;

    public Level(Mode mode) {
        this.mode = mode;
    }
    public void createMap(String path) {
        try {
            gameMap = (Objects) new JSONParser().parse(new FileReader(path));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public abstract GameCharacter createPlayer();

    public Mode getMode() {
        return mode;
    }

    public Object getGameMap() {
        return gameMap;
    }

    public GameCharacter getPlayer() {
        player = createPlayer();
        return player;
    }
}
