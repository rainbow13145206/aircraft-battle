package panel;

import entity.Bullet;
import entity.EnemyPlane;
import entity.Plane;
import ui.Frame;
import ui.Main;
import utils.GetImage;
import utils.OperateUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shkstart
 * @create 2023-09-07 20:29
 */
public class Panel extends JPanel {
    BufferedImage bgImg = GetImage.getImage("bg1.jpg");
    Plane plane = new Plane();
    EnemyPlane enemyPlane = new EnemyPlane();
    static List<EnemyPlane> enemyPlanesList = new ArrayList<>();

    static List<Bullet> bulletList = new ArrayList<>();

    public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);


    //开辟线程创建敌军
    public void begin() {
        EXECUTOR_SERVICE.submit(new Runnable() {
            int index2 = 0;
            int index = 0;

            //758842
            @Override
            public void run() {
                while (true) {
                    index = OperateUtils.generate(enemyPlanesList, EnemyPlane.class, index);
                    index2 = OperateUtils.generateWithArgs(bulletList, Bullet.class, index2, plane);
                    OperateUtils.move(enemyPlanesList, 5, false, true);
                    OperateUtils.move(bulletList, -10, false, true);
                    verifyHit();
                    repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    repaint();
                }
            }
        });

    }

    //验证子弹和敌军是否相撞
    public void verifyHit() {
        for (int i = 0; i < enemyPlanesList.size(); i++) {
            for (int j = 0; j < bulletList.size(); j++) {
                judgeCoordinate(enemyPlanesList.get(i), bulletList.get(j));
            }
        }
    }

    //记录分数
    int score = 0;

    //判断坐标的位置
    public void judgeCoordinate(EnemyPlane enemyPlane, Bullet bullet) {
        int enemyWith = enemyPlane.buffImg.getWidth();
        int enemyHeight = enemyPlane.buffImg.getHeight();
        int bulletWith = bullet.buffImg.getWidth() / 4;
        int bulletHeight = bullet.buffImg.getHeight() / 4;
        if (plane.x + plane.buffImage.getHeight() >= enemyPlane.x
                && plane.x <= enemyPlane.x + enemyWith
                && plane.y + plane.buffImage.getHeight() >= enemyPlane.y && plane.y <= enemyPlane.y + enemyHeight) {
            JOptionPane.showConfirmDialog(this, "你本次的分数为：" + score, "游戏结束", JOptionPane.OK_OPTION);
            System.exit(0);
        }
        if ((bullet.x + bulletWith >= enemyPlane.x && bullet.x <= enemyPlane.x + enemyWith)
                && (bullet.y + bulletHeight >= enemyPlane.y && bullet.y <= enemyPlane.y + enemyHeight)) {
            bulletList.remove(bullet);
            enemyPlanesList.remove(enemyPlane);
            score++;
        }
    }


    @Override
    public void paint(Graphics g) {
        g.drawImage(bgImg, 0, 0, null);
        g.drawImage(plane.buffImage, plane.x, plane.y, null);
        for (int i = 0; i < enemyPlanesList.size(); i++) {
            enemyPlane = enemyPlanesList.get(i);
            g.drawImage(enemyPlane.buffImg, enemyPlane.x, enemyPlane.y, null);
        }
        //画子弹
        bulletList.stream().forEach(bullet -> {
            g.drawImage(bullet.buffImg, bullet.x, bullet.y, bullet.buffImg.getWidth() / 4, bullet.buffImg.getHeight() / 4, null);
        });

        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑", 1, 25));
        //画分数
        g.drawString("分数： " + score, 10, 30);


    }

    Frame frame;

    public Panel(Frame frame) {
        this.frame = frame;
        int halfX = plane.buffImage.getWidth() / 2;//飞机的宽的一半
        int halfY = plane.buffImage.getHeight() / 2;//飞机高的一半
        //鼠标移动适配器
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {

                if (e.getX() >= halfX && e.getX() <= 496 - halfX) {
                    plane.x = e.getX() - halfX;
                }
                if (e.getY() >= halfY && e.getY() <= 708 - halfY) {
                    plane.y = e.getY() - halfY;
                }
                //x,y坐标发生改变时一定要重画
                repaint();
            }

        };
        //面板中添加鼠标监听器
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);

        //创建键盘适配器
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                    if (plane.x >= 10) {//存在误差
                        plane.x = plane.x - 20;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                    if (plane.x <= 375) {
                        plane.x = plane.x + 20;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
                    if (plane.y >= 15) {
                        plane.y = plane.y - 20;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                    if (plane.y <= 600) {
                        plane.y = plane.y + 20;
                    }
                }
                repaint();
            }
        };
        //添加键盘监听
        frame.addKeyListener(keyAdapter);

    }
}
