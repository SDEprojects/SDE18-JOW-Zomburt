package com.zomburt.gamestate;

import com.zomburt.Mode;
import com.zomburt.Scene;
import com.zomburt.Universe;
import com.zomburt.characters.Player;
import com.zomburt.characters.Zombie;

import java.io.Serializable;

public class GameState implements Serializable {
    public Player player;
    public Zombie zombie;
    public Mode mode;
    public Scene currentScene;
    public Boolean newScene;
    public Boolean win;
    public Universe gameUniverse;
    public String realName;
    public CheckPoint checkPoint;
    public String activity;
}
