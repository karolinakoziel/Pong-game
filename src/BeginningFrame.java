import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class BeginningFrame{
    Button twoPOffline;
    Button twoPOnline;
    Button oneP;
    List<Button> colorButtons = new ArrayList<Button>();
    public boolean end = false;
    static final int BUTTON_WIDTH = 220;
    static final int BUTTON_HEIGHT = 70;
    int GAME_WIDTH;
    int GAME_HEIGHT;
    int mode = 1; //1-choosing game kind, 2-choosing colour for player 1, 3-choosing colour for player 2
    int curSelectedId = 1;
    public int chosenType;
    public Color col1;
    public Color col2;
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
        if (e.getKeyCode()==KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_UP) {
            if (this.curSelectedId == 1 && this.mode == 1){
                oneP.changeSelection(1,3);
                twoPOnline.changeSelection(1,3);
                twoPOffline.changeSelection(1,3);
                this.curSelectedId = 3;
            } else if (this.curSelectedId == 4 && this.mode >= 2) {
                for (Button b : colorButtons) b.changeSelection(4,9);
                this.curSelectedId = 9;
            }else if (this.mode == 1){
                oneP.changeSelection(this.curSelectedId, this.curSelectedId - 1);
                twoPOnline.changeSelection(this.curSelectedId, this.curSelectedId - 1);
                twoPOffline.changeSelection(this.curSelectedId, this.curSelectedId - 1);
                this.curSelectedId--;
            } else {
                for (Button b : colorButtons) b.changeSelection(this.curSelectedId, this.curSelectedId - 1);
                this.curSelectedId--;
            }
        } else if (e.getKeyCode()==KeyEvent.VK_S || e.getKeyCode()==KeyEvent.VK_DOWN) {
            if (this.curSelectedId == 3 && this.mode == 1){
                oneP.changeSelection(3,1);
                twoPOnline.changeSelection(3,1);
                twoPOffline.changeSelection(3,1);
                this.curSelectedId = 1;
            } else if (this.curSelectedId == 9 && this.mode >= 2) {
                for (Button b : colorButtons) b.changeSelection(9, 4);
                this.curSelectedId = 4;
            } else if (this.mode == 1){
                oneP.changeSelection(this.curSelectedId, this.curSelectedId + 1);
                twoPOnline.changeSelection(this.curSelectedId, this.curSelectedId + 1);
                twoPOffline.changeSelection(this.curSelectedId, this.curSelectedId + 1);
                this.curSelectedId++;
            } else {
                for (Button b : colorButtons) b.changeSelection(this.curSelectedId, this.curSelectedId + 1);
                this.curSelectedId++;
            }
        } else if (e.getKeyCode()==KeyEvent.VK_D || e.getKeyCode()==KeyEvent.VK_RIGHT) {
            if (this.mode >= 2 && this.curSelectedId <= 6) {
                for (Button b : colorButtons) b.changeSelection(this.curSelectedId, this.curSelectedId + 3);
                this.curSelectedId = this.curSelectedId + 3;
            }
        } else if (e.getKeyCode()==KeyEvent.VK_A || e.getKeyCode()==KeyEvent.VK_LEFT) {
            if (this.mode >= 2 && this.curSelectedId > 6) {
                for (Button b : colorButtons) b.changeSelection(this.curSelectedId, this.curSelectedId - 3);
                this.curSelectedId = this.curSelectedId - 3;
            }
        } else if (e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_SPACE) {
            switch (this.mode) {
                case 1:
                    chosenType = curSelectedId;
                    mode = 2;
                    curSelectedId = 4;
                    break;
                case 2:
                    choseColor();
                    if (chosenType == 2){
                        mode = 3;
                        for (Button b : colorButtons) b.changeSelection(this.curSelectedId, 4);
                        this.curSelectedId = 4;
                    } else if (this.chosenType == 1) {
                        col2 = Color.gray;
                        end = true;
                    } else {
                        //TODO: kolor drugiego gracza z servera
                        end = true;
                    }
                    break;
                case 3:
                    choseColor();
                    end = true;
                    break;
            }
        }
    }
    private void choseColor(){
        if (this.mode == 2) {
            switch(curSelectedId){
                case 4:
                    col1 = Color.pink;
                    break;
                case 5:
                    col1 = Color.green;
                    break;
                case 6:
                    col1 = Color.orange;
                    break;
                case 7:
                    col1 = Color.blue;
                    break;
                case 8:
                    col1 = Color.red;
                    break;
                case 9:
                    col1 = Color.cyan;
                    break;
            }
        } else {
            switch(curSelectedId){
                case 4:
                    col2 = Color.pink;
                    break;
                case 5:
                    col2 = Color.green;
                    break;
                case 6:
                    col2 = Color.orange;
                    break;
                case 7:
                    col2 = Color.blue;
                    break;
                case 8:
                    col2 = Color.red;
                    break;
                case 9:
                    col2 = Color.cyan;
                    break;
            }
        }

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
