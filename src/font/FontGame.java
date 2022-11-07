package font;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontGame {
    private static Font myFont;

    public static Font getFontEmulator(float size) {
        try {
            InputStream is = FontGame.class.getResourceAsStream("/font/PixelEmulator-xq08.ttf");
            myFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(size);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return myFont;
    }

    public static Font getFontRush(float size) {
        try {
            InputStream is = FontGame.class.getResourceAsStream("/font/SpeedRush-JRKVB.ttf");
            myFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(size);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return myFont;
    }

    public static Font getFontGood(float size) {
        try {
            InputStream is = FontGame.class.getResourceAsStream("/font/PacfontGood-yYye.ttf");
            myFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(size);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return myFont;
    }
}
