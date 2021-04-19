package Snake;

import java.util.Random;

class KI {

    private static int sumUp, sumLeft, sumRight, sumDown, sumApple, dif, upX, upY, leftX, leftY, rightX, rightY, downX, downY;
    private static boolean up,left,right,down;
    private static String dir;

    //TODO: fix getDir() is always "up"

    private static void variables() {
        upX = Snake.x;
        upY = Snake.y - 10;
        leftX = Snake.x - 10;
        leftY = Snake.y;
        rightX = Snake.x + 10;
        rightY = Snake.y;
        downX = Snake.x;
        downY = Snake.y + 10;
        /*sumUp = upX+upY;
        sumLeft = leftX+leftY;
        sumRight= rightX+rightY;
        sumDown = downX+downY;
        sumApple = Apple.x+Apple.y;*/
        up = true;
        left = true;
        right = true;
        down = true;
    }
    static void ki() {


        if (!Game.isDead) {
            check();

            if (up) {
                Snake.vX = 0;
                Snake.vY = -10;
            }
            else if (left) {
                Snake.vX = -10;
                Snake.vY = 0;
            }
            else if (down) {
                Snake.vX = 0;
                Snake.vY = 10;
            }
            else if (right) {
            Snake.vX = 10;
            Snake.vY = 0;
            }

            move();
        }
    }

