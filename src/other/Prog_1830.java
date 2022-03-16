package other;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Prog_1830 {
    public static void main(String[] args) {
        System.out.println("sss".replaceAll("s", ""));
    }
}
class Solution {
    public String solution(String sentence) {
        String answer = "";
        Set<Character> set = new HashSet<>();

        for(int i=0; i<sentence.length(); i++){
            if(sentence.charAt(i) >='a' && sentence.charAt(i) <='z')
                set.add(sentence.charAt(i));
        }

        int[] lastIndex = new int[set.size()];
        for(int i=0; i<lastIndex.length; i++){
         //   lastIndex[i] = sentence.lastIndexOf();
        }
        System.out.println(Arrays.toString(lastIndex));


        return answer;
    }
}
