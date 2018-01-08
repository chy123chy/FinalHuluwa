import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Field extends JPanel implements SaveService, Serializable {
    private static final long serialVersionUID = -6601903278464574L;

    private static boolean isSaving = false;

    private final int OFFSET = 100;
    private final int SPACE = 50;

    //private ArrayList<Tile> tiles = new ArrayList();
    private ArrayList<GPlayer> Gplayers = new ArrayList();
    private ArrayList<SPlayer> Splayers = new ArrayList();
    private ArrayList world = new ArrayList();

    private int w = 0;
    private int h = 0;
    private boolean completed = false;
    private long pauseTime = 0;

    private String level = "";

    public Field() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    public final void initWorld() {

        int x = 0;
        int y = OFFSET;

        int r = 0;

        //Tile a;
        Player p;

        String gourdForm = Grandpa.chooseFormation();
       // System.out.println(gourdForm);
        String snakeForm = Snake.chooseFormation();
        for(int i  = 0; i < (20 + 1) * 8; i++){
            if(i % (20 + 1) >= 0 && i % (20 + 1) < 10) {
                level += gourdForm.charAt(i);
               // System.out.println(level);
               // System.out.println();
            }
            else level += snakeForm.charAt(i);
        }

        for (int i = 0; i < level.length(); i++) {

            char item = level.charAt(i);

            switch (item){
                case '\n':
                    y += SPACE;
                    System.out.println(y);
                    if (this.w < x) {
                        this.w = x;
                    }
                    x = 0;
                    break;
                case '.':
                    //a = new Tile(x, y);
                    //tiles.add(a);
                    x += SPACE;
                    break;
                case 'P':
                    p = new Grandpa(x, y, this);
                    Gplayers.add((GPlayer) p);
                    x += SPACE;
                    break;
                case 'G':
                    p = new Gourd(x, y, r, this);
                    r++;
                    Gplayers.add((GPlayer) p);
                    x += SPACE;
                    break;
                case 'S':
                    p = new Snake(x, y, this);
                    Splayers.add((SPlayer) p);
                    x += SPACE;
                    break;
                case 'C':
                    p = new Scorpion(x, y, this);
                    Splayers.add((SPlayer) p);
                    x += SPACE;
                    break;
                case 'M':
                    p = new Minion(x, y, this);
                    Splayers.add((SPlayer) p);
                    x += SPACE;

                    break;
                case ' ':
                    x += SPACE;
                    break;
            }
            /*if (item == '\n') {
                y += SPACE;
                if (this.w < x) {
                    this.w = x;
                }

                x = OFFSET;
            } else if (item == '.') {
                a = new Tile(x, y);
                tiles.add(a);
                x += SPACE;
            } else if (item == 'P' || item == 'G' || item == 'S' || item == 'C' || item == 'M') {
                p = new Player(x, y, this);
                players.add(p);
                x += SPACE;
            } else if (item == ' ') {
                x += SPACE;
            }*/

            h = y;
        }

        //player = new Player(0+ OFFSET,0+OFFSET, this);

    }

    public void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        //System.out.println(getHeight());
        //System.out.println(getWidth());

        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        URL loc = this.getClass().getClassLoader().getResource("background.png");
        ImageIcon iia = new ImageIcon(loc);
        Image background = iia.getImage();
        g.drawImage(background, 0, 0, this);

        //world.addAll(tiles);

        world.addAll(Gplayers);
        world.addAll(Splayers);

        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            if (item instanceof Player) {
                g.drawImage(item.getImage(), item.x() + 10, item.y() + 10, this);
            } else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }

            if (completed) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }

        }
    }

    public Player findNearestEnemy(Player player, String enemy){
        Player nearestEnemy = null;
        double minDistance = 9999999;
        if(enemy == "S" && !Splayers.isEmpty()){
            //nearestEnemy = Splayers.get(0);
            int x = player.x();
            int y = player.y();
            for(Player p : Splayers){
                if(p.getLiveState()) {
                    double distance = Math.sqrt((p.x() - x) * (p.x() - x) + (p.y() - y) * (p.y() - y));
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestEnemy = p;
                    }
                }
            }
        }
        else if(enemy == "G" && !Gplayers.isEmpty()){
            //nearestEnemy = Gplayers.get(0);
            int x = player.x();
            int y = player.y();
            for(Player p : Gplayers) {
                double distance = Math.sqrt((p.x() - x) * (p.x() - x) + (p.y() - y) * (p.y() - y));
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestEnemy = p;
                }
            }
        }
        if(nearestEnemy == null){
            completed = true;
            if(enemy == "S")
                JOptionPane.showMessageDialog(null, "Calabash Brothers Win!", "Game over",JOptionPane.PLAIN_MESSAGE);
            if(enemy == "G")
                JOptionPane.showMessageDialog(null, "Monsters Win!", "Game over",JOptionPane.PLAIN_MESSAGE);
        }
        return nearestEnemy;
    }

    private void chooseWinner(FightService p1, FightService p2){
        Random rand = new Random();
        double determin1 = rand.nextDouble() * 10 + p1.getATK();
        double determin2 = rand.nextDouble() * 10 + p2.getATK();

        if(determin1 > determin2) {
            p2.die();
            //System.out.println("Hulu win");
        }
        else if(determin1 < determin2) {
            p1.die();
            //System.out.println("Yaojing win");
        }
        else {
            chooseWinner(p1, p2);
            //System.out.println("Draw");
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }

    public void repaintField(){
        if(!Gplayers.isEmpty() && !Splayers.isEmpty()) {
            for (Player p1 : Gplayers)
                for (Player p2 : Splayers) {
                    //System.out.println("P1"+p1.x()+','+p1.y());
                    //System.out.println("P2"+p2.x()+','+p2.y());
                    if (Math.abs(p1.x() - p2.x()) < 20 && Math.abs(p1.y() - p2.y()) < 20 && p1.getLiveState() && p2.getLiveState())
                        chooseWinner((FightService) p1, (FightService) p2);
                }
        }

        if(!isSaving){
            isSaving = true;
            super.repaint();
            Saver saver = new Saver();
            saver.savePlayers(this);
            saver.saveTime(System.currentTimeMillis() - pauseTime);
            pauseTime = System.currentTimeMillis();
            isSaving = false;
        }
    }

    public boolean getCompleted(){
        return this.completed;
    }

    public boolean isPointValid(int x, int y){
        boolean isValid = true;
        if(!Gplayers.isEmpty() && !Splayers.isEmpty()) {
            for (Player p : Gplayers)
                if (p.x() == x && p.y() == y)
                    isValid = false;
            for (Player p : Splayers)
                if (p.x() == x && p.y() == y)
                    isValid = false;
        }
        return isValid;
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            /*if (completed) {
                return;
            }*/


            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {
                pauseTime = System.currentTimeMillis();
                for(Player p: Gplayers){
                    new Thread((Player)p).start();
                }
                for(Player p: Splayers){
                    new Thread((Player)p).start();
                }
            }

            else if (key == KeyEvent.VK_R) {
                restartLevel();
            }

            repaint();
        }
    }


    public void restartLevel() {

        //tiles.clear();
        Gplayers.clear();
        Splayers.clear();
        initWorld();
        if (completed) {
            completed = false;
        }
    }

    public ArrayList<GPlayer> getGplayers(){
        return Gplayers;
    }
    public ArrayList<SPlayer> getSplayers(){
        return Splayers;
    }

}