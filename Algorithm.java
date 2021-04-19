package Snake;

public class Algorithm {

    private static boolean geradeHoehe = false, geradeBreite = false;

    static void move() {

        //while (Snake.x != 0 && Snake.y != 0)
          //  start();
        algorithm();
    }

    static void start() {

        if ((Game.height-20)%2 == 0)
            geradeHoehe = true;
        else if (Game.width%2 == 0)
            geradeBreite = true;

        if (geradeHoehe && geradeBreite) {
            if (Snake.x != 0) {
                Snake.vX = -10;
            } else if (Snake.y != 0)
                Snake.vY = -10;
        }
    }

    static void algorithm() {

        if (Snake.y/10%2 == 0 && Snake.x + 10 != Game.width && Snake.x > 0) {
            Snake.vX = 10;
            Snake.vY = 0;
        } else if (Snake.y/10%2 == 1 && Snake.x - 10 > 0) {
            Snake.vX = -10;
            Snake.vY = 0;
        } else if (Snake.x == Game.width - 10 && Snake.y/10%2 == 0) {
            Snake.vX = 0;
            Snake.vY = 10;
        } else if (Snake.x == 10 && Snake.y/10%2 == 1 && Snake.y+10 != Game.height-20) {
            Snake.vX = 0;
            Snake.vY = 10;
        } else if (Snake.y+10 == Game.height-20 && Snake.x == 0) {
            Snake.vX = 0;
            Snake.vY = -10;
        } else if (Snake.x == 0 && Snake.y != 0) {
            Snake.vX = 0;
            Snake.vY = -10;
        } else if (Snake.x == 0 && Snake.y == 0) {
            Snake.vX = 10;
            Snake.vY = 0;
        }
    }

}
