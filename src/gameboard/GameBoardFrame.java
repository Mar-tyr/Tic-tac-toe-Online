package gameboard;

import client.Client;
import entity.Game;
import entity.User;
import launchpad.LaunchPadFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by 90465 on 2018/5/24.
 */
public class GameBoardFrame extends JFrame implements ActionListener {
    private User myUser, anotherUser;                   //自己和对手
    private Game game;                                    //当前棋局
    private LaunchPadFrame launchPadFrame;              //前面的Launchpad
    private JLabel myUsernameLabel, anothorUsernameLabel;   //显示用户名
    private Timer gameTimer;                            //计时器
    private GameBoardMainPanel mainPanel;               //主面板

    public GameBoardFrame() {
        gameTimer = new Timer(500, this);       //计时器每半秒执行一次动作
        setTitle("Tic-tac-toe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainPanel = new GameBoardMainPanel();
        add(mainPanel);
        pack();
    }


    public GameBoardFrame(LaunchPadFrame launchPadFrame, User user) {           //以前面的launchpad与玩家作为参数构造
        this();
        this.launchPadFrame = launchPadFrame;
        this.myUser = user;
        try {
            game = Client.getAction().findGameByUserId(user.getUser_id());      //获得棋局
            if (game != null) {
                int anotherUserId = getAnotherUserId();
                anotherUser = Client.getAction().findUserById(anotherUserId);  //获得对手用户信息
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        gameTimer.start();                                                      //计时器开始
    }

    public static void main(String[] args) {
        GameBoardFrame gameBoardFrame = new GameBoardFrame();
        gameBoardFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {                                //计时器动作
        try {
            game = Client.getAction().findGameByUserId(myUser.getUser_id());    //更新棋局
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (game == null) return;                                               //棋局为空，退出

        if (game.getCurrentUser() < 0) {                                         //currentUser为负代表游戏结束，-1是user1获胜，-2是user2获胜
            gameTimer.stop();                                                   //计时器停止计时
            try {
                myUser = Client.getAction().findUserById(myUser.getUser_id());  //重获玩家信息
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Your score: " +
                            myUser.getUser_score() + "\n" + "Your grade:" + myUser.getUser_grade(),
                    "Game over", JOptionPane.INFORMATION_MESSAGE);      //显示得分信息

            try {
                Thread.sleep(500);                                          //等待半秒
                Client.getAction().updateGame(myUser.getUser_id(), "over");     //向服务器发送销毁棋局的信息
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            backToLaunchpad();                                                      //回到launchpad
            return;
        }

        boolean isMyTerm = isMyTerm();                                              //是否自己下子
        if (isMyTerm) {                                                             //更新用户名JLabel的背景颜色
            myUsernameLabel.setOpaque(true);
            myUsernameLabel.setBackground(Color.RED);
            anothorUsernameLabel.setOpaque(false);
            anothorUsernameLabel.setBackground(null);
        } else {
            anothorUsernameLabel.setOpaque(true);
            anothorUsernameLabel.setBackground(Color.RED);
            myUsernameLabel.setOpaque(false);
            myUsernameLabel.setBackground(null);
        }

        if (anotherUser == null) {                                              //获取对手信息
            try {
                anotherUser = Client.getAction().findUserById(getAnotherUserId());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        mainPanel.updateUser();                                                 //更新用户名JLabel的显示
        mainPanel.board.repaint();                                              //更新棋局的显示

    }

    private void backToLaunchpad() {                                            //回退到Launchpad
        this.setVisible(false);
        launchPadFrame.setVisible(true);
    }

    public int getAnotherUserId() {                                             //获取对手的id

        if (game.getUser1Id() == myUser.getUser_id()) {
            return game.getUser2Id();
        } else {
            return game.getUser1Id();
        }
    }

    private boolean isMyTerm() {                                                //判断是否自己下子
        if (game == null) return false;
        if (game.getCurrentUser() < 0) return false;
        if (game.getCurrentUser() == 1) {
            return myUser.getUser_id() == game.getUser1Id();
        } else {
            return myUser.getUser_id() == game.getUser2Id();
        }
    }

    private boolean isCross() {
        return myUser.getUser_id() == game.getUser1Id();
    }       //判断自己是叉还是圈

    private int getBoardPosition(Point point) {                                 //根据鼠标时间的Point来确定棋子在编号0-8的哪个格子
        int x = (int) point.getX(), y = (int) point.getY();
        int result = 0;
        if (x > 15 && x < 185) result += 0;
        else if (x > 215 && x < 385) result += 1;
        else if (x > 415 && x < 585) result += 2;
        else return -1;
        if (y > 15 && y < 185) result += 0;
        else if (y > 215 && y < 385) result += 3;
        else if (y > 415 && y < 585) result += 6;
        else return -1;
        return result;
    }

    class GameBoardMainPanel extends JPanel {
        public static final int DEFAULT_WIDTH = 1600, DEFAULT_HEIGHT = 900;
        private ChessBoard board;

        public GameBoardMainPanel() {
            super();
            Font font = new Font("Times New Roman", Font.BOLD, 30);             //设置字体
            UIManager.put("Label.font", font);
            UIManager.put("TextField.font", font);
            UIManager.put("RadioButton.font", font);
            UIManager.put("PasswordField.font", font);
            UIManager.put("Button.font", font);
            setLayout(null);
            board = new ChessBoard(500, 250, 600, 600);

            myUsernameLabel = new JLabel("HEHEHEHE", JLabel.CENTER);
            myUsernameLabel.setBounds(200, 350, 200, 100);

            add(myUsernameLabel);

            anothorUsernameLabel = new JLabel("HEHEHEHE", JLabel.CENTER);
            anothorUsernameLabel.setBounds(1250, 350, 200, 100);
            add(anothorUsernameLabel);


            board.setOpaque(false);
            add(board);


        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            try {
                g.drawImage(ImageIO.read(new File("image/gameboard_bg1.jpg")), 0, 0, this);     //背景图片
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void updateUser() {                                      //更新用户名的显示
            myUsernameLabel.setText(myUser.getUser_username());
            if (anotherUser != null)
                anothorUsernameLabel.setText(anotherUser.getUser_username());
            else
                anothorUsernameLabel.setText("No one else online!");
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        }
    }

    class ChessBoard extends JPanel {                                   //棋盘
        Stroke lineStroke = new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        Stroke circleStroke = new BasicStroke(6, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
        Stroke crossStroke = new BasicStroke(6, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        private int width, height, firstPercentile, secondPercentile;

        public ChessBoard(int x, int y, int width, int height) {                //初始化
            super();
            this.width = width;
            this.height = height;
            setBounds(x, y, width, height);
            firstPercentile = width / 3;
            secondPercentile = firstPercentile * 2;
            addMouseListener(new BoardMouseListener());
        }

        private void paintBoardLine(Graphics g) {                               //绘制棋盘
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.setStroke(lineStroke);
            graphics2D.setColor(Color.black);
            g.drawLine(5, firstPercentile, width - 5, firstPercentile);
            g.drawLine(5, secondPercentile, width - 5, secondPercentile);
            g.drawLine(firstPercentile, 5, firstPercentile, 595);
            g.drawLine(secondPercentile, 5, secondPercentile, 595);
        }

        private void drawChess(Graphics g, String boardcode) {                  //根据boardcode绘制棋子
            char[] boardChars = boardcode.toCharArray();
            for (int i = 0; i < boardChars.length; ++i) {
                if (boardChars[i] == '0') continue;
                if (boardChars[i] < '4') {
                    drawCross(g, i / 3, i % 3);
                } else {
                    drawCircle(g, i / 3, i % 3);
                }
            }
        }

        @Override
        protected void paintComponent(Graphics g) {                             //绘制棋局
            super.paintComponent(g);
            paintBoardLine(g);
            if (game == null) return;
            drawChess(g, game.getBoardcode());
        }


        private void drawCircle(Graphics g, int row, int column) {              //画圈
            int centerX = column * firstPercentile + firstPercentile / 2, centerY = row * firstPercentile + firstPercentile / 2;
            int diameter = firstPercentile - 30;
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.setStroke(circleStroke);
            graphics2D.setColor(Color.RED);
            g.drawOval(centerX - diameter / 2, centerY - diameter / 2, diameter, diameter);
        }

        private void drawCross(Graphics g, int row, int column) {               //画叉
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.setStroke(lineStroke);
            graphics2D.setColor(Color.WHITE);
            graphics2D.drawLine(column * firstPercentile + 30, row * firstPercentile + 30, (column + 1) * firstPercentile - 30, (row + 1) * firstPercentile - 30);
            graphics2D.drawLine((column + 1) * firstPercentile - 30, row * firstPercentile + 30, column * firstPercentile + 30, (row + 1) * firstPercentile - 30);
        }
    }

    class BoardMouseListener extends MouseAdapter {                             //鼠标监听
        @Override
        public void mouseClicked(MouseEvent e) {
            if (!isMyTerm()) return;                                            //不是自己回合
            int boardPosition = getBoardPosition(e.getPoint());                 //判断点击位置
            if (boardPosition == -1) return;                                    //点的不对
            char[] boardChars = game.getBoardcode().toCharArray();              //棋盘码
            if (boardChars[boardPosition] != '0') return;                       //已经有子
            boolean isCross = isCross();                                        //判断自己是圈还是叉
            if (isCross) {                                                      //更新棋盘码
                char maxChar = '0';
                for (char c : boardChars) {
                    if (c > '3') continue;
                    if (c > maxChar) maxChar = c;
                }
                if (maxChar < '3') {
                    boardChars[boardPosition] = (char) (maxChar + 1);
                } else {
                    for (int i = 0; i < boardChars.length; ++i) {
                        if (boardChars[i] > '0' && boardChars[i] < '4')
                            boardChars[i]--;
                    }
                    boardChars[boardPosition] = '3';
                }
            } else {
                char maxChar = '3';
                for (char c : boardChars) {
                    if (c < '4') continue;
                    if (c > maxChar) maxChar = c;
                }
                if (maxChar < '6') {
                    boardChars[boardPosition] = (char) (maxChar + 1);
                } else {
                    for (int i = 0; i < boardChars.length; ++i) {
                        if (boardChars[i] == '4') {
                            boardChars[i] = '0';
                        }
                        if (boardChars[i] > '4' && boardChars[i] < '7') {
                            --boardChars[i];
                        }
                    }
                    boardChars[boardPosition] = '6';
                }
            }
            String newBoardcode = String.valueOf(boardChars);
            try {
                Client.getAction().updateGame(myUser.getUser_id(), newBoardcode);           //向服务器发出更新请求
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}