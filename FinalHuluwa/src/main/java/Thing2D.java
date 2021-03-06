import java.awt.Image;
import java.io.Serializable;

public abstract class Thing2D implements Serializable {
    private static final long serialVersionUID = -6601903235457464574L;

    private final int SPACE = 20;

    private int x;
    private int y;
    private Image image;

    public Thing2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image img) {
        image = img;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


} 