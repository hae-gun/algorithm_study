package week1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Prog_42895 {
    public static void main(String[] args) {
        System.out.println(Solution42895.solution(5,12) == 4);
    }
}
class Solution42895 {
    public static int solution(int N, int number) {
        int answer = 0;
        List<Set<Integer>> countList = new ArrayList<>();

        for(int i=0; i<=9; i++){
            countList.add(new HashSet<Integer>());
        }
        countList.get(1).add(N);

        for(int i=2; i<=9; i++){
            Set<Integer> set = countList.get(i);

            for(int j=1; j<=i; j++){
                Set<Integer> preSet = countList.get(j);
                Set<Integer> postSet = countList.get(i - j);
                for(int preNum : preSet){
                    for(int postNum : postSet){
                        set.add(preNum + postNum);
                        set.add(preNum - postNum);
                        set.add(preNum * postNum);

                        if(preNum != 0 && postNum != 0)
                            set.add(preNum / postNum);
                    }
                }
            }
            String str = String.valueOf(N);
            String ss = "";
            for(int j=0; j<i; j++){
                ss += str;
            }
//            System.out.println(ss);
            set.add(Integer.parseInt(ss));
        }
        for(Set<Integer> set : countList){
            if(set.contains(number))
                return countList.indexOf(set);
        }
        return -1;
    }
}