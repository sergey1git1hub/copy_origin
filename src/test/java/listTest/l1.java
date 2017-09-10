package listTest;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by SChubuk on 22.08.2017.
 */
class Temp{}
public class l1 {
    //@Test
    public void arraylist() {

        /*add get remove add get remove add ger remove*/
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        numbers.add(10);
        numbers.add(100);
        numbers.add(40);

        System.out.println(numbers.get(0));
        System.out.println("\nIteration #1: ");

        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i));
        }

        System.out.println("\nIteration #2: ");
        for (Integer value : numbers) {
            System.out.println(value);
        }

        numbers.remove(numbers.size() - 1);
        numbers.remove(0);

        List<String> values = new ArrayList<String>();

    }

    @Test
    public void l2() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        for(int i = 0; i<10; i++){
        doTimings(arrayList, linkedList);
        }
    }

    private static void doTimings(ArrayList<Integer> arrayList, LinkedList<Integer> linkedList ) {
        for (int i = 0; i < 1E6; i++) {
            arrayList.add(i);
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1E6; i++) {
            arrayList.add(i);
        }
        long end = System.currentTimeMillis();
        long arrayListTime = end - start;
        //System.out.println("Time taken: " + (arrayListTime) +  " ms for " + arrayList);

        for (int i = 0; i < 1E6; i++) {
            linkedList.add(i);
        }

        start = System.currentTimeMillis();
        for (int i = 0; i < 1E6; i++) {
            linkedList.add(i);
        }
        end = System.currentTimeMillis();
        System.out.println("Time taken: " + arrayListTime +  " ms for  + ArrayList  Time taken: " + (end - start)  + "for LinkedList");

    }

    @Test
    public void l3(){
            HashMap<Integer, String> map = new HashMap();
            map.put(5, "Five");
            map.put(8, "Eight");
            map.put(6, "Six");
            map.put(4, "Four");
            map.put(2, "Two");

            String text = map.get(4);
            String doesnotexist = map.get(3);
            System.out.println(text);
            System.out.println(doesnotexist);

            map.put(6, "6");
            //duplicate values but not duplicate keys
            //for(integer value :values) - interates through elements of an array
    }

    @Test
    public void test(){
    System.out.println(new Temp());
    }
}

/*
* HashMap
* LinkedHashMap
* TreeMap
*
* */