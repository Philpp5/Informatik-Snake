package Snake;

import javax.swing.*;
import java.util.Random;

public class Apple extends JPanel {

    public static int x,y,width,height;


    Apple() {

        Random r = new Random();
        int rX = r.nextInt(Game.width/10-1);
        rX = rX * 10;
        x = rX;

        int rY = r.nextInt(Game.height/10-2);
        rY = rY*10;
        y = rY;

        width = 10;
        height= 10;

    }
}
