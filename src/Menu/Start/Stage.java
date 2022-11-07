package Menu.Start;

import Main.Time;
import Sound.Sound;
import font.FontGame;

import java.awt.*;

public class Stage {
    private Time time = new Time();
    private boolean hidden = false;
    private String text;

    public Stage(String text) {
        this.text = text;
        Sound.playMenuStage();
    }

    public void update() {
        if(time.check(200)) {
            hidden = true;
        }
    }

    public void render(Graphics2D g2) {
        if(hidden) return;
        g2.setColor(Color.WHITE);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = FontGame.getFontEmulator(60f);
        g2.setFont(font);
        g2.drawString(text, 600, 312);
    }

    public boolean isHidden() {
        return hidden;
    }
}
