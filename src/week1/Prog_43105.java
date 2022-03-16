package week1;

import java.sql.SQLOutput;
import java.util.Arrays;

public class Prog_43105 {
    //https://programmers.co.kr/learn/courses/30/lessons/43105
    public static void main(String[] args) {
        System.out.println(Solution43105.solution(new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}}) == 30);
    }
}

class Solution43105{
    // # dp 문제.
    // # 맨 아래서 부터 위로 더해가면서 큰숫자로 비교.
    public static int solution(int[][] triangle) {
        int n = triangle.length;
        int[][] dp = new int[n][n];
        // 가장 아랫 줄 최대합들 = 삼각형 맨 아래줄 값들.
        for(int i=0; i<triangle.length; i++){
            dp[n-1][i] = triangle[n-1][i];
        }
        // 한칸씩 위로 올라오면서 크기 비교.
        for(int i=n-2; i>=0; i--){
            int[] prevLine = dp[i+1];
            int[] line = dp[i];
            for(int j=0; j<triangle[i].length ; j++) {
                line[j] = Math.max(triangle[i][j] + prevLine[j], triangle[i][j] + prevLine[j + 1]);
            }
        }
        return dp[0][0];
    }
}