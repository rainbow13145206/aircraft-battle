package entity;

import utils.GetImage;

import java.awt.image.BufferedImage;

/**
 * @author shkstart
 * @create 2023-09-08 15:34
 */
public class Bullet {
    public int x;
    public int y ;
    public BufferedImage buffImg;

    public Bullet() {
    }

    public Bullet(Plane plane){
        buffImg= GetImage.getImage("fire.png");
        x=plane.x+plane.buffImage.getWidth()/2-buffImg.getWidth()/4/2;//子弹缩小四倍
        y=plane.y-buffImg.getHeight()/4;
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
