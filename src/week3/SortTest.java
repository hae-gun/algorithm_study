package week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class SortTest {
    public static void main(String[] args) {
        ArrayList<myObject2> list = new ArrayList<myObject2>();

        list.add(new myObject2("A", 2, 2));
        list.add(new myObject2("A", 1, 1));
        list.add(new myObject2("A", 2, 1));
        list.add(new myObject2("A", 1, 2));

        System.out.println("before sorting:" + list);
        Collections.sort(list, (o1,o2) -> {
            int[] num1 = o1.numbers;
            int[] num2 = o2.numbers;
            for(int i=0; i<o1.numbers.length;i++){
                if(num1[i]==num2[i]) continue;
                else if (num1[i] > num2[i]) return 1;
                else if (num1[i] < num2[i]) return -1;
            }
            return 0;
        } );

        System.out.println("after sorting:" + list);
    }
}

class myObject2 {
    String name;
    int[] numbers;
    public myObject2(String name, int... numbers) {
        this.name = name;
        this.numbers = numbers;
    }
    @Override
    public String toString() {
        return this.name + ":" + Arrays.toString(numbers);
    }
}