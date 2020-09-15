package com.zomburt.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class IntroController {

    @FXML private Button startGame;

    @FXML private Button videoButton;

    @FXML private ImageView intro;

    public IntroController() {
    }

    public Button getStartGame() {
        return startGame;
    }

    public Button getVideoButton() {return videoButton;}

    public ImageView getIntro() {
        return intro;
    }

}
