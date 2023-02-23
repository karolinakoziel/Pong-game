import java.awt.*;
import java.awt.event.*;

public class Button extends Rectangle{
    public boolean selected = false;
    int id;
    int x;
    int y;

    Button(int x, int y, int BUTTON_WIDTH, int BUTTON_HEIGHT, int id){
        super(x,y,BUTTON_WIDTH, BUTTON_HEIGHT);
        this.id = id;
        this.x = x;
        this.y = y;
        if (id == 1 || id == 4) this.selected = true;
    }
    private void colorSetting(Graphics gr){
        switch (this.id) {
            case 1,2,3,4:
                gr.setColor(Color.pink);
                break;
            case 5:
                gr.setColor(Color.green);
                break;
            case 6:
                gr.setColor(Color.orange);
                break;
            case 7:
                gr.setColor(Color.blue);
                break;
            case 8:
                gr.setColor(Color.red);
                break;
            case 9:
                gr.setColor(Color.cyan);
                break;
        }
    }
    private void textAdding(Graphics gr){
        gr.setColor(Color.black);
        gr.setFont(new Font("Courier",Font.PLAIN,30));
        switch (this.id) {
            case 1:
                gr.drawString("1 player", this.x + 50, this.y + 45);
                break;
            case 2:
                gr.drawString("2 players offline", this.x + 5, this.y + 45);
                break;
            case 3:
                gr.drawString("2 players online", this.x + 7, this.y + 45);
                break;
            case 4:
                gr.drawString("Pink", this.x + 80, this.y + 45);
                break;
            case 5:
                gr.drawString("Green", this.x + 70, this.y + 45);
                break;
            case 6:
                gr.drawString("Orange", this.x + 60, this.y + 45);
                break;
            case 7:
                gr.drawString("Blue", this.x + 80, this.y + 45);
                break;
            case 8:
                gr.drawString("Red", this.x + 85, this.y + 45);
                break;
            case 9:
                gr.drawString("Cyan", this.x + 80, this.y + 45);
                break;
        }

    }
    public void changeSelection(int unSelect, int select){
        if (this.id == unSelect) this.selected = false;
        if (this.id == select) this.selected = true;
    }
    public void draw(Graphics gr){

        if (this.selected){
            gr.setColor(Color.black);

            gr.fillRect(x-7, y-7, width+14, height+14);
        }
        colorSetting(gr);
        gr.fillRect(x, y, width, height);
        textAdding(gr);


    }
}
