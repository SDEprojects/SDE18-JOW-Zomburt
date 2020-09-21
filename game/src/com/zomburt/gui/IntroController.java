package com.zomburt.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class IntroController {

    @FXML private Button startGame;
    @FXML private ImageView intro;
    @FXML private RadioButton easyMode;
    @FXML private RadioButton hardMode;
    @FXML private MenuBar introMenu;
    @FXML private MenuItem reload;

    public IntroController() {}

    public Button getStartGame() {
        return startGame;
    }

    public ImageView getIntro() {
        return intro;
    }

    public RadioButton getEasyMode() {
        return easyMode;
    }

    public RadioButton getHardMode() {
        return hardMode;
    }

    public MenuBar getIntroMenu() { return introMenu;}

    public MenuItem getReload() { return reload;}
}
