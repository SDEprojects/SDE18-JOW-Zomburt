package com.zomburt.gui;

import com.zomburt.GameEngine;
import com.zomburt.MapFactory;
import com.zomburt.Mode;
import com.zomburt.characters.Zombie;
import com.zomburt.combat.Weapon;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GameApp extends Application{
    private IntroController introController;
    private VideoController videoController;
    private GameController gameController;
    private MapController mapController;
    private String currentInput;
    private Mode modeInput = Mode.EASY;
    private GameEngine newGame;
    private static GameApp instance;
    private boolean pendingInput = false;

    public GameApp() {
        instance = this;
    }

    public static GameApp getInstance() {
        return instance;
    }

    public static GameEngine getEngine() {
        return getInstance().getGameEngine();
    }

    public GameEngine getGameEngine() {
        return newGame;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load intro layout from fxml file
        FXMLLoader introLoader = new FXMLLoader();
        introController = new IntroController();
        introLoader.setController(introController);
        introLoader.setLocation(com.zomburt.gui.GameApp.class.getResource("intro.fxml"));
        VBox introLayout = introLoader.load();
        try {
            introController.getIntro().setImage(new Image("file:./game/assets/zombie.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Show the scene containing the intro layout.
        Scene introScene = new Scene(introLayout);
        primaryStage.setScene(introScene);
        primaryStage.show();

        // config the radio button
        introController.getEasyMode().selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    introController.getHardMode().setSelected(false);
                    modeInput = Mode.EASY;
                    System.out.println(modeInput.toString());
                }
            }
        });

        introController.getHardMode().selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    introController.getEasyMode().setSelected(false);
                    modeInput = Mode.HARD;
                }
            }
        });

        // config startGame button listener
        EventHandler<ActionEvent> nextHandler =
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        FXMLLoader gameViewLoader = new FXMLLoader();
                        try {
                            gameController = new GameController();
                            gameViewLoader.setController(gameController);
                            gameViewLoader.setLocation(com.zomburt.gui.GameApp.class.getResource("game2.fxml"));
                            GridPane gameLayout = gameViewLoader.load();
                            //load pictures
                            try {
                                gameController.getImage1().setImage(new Image("file:./game/assets/ParkingLot1.jpg"));
                                gameController.getImage2().setImage(new Image("file:./game/assets/ShoppingMall.jpg"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //show game map when click map button
                            gameController.getMapButton().setOnAction(e -> {
                                FXMLLoader mapViewLoader = new FXMLLoader();
                                try {
                                    mapController = new MapController();
                                    mapViewLoader.setController(mapController);
                                    mapViewLoader.setLocation(GameApp.class.getResource("map.fxml"));
                                    GridPane mapLayout = mapViewLoader.load();
                                    try {
                                        mapController.getGameMap().setImage(new Image("file:./game/assets/new_map_Zomburt.png"));
                                    } catch (Exception exception) {
                                        exception.printStackTrace();
                                    }
                                    Scene mapScene = new Scene(mapLayout);
                                    Stage mapStage = new Stage();
                                    mapStage.setScene(mapScene);
                                    mapStage.show();
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            });

                            //quit the game when click quit option in menu bar
                            gameController.getMenu().getMenus().get(0).getItems().get(2).setOnAction(e->{
                                System.exit(0);
                            });

                            // Show the scene containing the root layout.
                            Scene gameScene = new Scene(gameLayout);
                            Stage gameStage = new Stage();
                            gameStage.setScene(gameScene);
                            //hide intro scene
                            introScene.getWindow().hide();
                            // show game scene
                            gameStage.show();
                            // start the background game thread
                            runGameThread();

                            //save game when click save option in menu bar
                            gameController.getMenu().getMenus().get(0).getItems().get(0).setOnAction(e->{
                                 Serializing s = new Serializing();
                                 s.saveGameSate(newGame.getGameState());
                            });

                            //reload game when click resume option in menu bar
                            gameController.getMenu().getMenus().get(0).getItems().get(1).setOnAction(e->{
                                 Serializing s = new Serializing();
                                 newGame.restoreGameState(s.loadGameState());
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };

        // connect next button to the handler event
        introController.getStartGame().setOnAction(nextHandler);
    }

    private void runGameThread() {
        EventHandler<ActionEvent> eventHandler =
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        currentInput = gameController.getInput().getText().trim();
                        notifyInput();
                        gameController.getInput().clear();
                        gameController.getInput().requestFocus();
                    }
                };

        EventHandler<KeyEvent> enterPressedHandler =
                keyEvent -> {
                    if (keyEvent.getCode() == KeyCode.ENTER) {
                        currentInput = gameController.getInput().getText().trim();
                        notifyInput();
                        gameController.getInput().clear();
                        gameController.getInput().requestFocus();
                    }
                };

        gameController.getInput().setOnKeyPressed(enterPressedHandler);

        gameController.getEnter().setOnAction(eventHandler);
        Thread gameLoop =
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    executeGameLoop();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });

        // don't let thread prevent JVM shutdown
        gameLoop.setDaemon(true);
        gameLoop.start();
    }

    // game thread logic, so we should also wrap the UI access calls
    private void executeGameLoop() throws Exception {
        MapFactory mapFactory = MapFactory.getInstance();
        mapFactory.createMap("./game/assets/store.json");
        newGame = new GameEngine();
        newGame.totalNumZombies = mapFactory.getTotalNumZombies();
        newGame.run();
    }

    // update UI status
    public void updateUI() {
        // update total number of remaing zombies
        Platform.runLater(
                new Runnable() {
                    @Override
                    public void run() {
                       gameController.getRemainZombies().setText(Integer.toString(newGame.totalNumZombies));
                       gameController.getHealth().setText(Integer.toString(newGame.player.getHealth()));
                       gameController.getScore().setText(Integer.toString(newGame.player.getScore()));
                       gameController.getCurrentLocation().setText(newGame.currentScene.getSceneName());
                    }
                });

        // update list view
        Platform.runLater(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            gameController.getInventory().getItems().clear();
                            for (Weapon weapon : newGame.player.getInventory()) {
                                gameController.getInventory().getItems().add(weapon.getName() + "(" + weapon.getDamage() + ")");
                            }

                            gameController.getRoomInventory().getItems().clear();
                            gameController.getWeaponsRoom().getItems().clear();
                            for (Weapon weapon : newGame.currentScene.getRoomLoot()) {
                                gameController.getWeaponsRoom().getItems().add(weapon.getName() + "(" + weapon.getDamage() + ")");
                            }
                            for (Zombie zombie : newGame.currentScene.getFeature()) {
                                gameController.getRoomInventory().getItems().add(zombie.getName());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    // update game status LOST
    public void updateGameStatusLost() {
        // update total number of remaing zombies
        Platform.runLater(
            new Runnable() {
                @Override
                public void run() {
                    gameController.getYouLose().setVisible(true);
                }
            });
    }

    // update game status WIN
    public void updateGameStatusWON() {
        // update total number of remaing zombies
        Platform.runLater(
            new Runnable() {
                @Override
                public void run() {
                    gameController.getYouLose().clear();
                    gameController.getYouLose().setText("Good Job!! Mission Completed!");
                    gameController.getYouLose().setVisible(true);
                }
            });
    }

    // update zombies status
    public void updateZombie() {
        // update total number of remaing zombies
        Platform.runLater(
            new Runnable() {
                @Override
                public void run() {
                    if (newGame.zombie != null && newGame.zombie.getHealth() > 0) {
                        gameController.getFightingZombie().setText(newGame.zombie.getName());
                    } else {
                        gameController.getFightingZombie().setText("None");
                    }

                    if (newGame.zombie != null) {
                        gameController.getZombieHealth().setText(Integer.toString(newGame.zombie.getHealth()));
                    } else {
                        gameController.getZombieHealth().setText("");
                    }

                    if (newGame.zombie != null && newGame.zombie.getInventory().size() > 0 && newGame.zombie.getHealth() > 0) {
                        gameController.getZombieWeapon().setText(newGame.zombie.getInventory().get(0).getName() +"(" + newGame.zombie.getInventory().get(0).getDamage() + ")");
                    }

                    if (newGame.zombie == null || newGame.zombie.getInventory().size() == 0) {
                        gameController.getZombieWeapon().setText("None");
                    }
                }
            });
    }
    // inner input signal Class
    private final InputSignal inputSignal = new InputSignal();

    public static class InputSignal {
    }

    public void waitInput() {
        synchronized (inputSignal) {
            try {
                pendingInput = true;
                inputSignal.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void notifyInput() {
        synchronized (inputSignal) {
            pendingInput = false;
            inputSignal.notify();
        }
    }

    public boolean isPendingInput() {
        return pendingInput;
    }

    // call from game logic thread to get the input
    public String getInput() {
        waitInput();
        return currentInput;
    }

    public void getNarrative(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                gameController.getOutput().appendText(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // call from game logic thread to update the UI for current activity
    public void appendToCurActivity(String txt) {
        Platform.runLater(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            gameController.getOutput().appendText(txt + "\n");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }



    public GameController getGameController() {
        return gameController;
    }

    public Mode getModeInput() {
        return modeInput;
    }

    public MapController getMapController() {
        return mapController;
    }
}
