package ui;

import panel.Panel;
import ui.Frame;

/**
 * @author shkstart
 * @create 2023-09-07 20:24
 */
public class Main {
    public Main() {
        Frame frame = new Frame();
        Panel panel =new Panel(frame);
        frame.add(panel);
        frame.setVisible(true);
        panel.begin();
    }



}
