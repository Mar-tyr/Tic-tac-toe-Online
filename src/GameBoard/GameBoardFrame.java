package GameBoard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by 90465 on 2018/5/24.
 */
public class GameBoardFrame extends JFrame {

    public GameBoardFrame(){
        super();
        setTitle("Tic-tac-toe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GameBoardMainPanel mainPanel=new GameBoardMainPanel();
        add(mainPanel);
        pack();
    }

    public static void main(String []args){
        JFrame frame=new GameBoardFrame();
        frame.setVisible(true);
    }

    class GameBoardMainPanel extends JPanel{
        public static final int DEFAULT_WIDTH=1600,DEFAULT_HEIGHT=900;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            try {
                g.drawImage(ImageIO.read(new File("image/gameboard_bg1.jpg")), 0, 0, this);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        public GameBoardMainPanel(){
            super();
            setLayout(null);
            ChessBoard board=new ChessBoard(500,250,600,600);
            board.setOpaque(false);
            add(board);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        }
    }



    class ChessBoard extends JPanel{
        private int width,height,firstPercentile,secondPercentile;
        Stroke lineStroke=new BasicStroke(10,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER);
        Stroke circleStroke=new BasicStroke(6,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER);
        Stroke crossStroke=new BasicStroke(6,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER);
        public ChessBoard(int x,int y,int width,int height){
            super();
            this.width=width;
            this.height=height;
            setBounds(x,y,width,height);
            firstPercentile=width/3;
            secondPercentile=firstPercentile*2;
        }
        private void paintBoardLine(Graphics g){
            Graphics2D graphics2D=(Graphics2D)g;
            graphics2D.setStroke(lineStroke);
            graphics2D.setColor(Color.black);
            g.drawLine(5,firstPercentile,width-5,firstPercentile);
            g.drawLine(5,secondPercentile,width-5,secondPercentile);
            g.drawLine(firstPercentile,5,firstPercentile,595);
            g.drawLine(secondPercentile,5,secondPercentile,595);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            paintBoardLine(g);
            drawCircle(g,0,1);
            drawCircle(g,1,1);
            drawCross(g,1,0);
            drawCross(g,1,2);
            drawCross(g,2,1);
        }

        private void drawCircle(Graphics g,int row,int column){
            int centerX=column*firstPercentile+firstPercentile/2,centerY=row*firstPercentile+firstPercentile/2;
            int diameter=firstPercentile-30;
            Graphics2D graphics2D=(Graphics2D)g;
            graphics2D.setStroke(circleStroke);
            graphics2D.setColor(Color.RED);
            System.out.println(centerX+ " "+centerY+" "+width+" "+height);
            g.drawOval(centerX-diameter/2,centerY-diameter/2,diameter,diameter);
        }

        private void drawCross(Graphics g,int row,int column){
            Graphics2D graphics2D=(Graphics2D) g;
            graphics2D.setStroke(lineStroke);
            graphics2D.setColor(Color.WHITE);
            graphics2D.drawLine(column*firstPercentile+30,row*firstPercentile+30,(column+1)*firstPercentile-30,(row+1)*firstPercentile-30);
            graphics2D.drawLine((column+1)*firstPercentile-30,row*firstPercentile+30,column*firstPercentile+30,(row+1)*firstPercentile-30);
        }
    }

}
