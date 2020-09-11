package com.zomburt.gui;

import com.zomburt.GameEngine;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GameApp extends Application {
    private IntroController introController;
    private GameController  gameController;
    private String currentInput;
    private static com.zomburt.gui.GameApp instance;

    public GameApp() {
        instance = this;
    }

    public static GameApp getInstance() {
        return instance;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load intro layout from fxml file
        FXMLLoader introLoader = new FXMLLoader();
        introController = new IntroController();
        introLoader.setController(introController);
        introLoader.setLocation( com.zomburt.gui.GameApp.class.getResource("intro.fxml"));
        VBox introLayout = introLoader.load();
        getIntro(new File("./game/assets/GameStartFile.txt"));

        // Show the scene containing the intro layout.
        Scene introScene = new Scene(introLayout);
        primaryStage.setScene(introScene);
        primaryStage.show();

        // config next button listener
        EventHandler<ActionEvent> nextHandler =
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    FXMLLoader gameViewLoader = new FXMLLoader();
                    try {
                        gameController = new GameController();
                        gameViewLoader.setController(gameController);
                        gameViewLoader.setLocation(com.zomburt.gui.GameApp.class.getResource("game.fxml"));
                        VBox gameLayout = gameViewLoader.load();
                        // Show the scene containing the root layout.
                        Scene gameScene = new Scene(gameLayout);
                        Stage gameStage = new Stage();
                        gameStage.setScene(gameScene);
                        //hide intro scence
                        introScene.getWindow().hide();
                        // show game scene
                        gameStage.show();
                        // start the background game thread
                        runGameThread();
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
        GameEngine newGame = new GameEngine();
        newGame.run();
    }

    // update UI status
    public void updateUI() {
        // update player status
        Platform.runLater(
            new Runnable() {
                @Override
                public void run() {

                }
            });

        // clear item in the list view
        Platform.runLater(
            new Runnable() {
                @Override
                public void run() {
                    try {
                        gameController.getInventory().getItems().clear();
                    } catch (Exception e) {
                        e.printStackTrace();
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
                inputSignal.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void notifyInput() {
        synchronized (inputSignal) {
            inputSignal.notify();
        }
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

    public void getIntro(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                introController.getIntro().appendText(line + "\n");
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
}
