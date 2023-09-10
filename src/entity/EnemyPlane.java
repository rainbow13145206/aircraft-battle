package entity;

import utils.GetImage;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

/**
 * @author shkstart
 * @create 2023-09-08 10:19
 */
public class EnemyPlane {
    public int x;
    public int y;
    public BufferedImage buffImg;
    public String imgName;

    public EnemyPlane() {
        imgName = getName();
        buffImg = GetImage.getImage(imgName);
        //飞机的坐标
        Random random = new Random();
        //x:0-350
        x = random.nextInt(350);
        y = 0;
    }

    private static String getName() {
        //随机生成图片
        Random random = new Random();
        int index = random.nextInt(15) + 1;
        String suffix = index < 10 ? "0" + index : "" + index;
        String name = "ep" + suffix + ".png";
        return name;
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