    private static void check() {

        fastestWay();

        //game end
        if (KI.leftX == -Snake.width) {
            left = false;
        } if (KI.rightX == Game.width) {
            right = false;
        } if (KI.upY == -Snake.height) {
            up = false;
        } if (KI.downY == Game.height - 20) {
            down = false;
        }

        //nach hinten gehen
        if (KI.upX == Snake.lastX[Snake.n] && KI.upY == Snake.lastY[Snake.n]) {
            up = false;
        } if (KI.leftX == Snake.lastX[Snake.n] && KI.leftY == Snake.lastY[Snake.n]) {
            left = false;
        } if (KI.rightX == Snake.lastX[Snake.n] && KI.rightY == Snake.lastY[Snake.n]) {
            right = false;
        } if (KI.downX == Snake.lastX[Snake.n] && KI.downY == Snake.lastY[Snake.n]) {
            down = false;
        }

        //weitere Körperteile
        for (int i=0; i<Snake.n;i++){
            if (KI.upX == Snake.bootyX[i] && KI.upY == Snake.bootyY[i]) {
                up = false;
            } if (KI.leftX == Snake.bootyX[i] && KI.leftY == Snake.bootyY[i]) {
                left = false;
            } if (KI.rightX == Snake.bootyX[i] && KI.rightY == Snake.bootyY[i]) {
                right = false;
            } if (KI.downX == Snake.bootyX[i] && KI.downY == Snake.bootyY[i]) {
                down = false;
            }
        }
        System.out.println(1);
        System.out.println("up: " + up);
        System.out.println("down: " + down);
        System.out.println("left: " + left);
        System.out.println("right: " + right);

        //Apfel in gleicher Spalte, aber "hinter" Kopf
        if (Apple.x == Snake.x) {
            if (!up && !down && !left && !right) {
                if (Apple.x > Snake.lastX[Snake.n] && Snake.lastX[Snake.n] > Snake.x) {
                    for (int i = 0; i < Snake.n; i++) {
                        if (KI.leftX != Snake.bootyX[i] && KI.leftY != Snake.bootyY[i] && KI.leftX != -Snake.width)
                            left = true;
                        else if (KI.rightX != Snake.bootyX[i] && KI.rightY != Snake.bootyY[i] && KI.rightX != Game.width)
                            right = true;
                        else { //if (KI.leftX != Snake.bootyX[i] && KI.leftY != Snake.bootyY[i] && KI.rightX != Snake.bootyX[i] && KI.rightY != Snake.bootyY[i]){
                            Random random = new Random();
                            int a = random.nextInt(2);
                            if (a == 0)
                                right = true;
                            else if (a == 1)
                                left = true;
                        }
                    }
                } else if (Apple.x < Snake.lastX[Snake.n] && Snake.lastX[Snake.n] < Snake.x) {
                    for (int i = 0; i < Snake.n; i++) {
                        if (KI.leftX != Snake.bootyX[i] && KI.leftY != Snake.bootyY[i] && KI.leftX != -Snake.width)
                            left = true;
                        else if (KI.rightX != Snake.bootyX[i] && KI.rightY != Snake.bootyY[i] && KI.rightX != Game.width)
                            right = true;
                        else { //if (KI.leftX != Snake.bootyX[i] && KI.leftY != Snake.bootyY[i] && KI.rightX != Snake.bootyX[i] && KI.rightY != Snake.bootyY[i]){
                            Random random = new Random();
                            int a = random.nextInt(2);
                            if (a == 0)
                                right = true;
                            else if (a == 1)
                                left = true;
                        }
                    }
                }
            }
        }
        //Apfel in gleicher Reihe, aber "hinter" Kopf
        else if (Apple.y == Snake.y) {
            if (!up && !down && !left && !right) {
                if (Apple.y > Snake.lastY[Snake.n] && Snake.lastY[Snake.n] > Snake.y) {
                    for (int i = 0; i < Snake.n; i++) {
                        if (KI.upX != Snake.bootyX[i] && KI.upY != Snake.bootyY[i] && KI.upY != -Snake.height)
                            up = true;
                        else if (KI.downX != Snake.bootyX[i] && KI.downY != Snake.bootyY[i] && KI.downY != Game.height - 20)
                            down = true;
                        else { //if (KI.upX != Snake.bootyX[i] && KI.upY != Snake.bootyY[i] && KI.downX != Snake.bootyX[i] && KI.downY != Snake.bootyY[i]){
                            Random random = new Random();
                            int a = random.nextInt(2);
                            if (a == 0)
                                up = true;
                            else if (a == 1)
                                down = true;
                        }
                    }
                } else if (Apple.y < Snake.lastY[Snake.n] && Snake.lastY[Snake.n] < Snake.y) {
                    for (int i = 0; i < Snake.n; i++) {
                        if (KI.upX != Snake.bootyX[i] && KI.upY != Snake.bootyY[i] && KI.upY != -Snake.height)
                            up = true;
                        else if (KI.downX != Snake.bootyX[i] && KI.downY != Snake.bootyY[i] && KI.downY != Game.height - 20)
                            down = true;
                        else { //if (KI.upX != Snake.bootyX[i] && KI.upY != Snake.bootyY[i] && KI.downX != Snake.bootyX[i] && KI.downY != Snake.bootyY[i]){
                            Random random = new Random();
                            int a = random.nextInt(2);
                            if (a == 0)
                                up = true;
                            else if (a == 1)
                                down = true;
                        }
                    }
                }
            }
        }
        System.out.println(2);
        System.out.println("up: " + up);
        System.out.println("down: " + down);
        System.out.println("left: " + left);
        System.out.println("right: " + right);


        //sonstige Fälle
        if (!up && !down && !left && !right) {
            for (int i = 0; i <= Snake.n; i++) {
                if (KI.leftX != -Snake.width && KI.leftX != Snake.bootyX[i] && KI.leftY != Snake.bootyY[i] && KI.leftX != Snake.lastX[Snake.n] && KI.leftY != Snake.lastY[Snake.n])
                    left = true;
                if (KI.rightX != Game.width && KI.rightX != Snake.bootyX[i] && KI.rightY != Snake.bootyY[i] && KI.rightX != Snake.lastX[Snake.n] && KI.rightY != Snake.lastY[Snake.n])
                    right = true;
                if (KI.upY != -Snake.height && KI.upX != Snake.bootyX[i] && KI.upY != Snake.bootyY[i] && KI.upX != Snake.lastX[Snake.n] && KI.upY != Snake.lastY[Snake.n])
                    up = true;
                if (KI.downY != Game.height - 20 && KI.downX != Snake.bootyX[i] && KI.downY != Snake.bootyY[i] && KI.downX != Snake.lastX[Snake.n] && KI.downY != Snake.lastY[Snake.n])
                    down = true;
            }

            if (left && right) {
                for (int i=1;i<Game.width/10;i++) {
                    int x = Snake.x + 10*i;
                    int x2 = Snake.x - 10*i;
                    for (int j=0; j<Snake.n; j++) {
                        if (x == Snake.bootyX[j] || x == Snake.lastX[Snake.n])
                            right = false;
                        else if (x2 == Snake.bootyX[j] || x2 == Snake.lastX[Snake.n])
                            left = false;
                    }
                }
            } if (up && down) {
                for (int i=1;i<Game.width/10;i++) {
                    int y = Snake.y + 10*i;
                    int y2 = Snake.y - 10*i;
                    for (int j=0; j<Snake.n; j++) {
                        if (y == Snake.bootyY[j] || y == Snake.lastY[Snake.n])
                            down = false;
                        else if (y2 == Snake.bootyY[j] || y2 == Snake.lastY[Snake.n])
                            up = false;
                    }
                }
            }
        }

        System.out.println(3);
        System.out.println("up: " + up);
        System.out.println("down: " + down);
        System.out.println("left: " + left);
        System.out.println("right: " + right);
        /*if (!up && !down) {
            for (int i=1;i<Game.width/10;i++) {
                int x = Snake.x + 10*i;
                int x2 = Snake.x - 10*i;
                for (int j=0; j<Snake.n; j++) {
                    if (x == Snake.bootyX[j])
                        left = true;
                    else if (x2 == Snake.bootyX[j])
                        right = true;
                }
            }
        }*/
    }

