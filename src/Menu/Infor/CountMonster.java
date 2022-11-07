package Menu.Infor;

import font.FontGame;

import java.awt.*;

public class CountMonster extends Infor{
    public CountMonster(String text, int x, int y) {
        super(text, x, y);
    }

    @Override
    public void render(Graphics2D graphics2D) {
        super.render(graphics2D);
        graphics2D.drawString("Monsters: " + text, x, y + size);
    }
}
