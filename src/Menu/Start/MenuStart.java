package Menu.Start;

import Main.MouseHandler;
import Sound.Sound;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class MenuStart {
    private BufferedImage background, start, logo, checkbox_true, checkbox_false, _AI;

    private int screenWidth;
    private int screenHeight;

    private boolean AI = false;

    private static boolean hidden = false;

    public MenuStart(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight - 40;

        try {
            this.background = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Image/background.png")));
            this.logo = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Image/logoGame.png")));
            this.start = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Image/start.png")));
            this.checkbox_true = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Image/checkboxTrue.png")));
            this.checkbox_false = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Image/checkboxFalse.png")));
            this._AI = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Image/AI.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(MouseHandler mouse) {
        int startX = (screenWidth - this.start.getWidth()) / 2;
        int startY = 20 + logo.getHeight();
        if(!mouse.isMouseClick()) return;
        if(startX <= mouse.getMouseX() && mouse.getMouseY() <= startX + start.getWidth()){
            if(startY <= mouse.getMouseY() && mouse.getMouseY() <= startY + start.getHeight()) {
                hidden = true;
            }
        }
        startX = 1;
        startY = 50;

        if(startX <= mouse.getMouseX() && mouse.getMouseX() <= startX + 50) {
            if(startY <= mouse.getMouseY() && mouse.getMouseY() <= startY + 50) {
                AI = !AI;
            }
        }
        mouse.setMouse();
    }

    public void render(Graphics2D g2) {
        if (hidden) return;

        g2.drawImage(this.background, 0, 0, screenWidth, screenHeight, null);
        g2.drawImage(this.logo, (screenWidth - this.logo.getWidth() * 2) / 2, 17, logo.getWidth() * 2, logo.getHeight() * 2, null);
        g2.drawImage(this.start, (screenWidth - this.start.getWidth()) / 2, 20 + logo.getHeight(), start.getWidth(), start.getHeight(), null);
        if(!AI) g2.drawImage(this.checkbox_false, 1, 50, 50, 50, null);
        else g2.drawImage(this.checkbox_true, 1, 50, 50, 50, null);
        g2.drawImage(this._AI, 50, 50, 50, 50, null);

    }

    public static boolean isHidden() {
        return hidden;
    }

    public static void setHidden(boolean hidden) {
        MenuStart.hidden = hidden;
    }

    public boolean isAI() {
        return AI;
    }
}
