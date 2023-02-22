import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class BeginningFrame{
    Button twoPOffline;
    Button twoPOnline;
    Button oneP;
    List<Button> colorButtons = new ArrayList<Button>();
    boolean end = false;
    static final int BUTTON_WIDTH = 220;
    static final int BUTTON_HEIGHT = 70;
    int GAME_WIDTH;
    int GAME_HEIGHT;
    int mode = 1; //1-choosing game kind, 2-choosing colour for player 1, 3-choosing colour for player 2
    int curSelectedId = 1;
    BeginningFrame(int GAME_WIDTH, int GAME_HEIGHT){
        oneP = new Button(GAME_WIDTH/2 - BUTTON_WIDTH/2, GAME_HEIGHT/3, BUTTON_WIDTH, BUTTON_HEIGHT,1);
        twoPOffline = new Button(GAME_WIDTH/2 - BUTTON_WIDTH/2, GAME_HEIGHT/9*5, BUTTON_WIDTH, BUTTON_HEIGHT, 2);
        twoPOnline = new Button(GAME_WIDTH/2 - BUTTON_WIDTH/2, GAME_HEIGHT/9*7, BUTTON_WIDTH, BUTTON_HEIGHT, 3);
        this.colorButtons.add(new Button((GAME_WIDTH/4 - BUTTON_WIDTH/2), GAME_HEIGHT/3, BUTTON_WIDTH, BUTTON_HEIGHT, 4));
        this.colorButtons.add(new Button((GAME_WIDTH/4 - BUTTON_WIDTH/2), GAME_HEIGHT/9*5, BUTTON_WIDTH, BUTTON_HEIGHT, 5));
        this.colorButtons.add(new Button((GAME_WIDTH/4 - BUTTON_WIDTH/2), GAME_HEIGHT/9*7, BUTTON_WIDTH, BUTTON_HEIGHT, 6));
        this.colorButtons.add(new Button((GAME_WIDTH/4*3 - BUTTON_WIDTH/2), GAME_HEIGHT/3, BUTTON_WIDTH, BUTTON_HEIGHT, 7));
        this.colorButtons.add(new Button((GAME_WIDTH/4*3 - BUTTON_WIDTH/2), GAME_HEIGHT/9*5, BUTTON_WIDTH, BUTTON_HEIGHT, 8));
        this.colorButtons.add(new Button((GAME_WIDTH/4*3 - BUTTON_WIDTH/2), GAME_HEIGHT/9*7, BUTTON_WIDTH, BUTTON_HEIGHT, 9));
        this.GAME_WIDTH = GAME_WIDTH;
        this.GAME_HEIGHT = GAME_HEIGHT;
    }
    public void keyPressed(KeyEvent e) {
        switch (this.mode) {
            case 1:
                if (e.getKeyCode()==KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_UP) {
                    switch (curSelectedId){
                        case 1:
                            oneP.selected = false;
                            twoPOnline.selected = true;
                            curSelectedId = 3;
                            break;
                        case 2:
                            twoPOffline.selected = false;
                            oneP.selected = true;
                            curSelectedId = 1;
                            break;
                        case 3:
                            twoPOnline.selected = false;
                            twoPOffline.selected = true;
                            curSelectedId = 2;
                            break;
                    }
                } else if (e.getKeyCode()==KeyEvent.VK_S || e.getKeyCode()==KeyEvent.VK_DOWN) {
                    switch (curSelectedId){
                        case 1:
                            oneP.selected = false;
                            twoPOffline.selected = true;
                            curSelectedId = 2;
                            break;
                        case 2:
                            twoPOffline.selected = false;
                            twoPOnline.selected = true;
                            curSelectedId = 3;
                            break;
                        case 3:
                            twoPOnline.selected = false;
                            oneP.selected = true;
                            curSelectedId = 1;
                            break;
                    }
                }
        }
        //zmiana modu
    }

    public void draw(Graphics gr) {
        switch (this.mode) {
            case 1:
                twoPOffline.draw(gr);
                twoPOnline.draw(gr);
                oneP.draw(gr);
                gr.setColor(Color.black);
                gr.setFont(new Font("Algerian",Font.PLAIN,GAME_WIDTH/14));
                gr.drawString("WELCOME TO PONG GAME", GAME_WIDTH/14, GAME_HEIGHT/5 );
                break;
            case 2,3:
                for (Button singleB : colorButtons) {
                    singleB.draw(gr);
                }
                gr.setColor(Color.black);
                gr.setFont(new Font("Algerian",Font.PLAIN,GAME_WIDTH/12));
                gr.drawString("CHOOSE COLOR", GAME_WIDTH/4-20, GAME_HEIGHT/6 );
                gr.setFont(new Font("Baumans",Font.PLAIN,GAME_WIDTH/19));
                gr.drawString("For player" + String.valueOf(mode - 1), GAME_WIDTH/3+40, GAME_HEIGHT/4+10 );
        }


    }
}
