// Citations: https://docs.oracle.com/javase/tutorial/uiswing/;
//            https://stackoverflow.com;
//            https://www3.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html#zz-3.
//            https://javatpoint.com

package ui;

import javax.swing.*;
import java.awt.*;

// Represents a background image that extends the JComponent class.
public class BackgroundImage extends JComponent {
    private Image image;

    public BackgroundImage(Image image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, -100, -100, this);
    }
}
