import java.awt.*;
import java.awt.event.*;

public class BeginningFrame{
    Button twoPOffline;
    Button twoPOnline;
    Button oneP;
    boolean end = false;
    static final int BUTTON_WIDTH = 150;
    static final int BUTTON_HEIGHT = 50;
    BeginningFrame(int GAME_WIDTH, int GAME_HEIGHT){
        twoPOffline = new Button(GAME_WIDTH/2 - BUTTON_WIDTH/2, GAME_HEIGHT/3, BUTTON_WIDTH, BUTTON_HEIGHT,1);
        twoPOnline = new Button(GAME_WIDTH/2 - BUTTON_WIDTH/2, GAME_HEIGHT/9*5, BUTTON_WIDTH, BUTTON_HEIGHT, 2);
        oneP = new Button(GAME_WIDTH/2 - BUTTON_WIDTH/2, GAME_HEIGHT/9*7, BUTTON_WIDTH, BUTTON_HEIGHT, 3);
    }
    public void keyPressed(KeyEvent e) {

    }
    public void draw(Graphics gr) {
        twoPOffline.draw(gr);
        twoPOnline.draw(gr);
        oneP.draw(gr);
    }
}
