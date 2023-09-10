package utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author shkstart
 * @create 2023-09-08 15:58
 */
public class OperateUtils {


    //生成
    public static <T> int generate(List<T> list, Class<T> clazz, int index) {
        index++;
        T t = null;
        try {
            if (index > 20) {//每执行20次这个方法就生产一个实体
                t = clazz.newInstance();
                list.add(t);
                index = 0;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return index;
    }

    //生成实体（有参）
    public static <T, R> int generateWithArgs(List<T> list, Class<T> clazz, int index, R r) {
        index++;
        T t = null;
        try {
            if (index > 18) {//每执行20次这个方法就生产一个实体
                Constructor<T> constructor = clazz.getConstructor(r.getClass());
                t = constructor.newInstance(r);
                list.add(t);
                index = 0;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return index;
    }

    //移动
    public static <T> void move(List<T> list, int step, boolean isX, boolean isY) {
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            try {


                if (isX) {
                    Method getX = t.getClass().getMethod("getX", null);
                    Method setX = t.getClass().getMethod("setX", int.class);
                    int x = (int) getX.invoke(t, null);
                    setX.invoke(t, x + step);
                }
                if (isY) {
                    Method getY = t.getClass().getMethod("getY", null);
                    Method setY = t.getClass().getMethod("setY", int.class);
                    int y = (int) getY.invoke(t, null);
                    setY.invoke(t, y + step);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
