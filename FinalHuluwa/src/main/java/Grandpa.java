import java.io.Serializable;
import java.util.Random;

public class Grandpa extends GPlayer implements Serializable {
    private static final long serialVersionUID = -6605443208557464574L;

    private final static String He =
            "....................\n" +
                    "....................\n" +
                    "G......G............\n" +
                    ".G....G.............\n" +
                    "..G..G..............\n" +
                    "...PG...............\n" +
                    "....................\n" +
                    "....................\n";

    private final static String Yan =
            ".......G............\n" +
                    "......G.............\n" +
                    ".....G..............\n" +
                    "....G...............\n" +
                    "...G................\n" +
                    "..G.................\n" +
                    ".G..................\n" +
                    "P...................\n";

    private final static String Heng =
            "..G.................\n" +
                    ".G..................\n" +
                    "..G.................\n" +
                    ".G..................\n" +
                    "..G.................\n" +
                    ".G..................\n" +
                    "..G.................\n" +
                    ".P..................\n";

    private final static String She =
            ".G..................\n" +
                    ".G..................\n" +
                    ".G..................\n" +
                    ".G..................\n" +
                    ".G..................\n" +
                    ".G..................\n" +
                    ".G..................\n" +
                    ".P..................\n";

    private final static String Yu =
            "....................\n" +
                    "....................\n" +
                    "....G...............\n" +
                    ".G...G..............\n" +
                    "G.G.G.G.............\n" +
                    "...P................\n" +
                    "....................\n" +
                    "....................\n" ;

    private final static String Fang =
            "....................\n" +
                    "....................\n" +
                    "..G.................\n" +
                    ".G.G................\n" +
                    "P...G...............\n" +
                    ".G.G................\n" +
                    "..G.................\n" +
                    "....................\n" ;

    private final static String Yue =
            "....................\n" +
                    "...G................\n" +
                    ".GG.................\n" +
                    "PG..................\n" +
                    ".GG.................\n" +
                    "...G................\n" +
                    "....................\n" +
                    "....................\n";

    private final static String Feng =
            "....................\n" +
                    ".G..................\n" +
                    "..G.................\n" +
                    "PGGG................\n" +
                    "..G.................\n" +
                    ".G..................\n" +
                    "....................\n" +
                    "....................\n";

    private static String formation;

    public Grandpa(int x, int y, Field field){
        super(x, y,1, 1, "Grandpa.png", field);
    }

    public static String chooseFormation(){
        Random rand = new Random();
        int num = rand.nextInt(8);
        switch(num){
            case 0: formation = He; break;
            case 1: formation = Yan; break;
            case 2: formation = Heng; break;
            case 3: formation = She; break;
            case 4: formation = Yu; break;
            case 5: formation = Fang; break;
            case 6: formation = Yue; break;
            case 7: formation = Feng; break;
            default:break;
        }
        return formation;
    }

    public String getFormation(){
        return formation;
    }


}
