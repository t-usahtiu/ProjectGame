package Menu.Infor;

import java.awt.*;

public class Life extends Infor{
    public Life(String text, int x, int y) {
        super(text, x, y);
    }

    @Override
    public void render(Graphics2D graphics2D) {
        super.render(graphics2D);
        graphics2D.drawString("Life: " + text, x, y + size);
    }
}
