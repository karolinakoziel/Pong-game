import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class GameFrame extends JPanel implements Runnable {
    static final String IP = "192.168.1.145";
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = 600;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;
    BeginningFrame begFrame;
    Boolean started = false;
    int winner = 0;
    Socket socket = null;
    DataOutputStream dout = null;
    DataInputStream din = null;
    Boolean isMainPlayer = null;
    Boolean EnemyDisconnected = false;

    GameFrame(){
        this.begFrame = new BeginningFrame(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH,GAME_HEIGHT);
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall() {
        random = new Random();
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
    }

    public void newPaddles() {
        int type1,type2;
        switch (begFrame.chosenType) {
            case 1:
                type1 = 1;
                type2 = 3;
                break;
            case 3:
                type1 = 1;
                type2 = 4;
                break;
            default:
                type1 = type2 = 2;
        }
        paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1, begFrame.col1, type1);
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2, begFrame.col2, type2);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }

    public void draw(Graphics gr) {
        if (!begFrame.end) {
            begFrame.draw(gr);
        } else if (EnemyDisconnected) {
            gr.setFont(new Font("Algerian",Font.PLAIN,GAME_WIDTH/14));
            gr.drawString("PLAYER 2 DISCONNECTED", GAME_WIDTH/11, GAME_HEIGHT/5 );
        }else if(winner > 0) {
            if (winner == 1) gr.setColor(begFrame.col1);
            else gr.setColor(begFrame.col2);
            gr.setFont(new Font("Algerian",Font.PLAIN,GAME_WIDTH/14));
            gr.drawString("PLAYER " + winner + " WON", GAME_WIDTH/4, GAME_HEIGHT/5 );
            try {
                dout.close();
                socket.close();
            } catch(Exception e){System.out.println(e);}
        } else {
            paddle1.draw(gr);
            paddle2.draw(gr);
            ball.draw(gr);
            score.draw(gr);
            Toolkit.getDefaultToolkit().sync();
        }
    }

    public void move() {
        if (begFrame.chosenType == 1) {
            if (ball.x > GAME_WIDTH / 2) paddle2.automaticMove(ball.y);
            else paddle2.automaticMove(GAME_HEIGHT/2);
        }
        paddle1.move();
        paddle2.move();
        if (begFrame.chosenType < 3 || isMainPlayer) ball.move();

    }

    public void checkCollision() {
        //bounce ball off top & bottom window edges
        if(ball.y <=0) {
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }
        //bounce ball off paddles
        if(ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //optional for more difficulty
            if(ball.yVelocity>0)
                ball.yVelocity++; //optional for more difficulty
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //optional for more difficulty
            if(ball.yVelocity>0)
                ball.yVelocity++; //optional for more difficulty
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        //stops paddles at window edges
        if(paddle1.y<=0)
            paddle1.y=0;
        if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
        if(paddle2.y<=0)
            paddle2.y=0;
        if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
        //give a player 1 point and creates new paddles & ball
        if(ball.x <=0) {
            score.player2++;
            newPaddles();
            newBall();
            if (score.player2 >= 10) winner = 2;
        }
        if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
            score.player1++;
            newPaddles();
            newBall();
            if (score.player1 >= 10) winner = 1;
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if(delta >=1) {
                if (begFrame.end && !started) {
                    if (begFrame.chosenType == 3) {
                        try{
                            socket = new Socket("localhost",6666);
                            dout = new DataOutputStream(socket.getOutputStream());
                            dout.writeUTF(getColorString(begFrame.col1));
                            dout.flush();
                            din = new DataInputStream(socket.getInputStream());
                            String col = din.readUTF();
                            System.out.println(col);
                            begFrame.col2 = getColor(col);
                            String num = din.readUTF();
                            System.out.println(num);
                            if (num.equals("1")) {
                                isMainPlayer = true;
                            }
                            else isMainPlayer = false;
                        }catch(Exception e){System.out.println(e);}
                    }

                    newPaddles();
                    paddle2.paddleColor = begFrame.col2;
                    newBall();
                    move();
                    checkCollision();
                    started = true;
                }
                if (begFrame.end && winner == 0) {
                    if (begFrame.chosenType == 3) {
                        try {
                            if (isMainPlayer)
                                dout.writeUTF(Integer.toString(paddle1.y) + " " + Integer.toString(ball.x) + " " + Integer.toString(ball.y) + " " + score.player1 + " " + score.player2);
                            else dout.writeUTF(Integer.toString(paddle1.y));
                            dout.flush();
                            String received = din.readUTF();
                            if (isMainPlayer) paddle2.y = Integer.valueOf(received);
                            else {
                                String[] words = received.split("\\s");
                                paddle2.y = Integer.valueOf(words[0]);
                                ball.x = GAME_WIDTH - Integer.valueOf(words[1]);
                                ball.y = Integer.valueOf(words[2]);
                                score.player1 = Integer.valueOf(words[4]);
                                score.player2 = Integer.valueOf(words[3]);
                            }
                        } catch (Exception e) {
                            System.out.println("Second player disconnected");
                            EnemyDisconnected = true;
                        }
                    }
                    move();
                    checkCollision();
                }
                repaint();
                delta--;
            }
        }
    }

    private String getColorString(Color col){
        if (col.equals(Color.pink)) return "pink";
        if (col.equals(Color.green)) return "green";
        if (col.equals(Color.orange)) return "orange";
        if (col.equals(Color.blue)) return "blue";
        if (col.equals(Color.red)) return "red";
        if (col.equals(Color.cyan)) return "cyan";
        return "black";
    }
    private Color getColor(String name){
        if (name.equals("pink")) return Color.pink;
        if (name.equals("green")) return Color.green;
        if (name.equals("orange")) return Color.orange;
        if (name.equals("blue")) return Color.blue;
        if (name.equals("red")) return Color.red;
        if (name.equals("cyan")) return Color.cyan;
        return Color.black;
    }

    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            if (!begFrame.end) {
                begFrame.keyPressed(e);
            } else {
                paddle1.keyPressed(e);
                paddle2.keyPressed(e);
            }
        }
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}