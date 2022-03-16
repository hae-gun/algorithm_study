package week1;

import java.util.Arrays;

public class Prog_43238 {
    public static void main(String[] args) {
        // 6	[7, 10]	28
        System.out.println(Solution43238.solution(6, new int[]{7,10}) == 28);
    }
}

class Solution43238{
    public static long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        long left = 0;
        // right -> 가장 오래걸렸을 때 시간. (모두 시간 긴 창구 사용했을 떄 걸리는 시간.)
        long right = (long) n * times[times.length - 1];
        // 이분탐색 사용.
        while(left <= right){
            long mid = (left + right) / 2;
            long sum = 0;
            for (int i=0; i<times.length; i++){
                sum += mid/times[i];
            }
            if(sum < n){
                left = mid+1;
            }else{
                right = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }
}