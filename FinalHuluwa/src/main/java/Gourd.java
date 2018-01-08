import java.io.Serializable;
import java.util.Random;

public class Gourd extends GPlayer implements Serializable {
    private static final long serialVersionUID = -66019032085567574L;

    int rank;

    public Gourd(int x, int y, int rank, Field field) {
        super(x, y, 4, 6, rank+".png", field);
        this.rank = rank;
    }

}
