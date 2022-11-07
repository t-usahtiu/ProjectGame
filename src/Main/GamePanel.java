package Main;

import Character.Bomber.AIBomber;
import Character.Bomber.Bomber;
import Character.Monster.*;
import Constant.Const;
import Menu.Infor.*;
import Menu.Infor.Frame;
import Menu.Start.Stage;
import Menu.Start.MenuStart;
import Sound.Sound;
import StillEntity.Item.*;
import StillEntity.Map;
import StillEntity.Still;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GamePanel extends JPanel implements Const, Runnable {
    boolean isRunning;
    Thread thread;

    protected int screenWidth = 16 * 31 * 3;
    protected int screenHeight = 16 * 13 * 3 + 40;
    private static int level = 0;
    private static int score = 0;

    protected BufferedImage view;

    private final KeyHandler keyHandler = new KeyHandler();
    private final MouseHandler mouseHandler = new MouseHandler();

    private Bomber bomber = new Bomber();
    private final ArrayList<Monster> monsters = new ArrayList<>();
    Map map = new Map();
    private boolean AI = false;
    private AIBomber aiBomber;
    private Time time = new Time();
    Stage startScreen;
    MenuStart menuStart;
    private int iXportal, iYportal;
    private Clip clipMenuStart, clipGame, clipGameV2;
    private boolean soundMenuStart = false, soundGame = false, soundGameV2 = false,
            soundBomberDie = false, findTheDoor = false;
    private static int countBoom = Bomber.getCountBoom();

    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            isRunning = true;
            thread.start();
        }
    }

    public GamePanel() {
        menuStart = new MenuStart(screenWidth, screenHeight);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);
    }

    public void start() {
        view = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void run() {
        try {
            requestFocus();
            start();
            while (isRunning) {
                update();
                draw();
                if (this.monsters.isEmpty() && Map.getSizeStill() == 1) {
                    if (Map.getStill(0).getX() == bomber.getX() / size && Map.getStill(0).getY() == bomber.getY() / size) {
                        this.nextStage();
                    }
                }
                Thread.sleep(1000 / 60);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(bomber.getBomberDie()) {
            if(Bomber.getLife() > 1) {
                level--;
                Bomber.setLife();
                this.monsters.clear();
                bomber = new Bomber();
                this.nextStage();
            } else {
                MenuStart.setHidden(false);
                this.monsters.clear();
                bomber = new Bomber();
                Bomber.setLife(3);
                level = 0;
                menuStart = new MenuStart(screenWidth, screenHeight);
            }
        }

        if (!MenuStart.isHidden()) {
            menuStart.update(mouseHandler);
            AI = menuStart.isAI();
            if (AI) {
                aiBomber = new AIBomber(bomber, map, monsters);
            }
            if (!soundMenuStart) {
                clipMenuStart = Sound.playMenuStart();
                soundMenuStart = true;
            }

            if (MenuStart.isHidden()) {
                this.nextStage();
                clipMenuStart.stop();
            }
            return;
        }
        if (!startScreen.isHidden()) {
            soundGame = false;
            soundGameV2 = false;
            soundBomberDie = false;
            findTheDoor = false;
            return;
        } else {
            if (!soundGame) {
                clipGame = Sound.playGame();
                soundGame = true;
            }
            if (time.check(350)) {
                if (!soundGameV2 && findTheDoor) {
                    clipGame.stop();
                    clipGameV2 = Sound.playGameFTD();
                    soundGameV2 = true;
                }
            }
            if (this.bomber.getDie()) {
                this.bomber.update();

                if (!soundBomberDie) {
                    clipGame.stop();
                    if (clipGameV2 != null) {
                        clipGameV2.stop();
                    }
                    soundBomberDie = true;
                }

                return;
            }
        }
        if (AI) {
            aiBomber.update();
        } else {
            this.bomber.setDirect();
            if (this.keyHandler.isLeftPressed()) {
                this.bomber.setDirectLeft();
            } else if (this.keyHandler.isRightPressed()) {
                this.bomber.setDirectRight();
            } else if (this.keyHandler.isUpPressed()) {
                this.bomber.setDirectUp();
            } else if (this.keyHandler.isDownPressed()) {
                this.bomber.setDirectDown();
            }
        }

        if (this.keyHandler.isBoom()) {
            if (Map.getMap(this.bomber.getX() / size, this.bomber.getY() / size) != hashCodeBoom) {
                if (GamePanel.countBoom > 0) {
                    Map.setMap(this.bomber.getX() / size, this.bomber.getY() / size, 3);
                    GamePanel.countBoom--;

                    int iXbomb = this.bomber.getX() / size;
                    int iYbomb = this.bomber.getY() / size;

                    if ((iXbomb + 1 == iXportal && iYbomb == iYportal) ||
                            (iXbomb - 1 == iXportal && iYbomb == iYportal) ||
                            (iXbomb == iXportal && iYbomb + 1 == iYportal) ||
                            (iXbomb == iXportal && iYbomb - 1 == iYportal)) {
                        findTheDoor = true;
                    }
                }
            }
        }

        this.bomber.update();

        for (Monster monster : monsters) {
            monster.move(bomber.getX(), bomber.getY());
            monster.update();
            if (monster.getX() / size == bomber.getX() / size && monster.getY() / size == bomber.getY() / size) {
                if (!monster.isDie()) bomber.setDie();
            }
        }

        for (Monster monster : monsters) {
            if (monster.getDie()) {
                score += monster.getScore();
            }
        }

        monsters.removeIf(monster -> monster.getDie());

        for (int i = 0; ; ++i) {
            Still still = Map.getStill(i);

            if (still == null) return;
            if (still instanceof Portal) continue;

            Item item = (Item) still;
            item.update(this.bomber);
        }

    }

    public void draw() {
        Graphics2D g2 = (Graphics2D) view.getGraphics();

        if (!MenuStart.isHidden()) {
            menuStart.render(g2);
        } else if (!startScreen.isHidden()) {
            g2.setColor(Color.black);
            g2.fillRect(0, 0, screenWidth, screenHeight);
            startScreen.update();
            startScreen.render(g2);
        } else {
            g2.setColor(new Color(56, 135, 0));
            g2.fillRect(0, 0, screenWidth, screenHeight);
            map.render(g2);
            this.bomber.render(g2);
            for (Monster monster : monsters) {
                monster.render(g2);
            }

            this.renderInfo(g2);
        }

        Graphics g = getGraphics();
        g.drawImage(view, 0, 0, screenWidth, screenHeight, null);
        g.dispose();
    }

    public void renderInfo(Graphics2D g2) {
        Infor infor = new CountBoom(Integer.toString(getCountBoom()), 0,  screenHeight - 40);
        infor.render(g2);
        infor = new Frame(Integer.toString(Bomber.getLine()), size * 4, screenHeight - 40);
        infor.render(g2);
        infor = new Speed(Integer.toString(bomber.getSpeed()), size * 8, screenHeight - 40);
        infor.render(g2);
        infor = new CountMonster(Integer.toString(monsters.size()), size * 12, screenHeight - 40);
        infor.render(g2);
        infor = new Life(Integer.toString(Bomber.getLife()), size * 17, screenHeight - 40);
        infor.render(g2);
        infor = new Score(Integer.toString(score), size * 21, screenHeight - 40);
        infor.render(g2);
    }

    public static void setCountBoom() {
        if (GamePanel.countBoom + 1 > Bomber.getCountBoom()) {
            return;
        }
        GamePanel.countBoom++;
    }

    public static int getCountBoom() {
        return countBoom;
    }

    public static int getLevel() {
        return level;
    }

    public static void setScore(int score) {
        GamePanel.score += score;
    }

    public void nextStage() {
        map.nextStage();

        startScreen = new Stage("STAGE " + (level + 1));

        try {
            Scanner in = new Scanner(new File("src/Stage/stage" + (level + 1) + ".txt"));

            level = in.nextInt();
            int height = in.nextInt();
            int width = in.nextInt();
            screenHeight = height * size + 40;
            screenWidth = width * size;
            map.setWidth(width);
            map.setHeight(height);
            in.nextLine();

            for (int j = 0; j < height; ++j) {
                String line = in.nextLine();
                for (int i = 0; i < width; ++i) {
                    if (line.charAt(i) == ' ') {
                        Map.setMap(i, j, hashCodeGrass);
                    } else if (line.charAt(i) == '#') {
                        Map.setMap(i, j, hashCodeWall);
                    } else if (line.charAt(i) == 'p') {
                        this.bomber.setX(i * size);
                        this.bomber.setY(j * size);
                    } else if (line.charAt(i) == '1') {
                        this.monsters.add(new Balloon(i * size, j * size));
                    } else if (line.charAt(i) == '2') {
                        this.monsters.add(new Oneal(i * size, j * size));
                    } else if (line.charAt(i) == '3') {
                        this.monsters.add(new Doria(i * size, j * size));
                    } else if (line.charAt(i) == '4') {
                        this.monsters.add((new Ovape(i * size, j * size)));
                    } else if (line.charAt(i) == '5') {
                        this.monsters.add(new Minvo(i * size, j * size));
                    } else {
                        Map.setMap(i, j, hashCodeBrick);
                        if (line.charAt(i) == 'x') {
                            Map.addStill(new Portal(i, j));
                            iXportal = i;
                            iYportal = j;
                        } else if (line.charAt(i) == 'b') {
                            Map.addStill(new BombItem(i * size, j * size));
                        } else if (line.charAt(i) == 's') {
                            Map.addStill(new SpeedItem(i * size, j * size));
                        } else if (line.charAt(i) == 'f') {
                            Map.addStill(new FlameItem(i * size, j * size));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    }
}
