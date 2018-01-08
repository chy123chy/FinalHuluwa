import java.io.Serializable;
import java.util.Random;

public class Scorpion extends SPlayer implements Serializable {
    private static final long serialVersionUID = -6601903208557461274L;

    public Scorpion(int x, int y, Field field){
        super(x, y, 2, 5, "Scorpion.png", field);
    }

}
