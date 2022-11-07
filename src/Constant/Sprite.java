package Constant;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Sprite implements Const {

    public static BufferedImage[] playerAnimUp = new BufferedImage[3];
    public static BufferedImage[] playerAnimDown = new BufferedImage[3];
    public static BufferedImage[] playerAnimRight = new BufferedImage[3];
    public static BufferedImage[] playerAnimLeft = new BufferedImage[3];
    public static BufferedImage[] playerAnimDie = new BufferedImage[3];

    public static final int tileSizeBomberX = 17;
    public static final int tileSizeBomberY = 25;

    public static BufferedImage[] bombAnim = new BufferedImage[4];
    public static BufferedImage[] fontExplosion = new BufferedImage[4];
    public static BufferedImage[] brickExplosion = new BufferedImage[4];

    public static BufferedImage[] balloon = new BufferedImage[11];
    public static BufferedImage[] oneal = new BufferedImage[11];
    public static BufferedImage[] doria = new BufferedImage[11];
    public static BufferedImage[] ovape = new BufferedImage[11];
    public static BufferedImage[] minvo = new BufferedImage[11];

    public static BufferedImage imageBrick, imageWall, player, portal;

    public static BufferedImage speedItem, flameItem, bombItem;

    public static BufferedImage[] score = new BufferedImage[8];

    public Sprite() throws IOException {
        BufferedImage spriteSheet = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
        BufferedImage bomber = ImageIO.read(Objects.requireNonNull(getClass().getResource("/image/Bomberman.gif")));
        BufferedImage fire = ImageIO.read(Objects.requireNonNull(getClass().getResource("/image/fire.png")));
        imageBrick = ImageIO.read(Objects.requireNonNull(getClass().getResource("/image/brick.png")));
        imageWall = ImageIO.read(Objects.requireNonNull(getClass().getResource("/image/wall.png")));

        player = spriteSheet.getSubimage(4 * tileSize, 0, tileSize, tileSize);
        portal = spriteSheet.getSubimage(11 * tileSize, 3 * tileSize, tileSize, tileSize);

        speedItem = spriteSheet.getSubimage(2 * tileSize, 14 * tileSize, tileSize, tileSize);
        bombItem = spriteSheet.getSubimage(0, 14 * tileSize, tileSize, tileSize);
        flameItem = spriteSheet.getSubimage(tileSize, 14 * tileSize, tileSize, tileSize);

        fontExplosion[0] = fire.getSubimage(40, 32, 94, 132);
        fontExplosion[1] = fire.getSubimage(160, 32, 94, 132);
        fontExplosion[2] = fire.getSubimage(280, 32, 94, 132);
        fontExplosion[3] = fire.getSubimage(382, 32, 94, 132);

        playerAnimUp[0] = bomber.getSubimage(15 , 0, tileSizeBomberX, tileSizeBomberY);
        playerAnimUp[1] = bomber.getSubimage(15 + tileSizeBomberX, 0, tileSizeBomberX, tileSizeBomberY);
        playerAnimUp[2] = bomber.getSubimage(15 + 2 * tileSizeBomberX, 0, tileSizeBomberX, tileSizeBomberY);

        playerAnimRight[0] = bomber.getSubimage(69, 0, tileSizeBomberX,  tileSizeBomberY);
        playerAnimRight[1] = bomber.getSubimage( 69 + tileSizeBomberX, 0, tileSizeBomberX,  tileSizeBomberY);
        playerAnimRight[2] = bomber.getSubimage(69 + 2 * tileSizeBomberX, 0, tileSizeBomberX,  tileSizeBomberY);

        playerAnimDown[0] = bomber.getSubimage(125, 0, tileSizeBomberX,  tileSizeBomberY);
        playerAnimDown[1] = bomber.getSubimage(125 + tileSizeBomberX, 0, tileSizeBomberX,  tileSizeBomberY);
        playerAnimDown[2] = bomber.getSubimage(125 + 2 * tileSizeBomberX, 0, tileSizeBomberX,  tileSizeBomberY);

        playerAnimLeft[0] = bomber.getSubimage(179, 0, tileSizeBomberX,  tileSizeBomberY);
        playerAnimLeft[1] = bomber.getSubimage(179 + tileSizeBomberX, 0, tileSizeBomberX,  tileSizeBomberY);
        playerAnimLeft[2] = bomber.getSubimage(179 + 2 * tileSizeBomberX, 0, tileSizeBomberX,  tileSizeBomberY);

        playerAnimDie[0] = bomber.getSubimage(152, 175, tileSizeBomberX, tileSizeBomberY);
        playerAnimDie[1] = bomber.getSubimage(172, 175, 19, tileSizeBomberY);
        playerAnimDie[2] = bomber.getSubimage(192, 175, 23, tileSizeBomberY);

        for (int i = 0; i < 3; i++) {
            bombAnim[i] = spriteSheet.getSubimage(i * tileSize, 3 * tileSize, tileSize, tileSize);
//            brickExplosion[i] = spriteSheet.getSubimage((i + 5) * tileSize, 3 * tileSize, tileSize, tileSize);
        }

        for (int i = 0; i < 11; ++i) {
            balloon[i] = spriteSheet.getSubimage(i * tileSize, 15 * tileSize, tileSize, tileSize);
        }

        for (int i = 0; i < 7; ++i) {
            oneal[i] = spriteSheet.getSubimage(i * tileSize, 16 * tileSize, tileSize, tileSize);
            doria[i] = spriteSheet.getSubimage(i * tileSize, 19 * tileSize, tileSize, tileSize);
            ovape[i] = spriteSheet.getSubimage(i * tileSize, 20 * tileSize, tileSize, tileSize);
            minvo[i] = spriteSheet.getSubimage(i * tileSize, 18 * tileSize, tileSize, tileSize);
        }

        for (int i = 7; i < 11; ++i) {
            oneal[i] = spriteSheet.getSubimage(i * tileSize, 18 * tileSize, tileSize, tileSize);
            doria[i] = spriteSheet.getSubimage(i * tileSize, 18 * tileSize, tileSize, tileSize);
            ovape[i] = spriteSheet.getSubimage(i * tileSize, 17 * tileSize, tileSize, tileSize);
            minvo[i] = spriteSheet.getSubimage(i * tileSize, 15 * tileSize, tileSize, tileSize);
        }


        for (int i = 0; i < 4; ++i) {
            score[i] = spriteSheet.getSubimage(7 * tileSize, 21 * tileSize + i * tileSize / 2, tileSize, tileSize / 2);
            score[i + 4] = spriteSheet.getSubimage(8 * tileSize, 21 * tileSize + i * tileSize / 2, tileSize, tileSize / 2);
        }
    }
}
