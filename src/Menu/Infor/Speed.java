package Menu.Infor;

import java.awt.*;

public class Speed extends Infor{
    public Speed(String text, int x, int y) {
        super(text, x, y);
    }

    @Override
    public void render(Graphics2D graphics2D) {
        super.render(graphics2D);
        graphics2D.drawString("Speed: " + text, x, y + size);
    }
}
