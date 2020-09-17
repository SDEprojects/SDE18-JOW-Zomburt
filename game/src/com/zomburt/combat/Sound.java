package com.zomburt.combat;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound implements Runnable{

    @Override
    public void run() {
        File localFile = new File("./game/assets/test.wav");

        try {
            AudioInputStream mySoundFile = AudioSystem.getAudioInputStream(localFile);
            Clip mySound = AudioSystem.getClip();
            mySound.open(mySoundFile);
            mySound.start();
            Thread.sleep(6000);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void playSound() {
        Thread soundThread = new Thread(new Sound());
        soundThread.start();
    }

    public static void main(String[] args) {
        Sound sound = new Sound();
        sound.playSound();
    }
}
