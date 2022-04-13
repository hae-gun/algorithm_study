package datastructure;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Set {
    public static void main(String[] args) {
        HashSet<String> hashSet = new HashSet<>(20,0.5f);
        hashSet.contains("s");
        hashSet.add("a");
        System.out.println(hashSet.iterator().next());

        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.spliterator();
        linkedHashSet.add("S");


        EnumSet<MyEnum> enumSet = EnumSet.of(MyEnum.A, MyEnum.B);

        TreeSet<String> treeSet = new TreeSet<>(Collections.reverseOrder());





    }
}
enum MyEnum{
    A,B,C;
}




