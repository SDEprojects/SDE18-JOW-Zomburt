package com.zomburt.utility;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound implements Runnable{

    @Override
    public void run() {
        File localFile = new File("./game/assets/soundeffect.wav");
     //   File localFile = new File("./game/assets/levelclearer.wav");

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

    public static void playSoundAtEnd() {
        Thread soundThread = new Thread(new Sound());
        soundThread.start();
    }

    public static void main(String[] args) {
        Sound audio = new Sound();
        audio.playSoundAtEnd();
    }
}
