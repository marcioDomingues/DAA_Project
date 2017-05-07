import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mdo on 5/7/17.
 */
public class PerformanceTest {
    private static final long MEGABYTE = 1024L * 1024L;

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }

    public static void main(String[] args) {
// I assume you will know how to create a object Person yourself... List<Person> list = new ArrayList<Person>();\
        ArrayList <Integer> teste = new ArrayList<Integer>();

        Random randomGenerator = new Random();

        for (int i = 0; i <= 100000; i++) {
            // list.add(new Person("Jim", "Knopf"));
            int randomInt = randomGenerator.nextInt(100);
            teste.add( randomInt );
        }

// Get the Java runtime
        Runtime runtime = Runtime.getRuntime();
// Run the garbage collector
        runtime.gc();
// Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is bytes: " + memory);
        System.out.println("Used memory is megabytes: " + bytesToMegabytes(memory));
    }
}
