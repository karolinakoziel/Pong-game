import java.awt.*;
import java.awt.event.*;

public class Button extends Rectangle{
    boolean selected = false;
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
    public void draw(Graphics gr){

        if (selected){
            gr.setColor(Color.black);

            gr.fillRect(x-7, y-7, width+14, height+14);
        }
        colorSetting(gr);
        gr.fillRect(x, y, width, height);



    }
}
