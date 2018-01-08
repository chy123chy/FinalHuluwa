import javax.swing.JFrame;
import java.awt.*;


public final class Main extends JFrame {

    private final int OFFSET = 100;


    public Main() {
        InitUI();
    }

    public void InitUI() {

        Field field = new Field();
        add(field);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //System.out.println(field.getBoardHeight());
        setSize(1000+26,
                600+71);
        setLocationRelativeTo(null);
        setTitle("Huluwa v1.0");
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.setVisible(true);
    }
}

