import java.io.Serializable;
import java.util.Random;

public abstract class GPlayer extends Player implements Serializable {
    private static final long serialVersionUID = -6601903208557464574L;

    public GPlayer(int x, int y, int speed, int atk, String img, Field field){
        super(x, y, speed, atk, img, field);
    }

    public void run(){

        while (!Thread.interrupted() && getLiveState() && !isOver()) {
            Random rand = new Random();

            FindEnemy enemy = findNextEnemy("S");

            //System.out.println("MY:"+x()+','+y()+' '+"G:"+enemy.getEnemyX()+','+enemy.getEnemyY());

            if(enemy != null) {
                movingTo(enemy.getEnemyX(), enemy.getEnemyY());
            }

            //this.move(this.getSpeed(), 0);
            try {

                Thread.sleep(rand.nextInt(20) * (11 - getSpeed()));
                repaintField();

            } catch (Exception e) {

            }
        }
    }

}
