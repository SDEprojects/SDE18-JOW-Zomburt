package com.zomburt.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;

public class IntroController {

    @FXML private Button startGame;
    @FXML private ImageView intro;
    @FXML private RadioButton easyMode;
    @FXML private RadioButton hardMode;
    @FXML private Menu menu;
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

    public Menu getMenu() { return menu;}

    public MenuItem getReload() { return reload;}
}
