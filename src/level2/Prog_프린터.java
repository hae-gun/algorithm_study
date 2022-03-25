package level2;

import common.Print;

import java.util.*;

public class Prog_프린터 {
    public static void main(String[] args) {
        Print.answer(Solution42587.solution(new int[]{2, 1, 3, 2},2),1);
        Print.answer(Solution42587.solution(new int[]{1, 1, 9, 1, 1, 1},0),5);
    }
}

class Solution42587 {
    public static int solution(int[] priorities, int location) {
        int[] sortArr = Arrays.copyOf(priorities,priorities.length);
        Arrays.sort(sortArr);
        Queue<Integer> prq = new LinkedList<>();
        Deque<Integer> deq = new LinkedList<>();
        Deque<Integer> idxq = new LinkedList<>();
        for(int i=sortArr.length-1; i>=0; i--){
            prq.add(sortArr[i]);
        }
        for (int i = 0; i < priorities.length; i++) {
            deq.add(priorities[i]);
            idxq.add(i);

        }

        int[] answer = new int[priorities.length];
        int i=0;
        while(!deq.isEmpty()){
            if(deq.peek() != prq.peek()){
                deq.add(deq.poll());
                idxq.add(idxq.poll());
            }else{
                answer[idxq.poll()] = ++i;
                deq.poll();
                prq.poll();
            }
        }
        return answer[location];
    }
}