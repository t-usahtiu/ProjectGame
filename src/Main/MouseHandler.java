package Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public final class MouseHandler implements MouseListener {
    private boolean mouseClick, mousePress, mouseRelease, mouseEnter, mouseExit;

    private int mouseX, mouseY;

    public MouseHandler() {
        this.setMouse();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.setMouse();
        mouseClick = true;

        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.setMouse();
        mousePress = true;

        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.setMouse();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setMouse();
        mouseEnter = true;

        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setMouse();
        mouseExit = true;

        mouseX = e.getX();
        mouseY = e.getY();
    }

    public void setMouse() {
        mouseClick = false;
        mousePress = false;
        mouseEnter = false;
        mouseExit = false;
        mouseRelease = false;
    }

    public boolean isMouseClick() {
        return mouseClick;
    }

    public boolean isMousePress() {
        return mousePress;
    }

    public boolean isMouseRelease() {
        return mouseRelease;
    }

    public boolean isMouseEnter() {
        return mouseEnter;
    }

    public boolean isMouseExit() {
        return mouseExit;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
}
