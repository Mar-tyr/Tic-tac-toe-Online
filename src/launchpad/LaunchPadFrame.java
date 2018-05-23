package launchpad;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by 90465 on 2018/5/21.
 */
public class LaunchPadFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 800;
    private LogOnFrame logOnFrameCache;

    public LaunchPadFrame() throws IOException {
        setTitle("Tic Tac Toe!");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);
        LaunchPadMainPanel mainPanel=this.new LaunchPadMainPanel();
        add(mainPanel, BorderLayout.CENTER);
        pack();
    }


    public static void main(String[] agrs) {
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

    private void enterLogon() throws IOException{
        this.setVisible(false);
        LogOnFrame logOnFrame=(logOnFrameCache==null)? new LogOnFrame(this):logOnFrameCache;
        logOnFrameCache=logOnFrame;
        logOnFrame.setVisible(true);
    }

    class LaunchPadMainPanel extends JPanel{
        private Image backgroundImage;
        private static final int DEFAULT_WIDTH = 1200, DEFAULT_HEIGHT = 800;
        private static final int BUTTON_Y_INTERVAL = 10;

        public LaunchPadMainPanel() throws IOException{
            backgroundImage = ImageIO.read(new File("image/launchpad_bg.jpg"));
            setLayout(null);


            JButton signInButton = new MyButton("image/button_sign-in.png");
            signInButton.setName("signInButton");
            signInButton.setBounds((DEFAULT_WIDTH - signInButton.getWidth()) / 2, (DEFAULT_HEIGHT - signInButton.getHeight()) / 2, signInButton.getWidth(), signInButton.getHeight());

            add(signInButton);


            JButton logOnButton = new MyButton("image/button_log-on.png");
            logOnButton.setName("logOnButton");
            logOnButton.setBounds((DEFAULT_WIDTH - logOnButton.getWidth()) / 2, signInButton.getY() + signInButton.getHeight() + BUTTON_Y_INTERVAL, logOnButton.getWidth(), logOnButton.getHeight());
            logOnButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        enterLogon();
                    }catch (IOException exception){
                        exception.printStackTrace();
                    }
                }
            });
            add(logOnButton);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        }
    }

}

