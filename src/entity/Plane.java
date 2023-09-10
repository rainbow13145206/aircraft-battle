package entity;

import utils.GetImage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

/**
 * @author shkstart
 * @create 2023-09-07 20:45
 */
public class Plane {
    public int x;
    public int y;
    public BufferedImage buffImage;

    public Plane() {
        x = 200;
        y = 500;
        buffImage = GetImage.getImage("hero.png");

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
