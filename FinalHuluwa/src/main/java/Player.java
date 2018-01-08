import java.awt.Image;
import java.io.Serializable;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;

public abstract class Player extends Thing2D implements Runnable, FightService, FindEnemy, Serializable {
    private static final long serialVersionUID = -66019032085573474L;

    enum Direction{UP, DOWN, LEFT, RIGHT, LU, LD, RU, RD}

    private Field field;

    private boolean liveState;

    private int speed, atk;

    public Player(int x, int y, int speed, int atk, String img, Field field) {
        super(x, y);
        this.speed = speed;
        this.atk = atk;
        liveState = true;

        this.field = field;

        URL loc = this.getClass().getClassLoader().getResource(img);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public boolean move(Direction direction) {
        int nx = this.x(), ny = this.y();
        switch (direction){
            case UP:
                ny = this.y() + 1;
                break;
            case DOWN:
                ny = this.y() - 1;
                break;
            case LEFT:
                nx = this.x() - 1;
                break;
            case RIGHT:
                nx = this.x() + 1;
                break;
            case LU:
                nx = this.x() - 1;
                ny = this.y() + 1;
                break;
            case LD:
                nx = this.x() - 1;
                ny = this.y() - 1;
                break;
            case RU:
                nx = this.x() + 1;
                ny = this.y() + 1;
                break;
            case RD:
                nx = this.x() + 1;
                ny = this.y() - 1;
                break;
            default:break;
        }

        if(field.isPointValid(nx, ny)) {
            this.setX(nx);
            this.setY(ny);
            return true;
        }
        return false;
    }

    public void run() {

    }

    public void repaintField(){
        this.field.repaintField();
    }

    public int getATK(){
        return atk;
    }

    public int getSpeed(){
        return speed;
    }

    public boolean getLiveState(){
        return liveState;
    }

    public int getEnemyX(){
        return this.x();
    }

    public int getEnemyY(){
        return this.y();
    }

    public Player findNextEnemy(String enemy){
        return field.findNearestEnemy(this, enemy);
    }

    public void movingTo(int x, int y){
        if(Math.abs(this.x() - x) > Math.abs(this.y() - y))
            if(x - this.x() < 0) {
                if(!move(Direction.LEFT)){
                    Random rand = new Random();
                    int num = rand.nextInt(8);
                    move(Direction.values()[num]);
                }
            }
            else{
                if(!move(Direction.RIGHT)) {
                    Random rand = new Random();
                    int num = rand.nextInt(8);
                    move(Direction.values()[num]);
                }
            }
        else if(Math.abs(this.x() - x) < Math.abs(this.y() - y))
            if(y - this.y() < 0) {
                if(!move(Direction.DOWN)){
                    Random rand = new Random();
                    int num = rand.nextInt(8);
                    move(Direction.values()[num]);
                }
            }
            else {
                if(!move(Direction.UP)) {
                    Random rand = new Random();
                    int num = rand.nextInt(8);
                    move(Direction.values()[num]);
                }
            }
        else
            if(x - this.x() < 0 && y - this.y() < 0) {
                if(!move(Direction.LD)) {
                    Random rand = new Random();
                    int num = rand.nextInt(8);
                    move(Direction.values()[num]);
                }
            }
            else if(x - this.x() < 0 && y - this.y() > 0) {
                if(!move(Direction.LU)) {
                    Random rand = new Random();
                    int num = rand.nextInt(8);
                    move(Direction.values()[num]);
                }
            }
            else if(x - this.x() > 0 && y - this.y() > 0) {
                if(!move(Direction.RU)) {
                    Random rand = new Random();
                    int num = rand.nextInt(8);
                    move(Direction.values()[num]);
                }
            }
            else{
                if(!move(Direction.RD)) {
                    Random rand = new Random();
                    int num = rand.nextInt(8);
                    move(Direction.values()[num]);
                }
            }
    }

    public void die(){
        liveState = false;
        URL loc = this.getClass().getClassLoader().getResource("Dead.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public boolean isOver(){
        return field.getCompleted();
    }
}