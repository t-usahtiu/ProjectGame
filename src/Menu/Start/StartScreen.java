package Menu.Start;

import Main.Time;
import Sound.Sound;

import java.awt.*;

public class StartScreen {
    private Time time = new Time();
    private boolean hidden = false;
    private String text;

    public StartScreen(String text) {
        this.text = text;
    }

    public void update() {
        if(time.check(300)) {
            hidden = true;
        }
    }

    public void render(Graphics2D g2) {
        if(hidden) return;
        g2.setColor(Color.WHITE);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", Font.PLAIN, 40);
        g2.setFont(font);
        g2.drawString(text, 600, 312);
    }

    public boolean isHidden() {
        return hidden;
    }
}
