package launchpad;

import client.Client;
import entity.User;
import gameboard.GameBoardFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by 90465 on 2018/5/21.
 */
public class LaunchPadFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 800;
    private LogOnFrame logOnFrameCache;

    public LaunchPadFrame() throws IOException {                        //初始化
        setTitle("Tic Tac Toe!");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);
        LaunchPadMainPanel mainPanel = this.new LaunchPadMainPanel();
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

    private void enterLogon() throws IOException {                   //进入注册界面
        this.setVisible(false);
        LogOnFrame logOnFrame = (logOnFrameCache == null) ? new LogOnFrame(this) : logOnFrameCache;
        logOnFrameCache = logOnFrame;
        logOnFrame.setVisible(true);
    }

    private void enterGameBoard(User user) throws IOException {      //进入棋盘界面
        this.setVisible(false);
        GameBoardFrame gameBoardFrame = new GameBoardFrame(this, user);
        gameBoardFrame.setVisible(true);
    }

    class LaunchPadMainPanel extends JPanel {                        //主面板
        private static final int DEFAULT_WIDTH = 1200, DEFAULT_HEIGHT = 800;
        private static final int BUTTON_Y_INTERVAL = 10;
        JButton signInButton, logOnButton;
        private Image backgroundImage;

        public LaunchPadMainPanel() throws IOException {
            Font font = new Font("Times New Roman", Font.BOLD, 30);
            UIManager.put("Label.font", font);
            UIManager.put("TextField.font", font);
            UIManager.put("PasswordField.font", font);

            backgroundImage = ImageIO.read(new File("image/launchpad_bg.jpg"));
            setLayout(null);

            JLabel uNameLabel = new JLabel("Username:"), passwordLabel = new JLabel("Password:");    //用户名密码
            uNameLabel.setBounds(400, 250, 200, 100);
            uNameLabel.setForeground(Color.blue);
            passwordLabel.setForeground(Color.blue);
            passwordLabel.setBounds(400, 300, 200, 100);
            add(uNameLabel);
            add(passwordLabel);

            JTextField uNameInput = new JTextField();

            JPasswordField passwordInput = new JPasswordField();
            uNameInput.setColumns(10);
            uNameInput.setBounds(550, 290, 200, 30);
            passwordInput.setColumns(10);
            passwordInput.setBounds(550, 340, 200, 30);
            add(uNameInput);
            add(passwordInput);


            signInButton = new MyButton("image/button_sign-in.png");                        //登录按钮
            signInButton.setName("signInButton");
            signInButton.setBounds(500, 450, signInButton.getWidth(), signInButton.getHeight());

            add(signInButton);

            signInButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {                        //登录
                    User user = null;
                    try {
                        user = Client.getAction().login(uNameInput.getText(), String.valueOf(passwordInput.getPassword()));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    if (user != null) {
                        try {
                            enterGameBoard(user);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                }
            });

            logOnButton = new MyButton("image/button_log-on.png");                      //注册
            logOnButton.setName("logOnButton");
            logOnButton.setBounds(500, 510, logOnButton.getWidth(), logOnButton.getHeight());
            logOnButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        enterLogon();
                    } catch (IOException exception) {
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