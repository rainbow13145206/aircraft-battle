package test;

import entity.Bullet;
import entity.EnemyPlane;
import entity.Plane;
import panel.Panel;
import utils.GetImage;
import utils.OperateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shkstart
 * @create 2023-09-08 10:31
 */
public class TestGetImg {
    public static List<Plane> list = new ArrayList<>();

    public static void main(String[] args) {
//        testRandomEnemyPlane();
//        testMoveAndGenerate();
            testBullet();
    }

    public static void testBullet(){
        List<Bullet> bulletList = new ArrayList<>();
        Plane plane = new Plane();
        int index=20;
            index=OperateUtils.generateWithArgs(bulletList,Bullet.class,index,plane);
        bulletList.stream().forEach(r->{
            System.out.println(bulletList.size());
            System.out.println(r.x);
        });
    }
    public static void testMoveAndGenerate() {
        int index = 0;
        for (int i = 0; i < 41; i++) {
            index = OperateUtils.generate(list, Plane.class, index);

        }
        OperateUtils.move(list, 5, true, true);
        list.stream().forEach(r -> {
            System.out.println(r.x);
            System.out.println(r.y);
        });
    }

    public static void testRandomEnemyPlane() {
        EnemyPlane plane = null;
        for (int i = 0; i < 15; i++) {
            plane = new EnemyPlane();
            System.out.println(plane.imgName);
        }
    }
}
