package Snake;

import javax.swing.*;
import java.awt.*;


class Snake extends JFrame {

    static int x,y,width,height,vX,vY,n;
    static Color color;
    static int nObjects;

    static int[] bootyX;
    static int[] bootyY;
    static int[] lastX;
    static int[] lastY;


    Snake(int xPosition, int yPosition, int w, int h, Color c){

        x = xPosition;
        y = yPosition;
        width = w;
        height = h;
        color = c;
        n = 0;
        nObjects = (Game.height*Game.height)/(Snake.width*Snake.height);
        bootyX = new int[nObjects];
        bootyY = new int[nObjects];
        lastX = new int[nObjects];
        lastY = new int[nObjects];
    }

    static void addBooty() {
            if (vX == 0 && vY == 10) {
                bootyX[n] = lastX[n];
                bootyY[n] = lastY[n];
            }
            else if (vX == 0 && vY == -10) {
                bootyX[n] = lastX[n];
                bootyY[n] = lastY[n];
            }
            else if (vX == 10 && vY == 0) {
                bootyX[n] = lastX[n];
                bootyY[n] = lastY[n];
            }
            else if (vX == -10 && vY == 0) {
                bootyX[n] = lastX[n];
                bootyY[n] = lastY[n];
            }
        n++;
    }


    static void moveSnake() {
        lastX[0] = x;
        lastY[0] = y;

        y += vY;
        x += vX;

        for (int i = 1; i<=Snake.n+1; i++) {
                lastX[i] = bootyX[i-1];
                lastY[i] = bootyY[i-1];

                bootyX[i-1] = lastX[i-1];
                bootyY[i-1] = lastY[i-1];
        }
    }
}




