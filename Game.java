package Snake;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends JFrame implements KeyListener {


    //TODO: scalable, KI

    public static int width, height;
    private int lastVx, lastVy, framespeed = 80;
    private static int bestScore;
    static boolean isModeChosen = false;
    static boolean isRunning = false;
    static boolean isPause = false;
    static boolean isDead = false;
    private static boolean isKI = false;
    private static boolean isAlgorithm = false;
    private Timer snakeT;
    public static KeyAdapter keyAdapter;


    private Game() {

        addKeyListener(this);
        run();
        setVisible(true);
    }

    private void run() {

        frame(300,300,0,0,"Snake");
        setBackground(Color.BLACK);
        snake(10,0,10,10, Color.GREEN);
        apple();
        draw();
    }


    private void frame(int w,int h,int x,int y, String title) {
        width = w;
        height = h;

        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(w+50,h);
        setMinimumSize(new Dimension(w+50, h));
        setLocationRelativeTo(null);
        setResizable(false);

    }

    private void snake(int x, int y, int w, int h, Color c) {
        new Snake(x,y,w,h,c);
        Snake.vX = 0;
        Snake.vY = Snake.height;
    }

    private static void apple() {
        new Apple();
    }

    private void draw() {
        Draw draw = new Draw();
        add(draw);
    }


    private void move(long period) {
        snakeT = new Timer();
        TimerTask moveSnake = new TimerTask() {
            @Override
            public void run() {

                if (isKI)
                    KI.ki();
                else if (isAlgorithm) {
                    Algorithm.move();
                }
                Snake.moveSnake();
                eat();
                checkDeath();
                repaint();
            }
        };
        snakeT.scheduleAtFixedRate(moveSnake,0,period);
    }

    private void eat() {

        if (Snake.x == Apple.x && Snake.y == Apple.y){
            apple();
            Snake.addBooty();

            for (int i = 0;i<Snake.n; i++) {
                while (Apple.x == Snake.x && Apple.y == Snake.y)
                    apple();
                while (Apple.x == Snake.bootyX[i] && Apple.y == Snake.bootyY[i])
                    apple();
            }
            repaint();
        }
    }


    private void checkDeath() {
        if (Snake.x == -Snake.width) {
            isRunning = false;
            isDead = true;
            Snake.vY = 0;
            Snake.vX = 0;
            snakeT.cancel();
        }
        else if (Snake.x == Game.width) {
            isRunning = false;
            isDead = true;
            Snake.vY = 0;
            Snake.vX = 0;
            snakeT.cancel();
        }
        else if (Snake.y == -Snake.height) {
            isRunning = false;
            isDead = true;
            Snake.vY = 0;
            Snake.vX = 0;
            snakeT.cancel();
        }
        else if (Snake.y == Game.height-20) {
            isRunning = false;
            isDead = true;
            Snake.vY = 0;
            Snake.vX = 0;
            snakeT.cancel();
        }

        for (int i=0; i<Snake.n;i++){
            if (Snake.x == Snake.bootyX[i] && Snake.y == Snake.bootyY[i]) {
                isRunning = false;
                isDead = true;
                Snake.vY = 0;
                Snake.vX = 0;
                snakeT.cancel();
            }
        }

        if (Snake.x == Snake.lastX[Snake.n] && Snake.y == Snake.lastY[Snake.n]) {
            isRunning = false;
            isDead = true;
            Snake.vY = 0;
            Snake.vX = 0;
            snakeT.cancel();
        }
    }

    static int getBestScore() {
        bestScore = Math.max(Snake.n,bestScore);
        return bestScore;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                if (!isModeChosen) {
                    isAlgorithm = true;
                    isModeChosen = true;
                    framespeed = 10;
                }
                break;
            case KeyEvent.VK_P:
                if (!isModeChosen)
                    isModeChosen = true;
                break;
            case KeyEvent.VK_K:
                if (!isModeChosen) {
                    isKI = true;
                    isModeChosen = true;
                }

            case KeyEvent.VK_SPACE:
                if(isModeChosen && !isRunning && !isDead && !isPause) {
                isRunning = true;
                move(framespeed);
                Snake.vY = 10;
                Snake.vX = 0;
                }
                else if (isDead) {
                    isDead = false;
                    run();
                }
                else if (isPause && !isRunning) {
                    isPause = false;
                    isRunning = true;
                    move(framespeed);
                    Snake.vY = lastVy;
                    Snake.vX = lastVx;
                }
                else if (isModeChosen && !isPause) {
                    isPause = true;
                    isRunning = false;
                    lastVx = Snake.vX;
                    lastVy = Snake.vY;
                    Snake.vY = 0;
                    Snake.vX = 0;
                    snakeT.cancel();
                }
                break;
            case KeyEvent.VK_DOWN:
                if (!Game.isKI)
                if (isRunning) {
                    Snake.vY = 10;
                    Snake.vX = 0;
                }
                break;

            case KeyEvent.VK_LEFT:
                if (!Game.isKI)
                if (isRunning) {
                    Snake.vY = 0;
                    Snake.vX = -10;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (!Game.isKI)
                if (isRunning) {
                    Snake.vY = 0;
                    Snake.vX = 10;
                }
                break;
            case KeyEvent.VK_UP:
                if (!Game.isKI)
                if (isRunning) {
                    Snake.vY = -10;
                    Snake.vX = 0;
                }
                break;
        }
        repaint();
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }




    public static void main(String[] args) {
        new Game();
    }
}


