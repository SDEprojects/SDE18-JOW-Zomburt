package com.zomburt.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;


public class GameController {
    @FXML private TextField health;
    @FXML private ListView inventory;
    @FXML private ListView roomInventory;
    @FXML private ListView weaponsRoom;
    @FXML private ImageView image1;
    @FXML private ImageView image2;
    @FXML private ImageView victory;
    @FXML private TextArea output;
    @FXML private TextField input;
    @FXML private Button enter;
    @FXML private Button mapButton;
    @FXML private TextField remainZombies;
    @FXML private TextField currentLocation;
    @FXML private TextField fightingZombie;
    @FXML private TextField zombieHealth;
    @FXML private TextField zombieWeapon;
    @FXML private TextField score;
    @FXML private TextArea youLose;

    public TextField getHealth() {
        return health;
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

    public ImageView getVictory() {return victory;}

    public TextField getRemainZombies() {
        return remainZombies;
    }

    public TextField getCurrentLocation() {
        return currentLocation;
    }

    public TextField getFightingZombie() {
        return fightingZombie;
    }

    public TextField getZombieHealth() {
        return zombieHealth;
    }

    public TextField getZombieWeapon() {
        return zombieWeapon;
    }

    public TextField getScore() {
        return score;
    }

    public ListView getRoomInventory() {
        return roomInventory;
    }

    public ListView getWeaponsRoom() {
        return weaponsRoom;
    }

    public TextArea getYouLose() {
        return youLose;
    }
}
