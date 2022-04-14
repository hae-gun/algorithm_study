package datastructure;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class MySet {
    public static void main(String[] args) {
        HashSet<String> hashSet = new HashSet<>(20,0.5f);
        hashSet.contains("s");
        hashSet.add("a");
        System.out.println(hashSet.iterator().next());

        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.spliterator();
        linkedHashSet.add("S");


        EnumSet<MyEnum> enumSet = EnumSet.of(MyEnum.A, MyEnum.B);
        int[] test = {1,9,2,5,3,4,7,6,8};

       // System.out.println(treeSet.stream().reduce((o1,o2) -> o1+o2).get());

    }
}
enum MyEnum{
    A,B,C;
}




