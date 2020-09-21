package com.zomburt.utility;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound extends Thread{

    String status;

    public Sound(String status) {
      this.status = status;
      setName(status);
      chooseFile(status);
    }

    @Override
    public void run() {
        File localFile = new File(chooseFile(status));

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

    public String chooseFile(String str){
        String path = "";
        String[] files = {
                "./game/assets/applause.wav",
                "./game/assets/Super Mario Lose Life.wav",
                "./game/assets/punch.wav",
                "./game/assets/suspense.wav"
        };
        if(str.equals("win"))
           path = files[0];
        else if (str.equals("lose"))
           path = files[1];
        else if (str.equals("combat"))
           path = files[2];
        else if (str.equals("intro"))
           path = files[3];

        return path;
    }

    public static void playSound(String status) {
        Thread soundThread = new Thread(new Sound(status));
        soundThread.start();
    }

}
