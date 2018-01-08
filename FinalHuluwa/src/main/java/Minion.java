import java.io.Serializable;
import java.util.Random;

public class Minion extends SPlayer implements Serializable {
    private static final long serialVersionUID = -660194318557464574L;

    public Minion(int x, int y, Field field){
        super(x, y, 7, 3, "Minion.png", field);
    }

}
