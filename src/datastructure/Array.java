package datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Array {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4};
        boolean[] arr2 = {true, false, true, false};
        boolean[] arr2_1 = {true, false, true, false};
        boolean[] arr2_2 = new boolean[]{true, false, true, false};
        MyClass[] classes = {new MyClass(), new MyClass()};
        MyClass[] classes2 = new MyClass[10];
        System.out.println(Arrays.toString(classes));
        char[] arr3 = {'a', 'b', 'c', 'd'};
        float[] arr4 = {0.1f, 0.2f, 0.3f, 0.4f};
        String[] arr5 = {"a", "b", "c", "d", "e"};
        Object[] arr6 = {null, null, null, null};
        ArrayList<Integer> list = new ArrayList<>();

        System.out.println(Arrays.deepToString(classes));
        System.out.println(Arrays.deepToString(classes2));
    }
}

class MyClass {
    String name;

    @Override
    public String toString() {
        return "MyClass{" +
                "name='" + name + '\'' +
                '}';
    }
}

