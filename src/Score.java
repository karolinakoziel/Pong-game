import java.awt.*;

public class Score extends Rectangle{

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1;
    int player2;

    Score(int GAME_WIDTH, int GAME_HEIGHT){
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }
    public void draw(Graphics g) {
        g.setColor(Color.gray);
        g.setFont(new Font("Algerian",Font.PLAIN,60));

        g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);

        g.setColor(Color.black);
        g.drawString(String.valueOf(player1%10), (GAME_WIDTH/2)-55, 50);
        g.drawString(String.valueOf(player2%10), (GAME_WIDTH/2)+20, 50);
    }
}
