package week2;
import common.Print;

import java.util.*;
// 간단한 dp 문제

public class Prog_2xN타일링 {
    public static void main(String[] args) {
        Print.answer(Solution12900.solution(4), 5);
    }
}

class Solution12900 {
    public static int solution(int n) {
        int answer = 0;
        int[] dp = new int[n+1]; // dp[n] n길이 만드는데 필요한 경우의 수
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3;i<=n; i++){
            dp[i] = (dp[i-1] + dp[i-2]) % 1000000007;
        }
        //System.out.println(Arrays.toString(dp));
        return dp[n];
    }
}