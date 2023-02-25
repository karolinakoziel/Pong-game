import java.awt.*;
import javax.swing.*;

public class Window extends JFrame{
    GameFrame panel;
    Window(){
        panel = new GameFrame();
        this.add(panel);
        ImageIcon img = new ImageIcon("src/pingpong.png");
        this.setIconImage(img.getImage());
        this.setTitle("Pong");
        this.setBackground(Color.white);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

    }
}