    //beste, schnellste Richtung zum Apfel
    private static void fastestWay() {

        if (Apple.x < Snake.x && Apple.y < Snake.y) {
            left = true;
            up = true;
            right = false;
            down = false;
        } else if (Apple.x < Snake.x && Apple.y > Snake.y) {
            left = true;
            down = true;
            right = false;
            up = false;
        } else if (Apple.x > Snake.x && Apple.y > Snake.y) {
            right = true;
            down = true;
            left = false;
            up = false;
        } else if (Apple.x > Snake.x && Apple.y < Snake.y) {
            right = true;
            up = true;
            left = false;
            down = false;
        }

        if (Apple.x == Snake.x && Apple.y < Snake.y) {
            left = false;
            right = false;
            up = true;
            down = false;
        } else if (Apple.x == Snake.x && Apple.y > Snake.y) {
            left = false;
            right = false;
            up = false;
            down = true;
        } else if (Apple.y == Snake.y && Apple.x < Snake.x) {
            up = false;
            down = false;
            right = false;
            left = true;
        } else if (Apple.y == Snake.y && Apple.x > Snake.x) {
            up = false;
            down = false;
            right = true;
            left = false;
        }
    }

    private static void move() {
        Snake.lastX[0] = Snake.x;
        Snake.lastY[0] = Snake.y;

        Snake.y += Snake.vY;
        Snake.x += Snake.vX;

        for (int i = 1; i<=Snake.n+1; i++) {
            Snake.lastX[i] = Snake.bootyX[i-1];
            Snake.lastY[i] = Snake.bootyY[i-1];

            Snake.bootyX[i-1] = Snake.lastX[i-1];
            Snake.bootyY[i-1] = Snake.lastY[i-1];
        }

        variables();
    }


    //Richtung, NICHT BEACHTEN!!
    private static String getDir() {
        Random r = new Random();
        int x;

        if (sumUp == getMin()) {
            dir = "up";
            up = true;
            if (sumUp == sumLeft){
                x = r.nextInt(1);
                if (x == 0) {
                    dir = "up";
                    up = true;
                    left = false;
                }
                else if (x==1){
                    dir = "left";
                    left = true;
                    up = false;
                }
            }
        }
        if (sumDown == getMin()) {
            dir = "down";
            down = true;

            if (sumDown == sumRight){
                x = r.nextInt(1);
                if (x == 0) {
                    dir = "down";
                    down = true;
                    right = false;
                }
                else if (x==1) {
                    dir = "right";
                    right = true;
                    down = false;
                }
            }
        }
        if (sumLeft == getMin()) {
            dir = "left";
            left = true;
        }
        if (sumRight == getMin()) {
            dir = "right";
            right = true;
        }
        return dir;
    }

    //kleinster Abstand, NICHT BEACHTEN
    private static int getMin() {
        int max = Math.max(Math.max(sumUp,sumDown),Math.max(sumLeft,sumRight));
        if (Math.max(sumUp,sumDown) == Math.max(sumLeft,sumRight))
            max = Math.max(sumUp,sumDown);
        if (Math.max(sumUp,sumDown) == sumUp)
            up = true;
        else down = true;
        if (Math.max(sumLeft,sumRight) == sumLeft)
            left = true;
        else right = true;
        dif = sumApple - max;
        return max;
     }

}
