
import java.util.ArrayList;
public class TestMemoryUtil
{
    private static final int MegaBytes = 1024 * 1024;

    public static void main(String args[])
    {

        long totalMemory = Runtime.getRuntime().totalMemory() / MegaBytes;
        long maxMemory = Runtime.getRuntime().maxMemory() / MegaBytes;
        long freeMemory = Runtime.getRuntime().freeMemory() / MegaBytes;

        System.out.println("**** Heap utilization Analysis [MB] ****");
        System.out.println("JVM totalMemory also equals to initial heap size of JVM :"+ totalMemory);
        System.out.println("JVM maxMemory also equals to maximum heap size of JVM: "+ maxMemory);
        System.out.println("JVM freeMemory: " + freeMemory);

        ArrayList<String> objects = new ArrayList<String>();

        for (int i = 0; i < 10000000; i++)
        {
            objects.add(("" + 10 * 2710));
        }

        totalMemory = Runtime.getRuntime().totalMemory() / MegaBytes;
        maxMemory = Runtime.getRuntime().maxMemory() / MegaBytes;
        freeMemory = Runtime.getRuntime().freeMemory() / MegaBytes;

        System.out.println("Used Memory in JVM: " + (maxMemory - freeMemory));
        System.out.println("totalMemory in JVM shows current size of java heap:"+totalMemory);
        System.out.println("maxMemory in JVM: " + maxMemory);
        System.out.println("freeMemory in JVM: " + freeMemory);

    }
}