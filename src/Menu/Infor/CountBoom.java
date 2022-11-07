package Menu.Infor;

import Constant.Sprite;
import font.FontGame;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class CountBoom extends Infor {
    public CountBoom(String text, int x, int y) {
        super(text, x, y);
    }

    @Override
    public void render(Graphics2D graphics2D) {
        super.render(graphics2D);
        graphics2D.drawString("Boom: " + text, x, y + size);
    }
}
