package launchpad;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by 90465 on 2018/5/21.
 */
public class LogOnFrame extends JFrame {
    JFrame launchPadFrame;

    public LogOnFrame(LaunchPadFrame launchPadFrame) throws IOException {
        this.launchPadFrame=launchPadFrame;
        setTitle("Log On");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);
        add(new LogOnFrameMainPanel());
        pack();
    }

    private void back(){
        this.setVisible(false);
        launchPadFrame.setVisible(true);
    }

    public static void main(String[] args){

        JFrame frame=null;
        try {
            frame=new LogOnFrame(new LaunchPadFrame());

        }catch (IOException e){
            e.printStackTrace();
        }

        frame.setVisible(true);
    }

    class LogOnFrameMainPanel extends JPanel {
        private static final int DEFAULT_WIDTH = 1200, DEFAULT_HEIGHT = 800;
        Image logOnFrameBackground;

        public LogOnFrameMainPanel() throws IOException {
            Font font=new Font("Times New Roman",Font.BOLD,30);
            UIManager.put("Label.font",font);
            UIManager.put("TextField.font",font);
            UIManager.put("RadioButton.font",font);
            UIManager.put("PasswordField.font",font);
            UIManager.put("Button.font",font);


            logOnFrameBackground = ImageIO.read(new File("image/logon_bg.jpg"));
            BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
            setLayout(layout);
            add(Box.createVerticalStrut(DEFAULT_HEIGHT / 3));


            JPanel userNamePanel = new JPanel();
            userNamePanel.setOpaque(false);
            userNamePanel.setLayout(new FlowLayout());



            JLabel uNameLabel = new JLabel("User Name");

            uNameLabel.setForeground(Color.BLUE);
            userNamePanel.add(uNameLabel);
            userNamePanel.add(Box.createHorizontalStrut(30));
            JTextField uNameInput=new JTextField();
            uNameInput.setColumns(10);
            userNamePanel.add(uNameInput);

            add(userNamePanel);

            JPanel passwdPanel=new JPanel();
            passwdPanel.setAlignmentX(CENTER_ALIGNMENT);
            passwdPanel.setOpaque(false);
            passwdPanel.setLayout(new FlowLayout());
            JLabel passwdLabel=new JLabel("Password");
            passwdLabel.setForeground(Color.BLUE);
            passwdPanel.add(passwdLabel);
            passwdPanel.add(Box.createHorizontalStrut(47));
            JPasswordField passwdInput=new JPasswordField();
            passwdInput.setColumns(10);
            passwdPanel.add(passwdInput);
            add(passwdPanel);


            JPanel genderPanel=new JPanel(new FlowLayout());
            genderPanel.setOpaque(false);
            JRadioButton maleRadioButton=new JRadioButton("Male");
            maleRadioButton.setOpaque(false);
            JRadioButton femaleRadioButton=new JRadioButton("Female");
            femaleRadioButton.setOpaque(false);
            genderPanel.add(maleRadioButton);
            genderPanel.add(Box.createHorizontalStrut(30));
            genderPanel.add(femaleRadioButton);
            add(genderPanel);

            JPanel buttonPanel=new JPanel(new FlowLayout());
            buttonPanel.setOpaque(false);
            JButton submitButton=new JButton("Submit");

            JButton backButton=new JButton("Back");
            buttonPanel.add(submitButton);
            buttonPanel.add(Box.createHorizontalStrut(30));
            buttonPanel.add(backButton);
            backButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    back();
                }
            });
            add(buttonPanel);


            add(Box.createVerticalStrut(DEFAULT_HEIGHT/3));



        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(logOnFrameBackground, 0, 0, this);
        }
    }

}