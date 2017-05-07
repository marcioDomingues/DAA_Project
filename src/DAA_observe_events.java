import java.util.ArrayList;
import java.util.Random;

/******************************************************************************
 *  Compilation:  javac DAA_observe_events.java
 *  Executions:
 *          java DAA_observe_events
 *
 *              will use a predifined array as example
 *
 *          java DAA_observe_events -r N
 *
 *              Generates problem with N items with random values from -N to N degrees
 *              This way it will always be possible to visit the last event
 *
 *          java DAA_observe_events -l a b c d e ..... n
 *
 *              The user will input a list of angles separated by space
 *
 *              example
 *               java DAA_observe_events -l 1 -4 -1 4 5 -4 6 7 -2
 *
 * * Created by mdo on 5/7/17.
 ******************************************************************************/
public class DAA_observe_events {


    public ArrayList<Integer> reverse(ArrayList<Integer> list) {
        if (list.size() > 1) {
            Integer value = list.remove(0);
            reverse(list);
            list.add(value);
        }
        return list;
    }

    //generate random value between max and min
    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


    //solution based on LIS - Longest Increasing Subset
    //Using a bottom-up iteractive solution
    public ArrayList<Integer> findMaxObservableEvents(int[] angles) {
        //LPE = Longest Subset of Possible Events
        //AUX list to mark the chosen events
        int[] LSPE = new int[angles.length];

        for (int i = 0; i < angles.length; i++) {
            //in the begining we have no events in the result set
            int max_n_events = 0;
            //verify if its possible to go from initial telescope position to event i
            //and
            //verify if its possible to go from event i to event last event that we need to visit
            if (Math.abs(angles[i] - angles[angles.length - 1]) <= (angles.length - 1) - i && (Math.abs(angles[i] - 0) <= i + 1)) {

                for (int j = 0; j <= i; j++) {
                    if (Math.abs(angles[j] - angles[i]) <= i - j) {
                        // update the max from the previous entries
                        if (max_n_events == 0 || max_n_events < LSPE[j] + 1) {
                            max_n_events = 1 + LSPE[j];
                        }
                    }
                }
            }
            if (max_n_events == -1) {
                // means none of the previous events was possible
                max_n_events = 0;
            }
            LSPE[i] = max_n_events;
        }

        //TODO just for printing tests delete this
        // find the max in the LIS[]
        int result = -1;
        int index = -1;
        for (int i = 0; i < LSPE.length; i++) {
            if (result < LSPE[i]) {
                result = LSPE[i];
                index = i;
            }
        }

        //TODO just for printing tests delete this
        // Print the result
        // Start moving backwards from the end and
        String path = angles[index] + " ";
        int res = result - 1;
        for (int i = index - 1; i >= 0; i--) {
            if (LSPE[i] == res) {
                path = angles[i] + " " + path;
                res--;
            }
        }

        //TODO just for printing tests delete this
        //System.out.println("LPSE: " + result);
        //System.out.println("Actual Elements: " + path);


        //result set
        ArrayList<Integer> resultArray = new ArrayList<Integer>();

        //because we consider the initial position of the telescope
        //and we dont want that event in the final set
        //we go thoughthe marked events
        //add from the end because a result can be duplicated in LSPE
        // and we want the most recent from the possible solution
        int numberOfEvents = result;
        for (int i = index; i > 0; i--) {
            if (LSPE[i] == numberOfEvents) {
                resultArray.add(i);
                numberOfEvents--;
            }
        }

        return reverse(resultArray);
    }


    public static void main(String[] args) {

        int[] inputArray = new int[9];
        ;
        //type of execution
        if (args.length == 0) {
            System.out.println("No option selected the next example will be used");
            System.out.println(" 1 -4 -1 4 5 -4 6 7 -2");
            inputArray[0] = 1;
            inputArray[1] = -4;
            inputArray[2] = -1;
            inputArray[3] = 4;
            inputArray[4] = 5;
            inputArray[5] = -4;
            inputArray[6] = 6;
            inputArray[7] = 7;
            inputArray[8] = -2;

        } else {

            String type = args[0];

            switch (type) {
                case "-r":
                    int N = Integer.parseInt(args[1]);   // number of items
                    inputArray = new int[N];

                    for (int n = 0; n < N; n++) {
                        inputArray[n] = getRandomNumberInRange( (N*-1) , N );;
                    }

                    //user feedback
                    /*
                    System.out.print("Generated input Array: { ");
                    for (int i = 0; i < inputArray.length; i++) {
                        if ( i==0 ) System.out.print( inputArray[i]);
                        System.out.print( ", " + inputArray[i]);
                    }
                    System.out.println(" }");
                    */

                    break;
                case "-l":

                    inputArray = new int[args.length - 1];

                    for (int i = 1; i < args.length; i++) {
                        inputArray[i - 1] = Integer.parseInt(args[i]);
                    }

                    break;
                default:
                    System.out.println("wrong input");

                    break;
            }
        }


        //INPUTS
        //int[] inputArray = { 1, 12, 7, 0, 23, 11, 52, 31, 61, 69, 70, 2 };
        //int[] inputArray = {3, 2, -3, -2, -1, 0};
        //int[] inputArray = {1, -4, -1, 4, 5, -4, 6, 7, -2};


        //include the initial position of the telescope

        int[] angles = new int[inputArray.length + 1];
        //copy input values into new array
        angles[0] = 0;
        for (int i = 0; i < inputArray.length; i++) {
            angles[i + 1] = inputArray[i];
        }


        DAA_observe_events i = new DAA_observe_events();

        long startTime = System.currentTimeMillis();
        ArrayList<Integer> res = i.findMaxObservableEvents(angles);
        long estimatedTime = System.currentTimeMillis() - startTime;

        System.out.println("Result: " + res);

        System.out.println("Execution Time: " + estimatedTime);



    }


}