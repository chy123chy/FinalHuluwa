import java.io.Serializable;
import java.util.Random;

public class Snake extends SPlayer implements Serializable {
    private static final long serialVersionUID = -66208557464574L;

    private final static String He =
            "....................\n" +
                    "....................\n" +
                    "............M......M\n" +
                    ".............M....M.\n" +
                    "..............M..M..\n" +
                    "...............SC...\n" +
                    "....................\n" +
                    "....................\n";

    private final static String Yan =
            "............M.......\n" +
                    ".............M......\n" +
                    "..............M.....\n" +
                    "...............S....\n" +
                    "................C...\n" +
                    ".................M..\n" +
                    "..................M.\n" +
                    "...................M\n";

    private final static String Heng =
            ".................M..\n" +
                    "..................M.\n" +
                    ".................M..\n" +
                    "..................S.\n" +
                    ".................C..\n" +
                    "..................M.\n" +
                    ".................M..\n" +
                    "..................M.\n";

    private final static String She =
            "..................M.\n" +
                    "..................M.\n" +
                    "..................M.\n" +
                    "..................S.\n" +
                    "..................C.\n" +
                    "..................M.\n" +
                    "..................M.\n" +
                    "..................M.\n";

    private final static String Yu =
            "....................\n" +
                    "....................\n" +
                    "...............M....\n" +
                    "..............M...M.\n" +
                    ".............M.S.C.M\n" +
                    ".................M..\n" +
                    "....................\n" +
                    "....................\n" ;

    private final static String Fang =
            "....................\n" +
                    "....................\n" +
                    ".................M..\n" +
                    "................M.M.\n" +
                    "...............C...S\n" +
                    "................M.M.\n" +
                    ".................M..\n" +
                    "....................\n" ;

    private final static String Yue =
            "....................\n" +
                    "................M...\n" +
                    ".................MM.\n" +
                    "..................CS\n" +
                    ".................MM.\n" +
                    "................M...\n" +
                    "....................\n" +
                    "....................\n";

    private final static String Feng =
            "....................\n" +
                    "..................M.\n" +
                    ".................M..\n" +
                    "................CMMS\n" +
                    ".................M..\n" +
                    "..................M.\n" +
                    "....................\n" +
                    "....................\n";

    private static String formation;

    public Snake(int x, int y, Field field){
        super(x, y, 5, 9, "Snake.png",field);
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
