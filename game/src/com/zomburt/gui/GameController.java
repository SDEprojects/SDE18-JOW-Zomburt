package com.zomburt.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


public class GameController {
    @FXML private TextField health;
    @FXML private TextField hydration;
    @FXML private ListView inventory;
    @FXML private ImageView image;
    @FXML private TextArea output;
    @FXML private TextArea input;
    @FXML private Button enter;

    public TextField getHealth() {
        return health;
    }

    public TextField getHydration() {
        return hydration;
    }

    public ListView getInventory() {
        return inventory;
    }

    public ImageView getImage() {
        return image;
    }

    public TextArea getOutput() {
        return output;
    }

    public TextArea getInput() {
        return input;
    }

    public Button getEnter() {
        return enter;
    }
}
