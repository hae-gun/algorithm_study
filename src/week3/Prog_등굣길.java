package week3;

import common.Print;

import java.util.Arrays;

public class Prog_등굣길 {
    public static void main(String[] args) {
        Print.answer(Solution42898.solution(4, 3, new int[][]{{2, 2}}), 4);
    }
}

class Solution42898 {

    public static int solution(int m, int n, int[][] puddles) {
        int answer = 0;

        int[][] map = new int[n][m];

        for (int[] puddle : puddles) {
            map[puddle[1] - 1][puddle[0] - 1] = -1;
        }
        map[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == -1) continue;
                if (j > 0 && map[i][j - 1] != -1) map[i][j] += map[i][j - 1] % 1000000007; //하단 이동
                if (i > 0 && map[i - 1][j] != -1) map[i][j] += map[i - 1][j] % 1000000007;
            }
        }

        return map[n - 1][m - 1] % 1000000007;
    }
}
