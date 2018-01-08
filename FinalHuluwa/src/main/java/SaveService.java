import java.util.ArrayList;

public interface SaveService {
    public ArrayList<GPlayer> getGplayers();
    public ArrayList<SPlayer> getSplayers();
    public boolean getCompleted();
}
