package launchpad;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by 90465 on 2018/5/21.
 */
public class MyButton extends JButton {
    Image buttonBackgroundImage = null;
    private int width, height;

    public MyButton(String filename) throws IOException {
        buttonBackgroundImage = ImageIO.read(new File(filename));
        width = buttonBackgroundImage.getWidth(this);
        height = buttonBackgroundImage.getHeight(this);
        setSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(buttonBackgroundImage, 0, 0, null);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}