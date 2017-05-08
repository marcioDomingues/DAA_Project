import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mdomingues on 08/05/17.
 */
public class AuxTools {

    public static final int MegaBytes = 1024 * 1024;


    public static ArrayList<Integer> reverse(ArrayList<Integer> list) {
        if (list.size() > 1) {
            Integer value = list.remove(0);
            reverse(list);
            list.add(value);
        }
        return list;
    }

    //generate random value between max and min
    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
