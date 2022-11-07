package Menu.Infor;

import Constant.Const;
import font.FontGame;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Infor {
    protected BufferedImage image;
    protected int x;
    protected int y;
    protected int size = Const.size * 2 / 3;
    protected String text = "";

    public Infor(String  text, int x, int y) {
        this.x = x;
        this.y = y;
        this.text = text;
    }

    public void render(Graphics2D graphics2D){
        graphics2D.setColor(Color.WHITE);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = FontGame.getFontRush(size);
        graphics2D.setFont(font);
    }
}
