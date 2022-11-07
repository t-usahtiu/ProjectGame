package StillEntity.Item;

import Character.Bomber.Bomber;
import Constant.Sprite;
import StillEntity.Map;

import java.awt.*;

public class Portal extends Item {
    public Portal(int x, int y) {
        super(Sprite.portal, x, y);
    }

    @Override
    public void update(Bomber bomber) {

    }

    @Override
    public int hashCode(){
        return hashCodeItem;
    }

    @Override
    public void render(Graphics2D graphics2D) {
        if(Map.getMap(this.x, this.y) != 0){
            return;
        }
        graphics2D.drawImage(image, x * size, y * size, size, size, null);
    }
}
