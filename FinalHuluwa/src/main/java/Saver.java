import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Saver implements Serializable {
    private static final long serialVersionUID = -660190320809574L;

    static int num = 0;
    static FileOutputStream os;
    static ObjectOutputStream oos;
    static {
        try {
            os = new FileOutputStream("save" + num + ".data");
            oos = new ObjectOutputStream(os);
        }catch (Exception e){
            System.out.println("Open failed.");
        }
    }

    public static void savePlayers(SaveService field) {
        if(field.getCompleted()) {
            if (num == 9)
                num = 0;
            else num++;
            try {
                os.close();
            }catch (Exception e){
                System.out.println("Close failed.");
            }
            return;
        }

        try {
            oos.writeInt(field.getGplayers().size());
        }catch (Exception e){
            System.out.println("Write Gnum failed.");
        }
        for(GPlayer player: field.getGplayers()){
            try {
                oos.writeObject(player);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Write Gplayer failed.");
            }
        }

        try {
            oos.writeInt(field.getSplayers().size());
        }catch (Exception e){
            System.out.println("Write Snum failed.");
        }
        for(SPlayer player: field.getSplayers()){
            try {
                oos.writeObject(player);
            }catch (Exception e){
                System.out.println("Write Splayer failed.");
            }

        }

    }

    public void saveTime(long time){
        try {
            oos.writeLong(time);
        }catch (Exception e){
            System.out.println("Write time failed.");
        }
    }

}
