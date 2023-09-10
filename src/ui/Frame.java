package ui;

import javax.swing.*;
import java.awt.*;

/**
 * @author shkstart
 * @create 2023-09-07 20:22
 */
public class Frame extends JFrame {
    public Frame() throws HeadlessException {
        setVisible(true);
        setTitle("飞机大战");
        setSize(512, 768);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
