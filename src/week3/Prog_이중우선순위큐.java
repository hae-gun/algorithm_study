package week3;

import common.Print;

import java.util.*;
import java.util.stream.Collectors;

public class Prog_이중우선순위큐 {
    public static void main(String[] args) {
        Print.answer(Solution42628.solution(new String[]{"I 16","D 1"}),new int[]{0,0});
        Print.answer(Solution42628.solution(new String[]{"I 16","I 10","D 1"}),new int[]{10,10});
        Print.answer(Solution42628.solution(new String[]{"I 7","I 5","I -5","D -1"}),new int[]{7,5});
        Print.answer(Solution42628.solution(new String[]{"I 1", "I 2", "I 3", "I 4", "I 5", "D -1", "D -1","D -1", "D -1"}),new int[]{5,5});
    }
}
//I 숫자	큐에 주어진 숫자를 삽입합니다.
//D 1	큐에서 최댓값을 삭제합니다.
//D -1	큐에서 최솟값을 삭제합니다.
class Solution42628{
    public static int[] solution(String[] operations) {
        int[] answer = new int[2];
        Deque<Integer> deque = new LinkedList<>();


        PriorityQueue<Integer> pq = new PriorityQueue<>();
        PriorityQueue<Integer> reversQ = new PriorityQueue<>(Collections.reverseOrder());
        for(String cmdLine  :operations){
            String[] cmd = cmdLine.split(" ");
            if("I".equals(cmd[0])){
                pq.add(Integer.valueOf(cmd[1]));
                reversQ.add(Integer.valueOf(cmd[1]));
            }else if("1".equals(cmd[1])){
                int max = reversQ.poll();
                pq.remove(max);
            }else{
                int min = pq.poll();
                reversQ.remove(min);
            }
        }

        if(pq.size() == 0){
            answer[0] = 0;
            answer[1] = 0;
        }else{
            answer[0] = reversQ.poll();
            answer[1] = pq.poll();
        }
        System.out.println("ANS:: " + Arrays.toString(answer));
        return answer;
    }

}