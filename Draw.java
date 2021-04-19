package Snake;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Draw extends JPanel {


    public void paint(Graphics g) {

        //Apple + Snake

        g.setColor(Color.RED);
        g.fillRect(Apple.x,Apple.y,Apple.width,Apple.height);

        g.setColor(Snake.color);
        if (Game.isDead)
            g.setColor(Color.BLACK);
        g.fillRect(Snake.x,Snake.y,Snake.width,Snake.height);

        ArrayList<Graphics> snakeBooty = new ArrayList<>();
        for (int i = 0; i < Snake.nObjects; i++)
            snakeBooty.add(g);
        for (int i = 0; i < Snake.n;i++){
            snakeBooty.get(i).fillRect(Snake.bootyX[i],Snake.bootyY[i],Snake.width,Snake.height);
        }

        //Text + score
        g.setColor(Color.white);
        g.drawString(String.valueOf(Snake.n),Game.width+10,Game.height/2);
        g.drawString("best", Game.width+10,Game.height/2+50);
        g.drawString(String.valueOf(Game.getBestScore()),Game.width+10, Game.height/2+70);

        g.setColor(Color.white);
        g.drawLine(Game.width,0,Game.width,Game.height);

        g.setColor(Color.YELLOW);

        if (!Game.isModeChosen) {
            g.drawString("choose Game mode:", Game.width/2 -40, Game.height / 2 - 40);
            g.drawString("Single Player: press P", Game.width/2 -40, Game.height / 2 - 20);
            g.drawString("Algorithm: press A", Game.width/2 -40, Game.height / 2 );
            g.drawString("KI: press K", Game.width/2 -40, Game.height / 2 + 20);
        }
        else if(!Game.isRunning && !Game.isDead && !Game.isPause)
            g.drawString("press SPACE to start", Game.width /2 - 30, Game.height / 2 - 10);

        else if (Game.isPause)
            g.drawString("pause", Game.width /2 - 30, Game.height / 2 - 10);

        else if (Game.isDead)
            g.drawString("you are dead/stupid", Game.width /2 - 30, Game.height / 2 - 10);
    }
}
