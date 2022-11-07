package Menu.Infor;

import java.awt.*;

public class Frame extends Infor{
    public Frame(String text, int x, int y) {
        super(text, x, y);
    }

    @Override
    public void render(Graphics2D graphics2D) {
        super.render(graphics2D);
        graphics2D.drawString("Frame: " + text, x, y + size);
    }
}
