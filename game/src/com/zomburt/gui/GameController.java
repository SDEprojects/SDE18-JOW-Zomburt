package com.zomburt.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;


public class GameController {
    @FXML private TextField health;
    @FXML private TextField hydration;
    @FXML private ListView inventory;
    @FXML private ImageView image1;
    @FXML private ImageView image2;

    @FXML private TextArea output;
    @FXML private TextField input;
    @FXML private Button enter;
    @FXML private Button mapButton;

    public TextField getHealth() {
        return health;
    }

    public TextField getHydration() {
        return hydration;
    }

    public ListView getInventory() {
        return inventory;
    }

    public ImageView getImage1() {
        return image1;
    }

    public void setImage1(ImageView image1) {
        this.image1 = image1;
    }

    public ImageView getImage2() {
        return image2;
    }

    public void setImage2(ImageView image2) {
        this.image2 = image2;
    }

    public TextArea getOutput() {
        return output;
    }

    public TextField getInput() {
        return input;
    }

    public Button getEnter() {
        return enter;
    }

    public Button getMapButton() {return mapButton;}
}
