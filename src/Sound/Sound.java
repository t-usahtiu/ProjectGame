package Sound;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    public static Clip playGame() {
        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(new File("src/Sound/backSound.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(in);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Clip playGameFTD() {
        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(new File("src/Sound/backSound-ver2.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(in);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Clip playMenuStart() {
        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(new File("src/Sound/openingGame.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(in);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Clip playMenuStage() {
        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(new File("src/Sound/loadMap.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(in);
            clip.start();
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void playBombDrop() {
        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(new File("src/Sound/placeBomb.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(in);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playBombExplode() {
        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(new File("src/Sound/bombExplode.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(in);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playVictory() {
        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(new File("src/Sound/win.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(in);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playGameOver() {
        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(new File("src/Sound/endingGame.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(in);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Clip bomberWalk() {
        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(new File("src/Sound/running05.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(in);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}