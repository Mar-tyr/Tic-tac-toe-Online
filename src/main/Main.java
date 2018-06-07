package main;

import launchpad.LaunchPadFrame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by 90465 on 2018/6/2.
 */
public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = null;
                try {
                    frame = new LaunchPadFrame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                frame.setVisible(true);
            }
        });
    }
}
