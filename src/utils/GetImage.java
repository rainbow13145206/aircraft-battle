package utils;

import javax.imageio.ImageIO;
import javax.naming.Name;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author shkstart
 * @create 2023-09-07 20:31
 */
public class GetImage {
    public static BufferedImage getImage(String imgName) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(GetImage.class.getResource("/img/" + imgName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bufferedImage;
    }
}
