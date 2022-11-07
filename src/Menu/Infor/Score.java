package Menu.Infor;

import java.awt.*;

public class Score extends Infor{
    public Score(String text, int x, int y) {
        super(text, x, y);
    }

    @Override
    public void render(Graphics2D graphics2D) {
        super.render(graphics2D);
        graphics2D.drawString("Score: " + text, x, y + size);
    }
}
