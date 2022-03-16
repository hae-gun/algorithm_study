package week1;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Prog_42627 {
    // url : https://programmers.co.kr/learn/courses/30/lessons/42627
    public static void main(String[] args) {
        System.out.println(Solution42627.solution(new int[][]{{0, 3}, {1, 9}, {2, 6}}) == 9);
    }
}
class Solution42627{
    public static int solution(int[][] jobs) {
        int count = 0;
        int answer = 0;
        Arrays.sort(jobs, (o1, o2) -> o1[0]-o2[0]);

        PriorityQueue<int[]> pqueue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int curIdx = 0;
        int endTime = 0;
        while(count < jobs.length){

            while(curIdx < jobs.length &&  jobs[curIdx][0] <= endTime){
                pqueue.add(jobs[curIdx++]);
            }
            if (pqueue.isEmpty()) {
                endTime = jobs[curIdx][0];
            }else{
                int[] temp = pqueue.poll();
                answer += temp[1] + endTime - temp[0];
                endTime += temp[1];
                count++;
            }
        }
        return (int) Math.floor(answer / jobs.length);
    }
}
