import java.io.Serializable;
import java.util.Random;

public abstract class SPlayer extends Player implements Serializable {
    private static final long serialVersionUID = -66019032084645474L;

    public SPlayer(int x, int y, int speed, int atk, String img, Field field) {
        super(x, y, speed, atk, img, field);
    }

    public void run(){
        while (!Thread.interrupted() && getLiveState()&& !isOver()) {
            Random rand = new Random();

            FindEnemy enemy = findNextEnemy("G");

            //System.out.println("MY:"+x()+','+y()+' '+"S:"+enemy.getEnemyX()+','+enemy.getEnemyY());

            if(enemy != null) {
                movingTo(enemy.getEnemyX(), enemy.getEnemyY());
            }

            try {

                Thread.sleep(rand.nextInt(20) * (11 - getSpeed()));
                repaintField();

            } catch (Exception e) {

            }
        }
    }

}
