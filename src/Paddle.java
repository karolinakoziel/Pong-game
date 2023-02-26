import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle{

    int id;
    int yVelocity;
    int speed = 10;
    Color paddleColor;
    int type;


    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id, Color col, int type){ //1-letters and numbers 2-letters or numbers 3-automatic 4-server
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.paddleColor = col;
        this.id = id;
        this.type = type;
    }

    public void keyPressed(KeyEvent e) {
        if(this.type == 1 || (this.type == 2 && this.id == 1)) {
            if(e.getKeyCode()==KeyEvent.VK_W) {
                setYDirection(-speed);
            }
            if(e.getKeyCode()==KeyEvent.VK_S) {
                setYDirection(speed);
            }
        }
        if (this.type == 1 || (this.type == 2 && this.id == 2)) {
            if(e.getKeyCode()==KeyEvent.VK_UP) {
                setYDirection(-speed);
            }
            if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                setYDirection(speed);
            }
        }
    }
    public void keyReleased(KeyEvent e) {
        if(this.type == 1 || (this.type == 2 && this.id == 1)) {
            if(e.getKeyCode()==KeyEvent.VK_W) {
                setYDirection(0);
            }
            if(e.getKeyCode()==KeyEvent.VK_S) {
                setYDirection(0);
            }
        }
        if (this.type == 1 || (this.type == 2 && this.id == 2)) {
            if(e.getKeyCode()==KeyEvent.VK_UP) {
                setYDirection(0);
            }
            if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                setYDirection(0);
            }
        }
    }
    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }
    public void move() {
        y= y + yVelocity;
    }
    public void draw(Graphics g) {
        g.setColor(this.paddleColor);
        g.fillRect(x, y, width, height);
    }
    public void automaticMove(int yBall) {
        if (y < yBall - 6) y = y + 7;
        else if (y > yBall + 6) y = y - 7;
    }
}