package Main;

import Sound.Sound;

import javax.sound.sampled.Clip;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class KeyHandler implements KeyListener {
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private boolean boom;
    private Clip clipWalk;
    private boolean soundWalk = false;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
            this.downPressed = true;

            if (!soundWalk) {
                clipWalk = Sound.bomberWalk();
                soundWalk = true;
            }
        }
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
            this.upPressed = true;

            if (!soundWalk) {
                clipWalk = Sound.bomberWalk();
                soundWalk = true;
            }
        }
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
            this.leftPressed = true;

            if (!soundWalk) {
                clipWalk = Sound.bomberWalk();
                soundWalk = true;
            }
        }
        if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
            this.rightPressed = true;

            if (!soundWalk) {
                clipWalk = Sound.bomberWalk();
                soundWalk = true;
            }
        }
        if (keyCode == KeyEvent.VK_SPACE) {
            this.boom = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
            this.downPressed = false;

            if (soundWalk) {
                clipWalk.stop();
                soundWalk = false;
            }
        }
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
            this.upPressed = false;

            if (soundWalk) {
                clipWalk.stop();
                soundWalk = false;
            }
        }
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
            this.leftPressed = false;

            if (soundWalk) {
                clipWalk.stop();
                soundWalk = false;
            }
        }
        if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
            this.rightPressed = false;

            if (soundWalk) {
                clipWalk.stop();
                soundWalk = false;
            }
        }
        this.boom = false;
    }

    private void setupPressed() {
        this.downPressed = false;
        this.rightPressed = false;
        this.upPressed = false;
        this.leftPressed = false;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isBoom() {
        return boom;
    }
}