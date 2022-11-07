package Character.Bomber;

import Character.Monster.Monster;
import Constant.Const;
import StillEntity.Map;
import StillEntity.Still;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class AIBomber implements Const {
    private Bomber bomber;
    private Map map;
    private ArrayList<Monster> monsters;
    private final ArrayList<Integer> direct = new ArrayList<>();

    private int nextX, nextY;

    protected boolean directRight, directLeft, directUp, directDown;

    public AIBomber(Bomber bomber, Map map, ArrayList<Monster> monsters) {
        this.bomber = bomber;
        this.map = map;
        this.monsters = monsters;
    }

    public void update() {
        move();

        this.bomber.setDirect();
        int tmp = direct.get(direct.size() - 1);
        direct.remove(direct.size() - 1);
        this.setDirect();

        if (tmp == 1) {
            this.bomber.setDirectLeft();
        } else if (tmp == 2) {
            this.bomber.setDirectRight();
        } else if (tmp == 8) {
            this.bomber.setDirectUp();
        } else if (tmp == 16) {
            this.bomber.setDirectDown();
        }
    }

    public void move() {
        int[][] f = new int[Map.getWidth()][Map.getHeight()];
        for (int[] ints : f) {
            Arrays.fill(ints, -1);
        }

        Queue<Point> queue = new LinkedList<>();

        queue.add(new Point(bomber.getX() / size, bomber.getY() / size));

        int tmpX = -1, tmpY = -1;

        while (true) {
            int x = (int) queue.peek().getX();
            int y = (int) queue.peek().getY();
            queue.remove();

            if (check(x, y)){
                tmpX = x;
                tmpY = y;

                nextX = tmpX;
                nextY = tmpY;

                break;
            }

            if (Map.getMap(x + 1, y) != 3 && Map.getMap(x + 1, y) != 1 && f[x + 1][y] == -1) {
                f[x + 1][y] = f[x][y] + 1;
                queue.add(new Point(x + 1, y));
            }
            if (Map.getMap(x - 1, y) != 3 && Map.getMap(x - 1, y) != 1 && f[x - 1][y] == -1) {
                f[x - 1][y] = f[x][y] + 1;
                queue.add(new Point(x - 1, y));
            }
            if (Map.getMap(x, y + 1) != 3 && Map.getMap(x, y + 1) != 1 && f[x][y + 1] == -1) {
                f[x][tmpY + 1] = f[x][y] + 1;
                queue.add(new Point(x, y + 1));
            }
            if (Map.getMap(x, y - 1) !=3 && Map.getMap(x, y - 1) !=1 && f[x][y - 1] == -1) {
                f[x][y - 1] = f[x][y] + 1;
                queue.add(new Point(x, y - 1));
            }
        }

        while(f[tmpX][tmpY] != 0) {
            if(f[tmpX + 1][tmpY] == (f[tmpX][tmpY] - 1)) {
                direct.add(1);
                ++tmpX;
            } else if(f[tmpX - 1][tmpY] == f[tmpX][tmpY] - 1) {
                direct.add(2);
                --tmpX;
            } else if(f[tmpX][tmpY+1] == f[tmpX][tmpY] - 1) {
                direct.add(8);
                ++tmpY;
            }else if(f[tmpX][tmpY-1] == f[tmpX][tmpY] - 1) {
                direct.add(16);
                --tmpY;
            }
        }
    }

    public boolean check(int x, int y) {
        for (Monster monster : monsters) {
            if (monster.getX() / size == x && monster.getY() / size == y){
                return true;
            }
        }

        for (int i = 0; ; ++i) {
            Still still = Map.getStill(i);

            if (still == null) return false;

            if(still.getX() == x && still.getY() == y){
                return true;
            }
        }
    }

    public void setDirect() {
        directDown = false;
        directUp = false;
        directLeft = false;
        directRight = false;
    }

    public boolean distance() {
        if(Math.abs(bomber.getX()/size-nextX) + Math.abs(bomber.getY()/size-nextY) == 1) {
            return true;
        }

        return false;
    }
}
