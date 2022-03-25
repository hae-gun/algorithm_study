package week2;

import common.Print;
import jdk.internal.util.xml.impl.Input;

import java.io.IOException;

/*
 * 풀이접근 -> ~를 만족하는 가장 작은 or ~인 가장 큰 키워드 일때 '이분탐색' 생각해보기.
 * 이분탐색 개념 ->
 * left=0, right=최대값
 * 평균값(mid) 구하기 (left + right) /2
 * 원하는 조건이 만족하면 right 를 mid-1 로 이동하여 다시 수행
 * 원하는 조건이 만족하지 않다면 left 를 mid+1 로 이동하여 다시 수행
 * left <= right 일때까지 반복.
 * left 가 right 를 교차하는 순간 -> 최적의 값.
 */


public class Prog_금과은운반하기 {
    public static void main(String[] args) throws IOException {
        Print.answer(Solution86053.solution(10, 10, new int[]{100}, new int[]{100}, new int[]{7}, new int[]{10}), 50l);
        Print.answer(Solution86053.solution(90, 500, new int[]{70, 70, 0}, new int[]{0, 0, 500}, new int[]{100, 100, 2}, new int[]{4, 8, 1}), 499l);
    }
}

class Solution86053 {
    public static long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        // 최대 걸리는 순회시간
        // g[], s[] 최대길이 10e9
        // w[] 최대길이 10e5 w는 편도 이므로 * 2
        final long max = (long) (2 * 10e9 * 2 * 10e5);
        long left = 0, right = max, T, answer = max;


        while (left <= right) {
            T = (left + right) / 2;
            long goldMax = 0, silverMax = 0, gsMax = 0;

            for (int i = 0; i < s.length; i++) { // 각 도시에서 T시간 동안 금, 은, 금은모두 옮겼을때 갯수를 모두 더해준다.
                // T/t[i] => i번째 도시에서 왕복횟수
                // (/2 한 이유 t[i] 는 편도시간 이기때문)
                long wt = (long) Math.ceil((double) (T / t[i]) / 2) * w[i]; // 총 운반한 무게.
                // Math.min 의미. 1. 시간이 충분이 주어졌을 때 (wt 가 클때) -> 그 도시의 광물 모두 운반가능
                //               2. 광물 모두 못 옮길때 -> 주어진 시간동안 해당 화물이 움직인 총 무게
                goldMax += Math.min(g[i], wt);
                silverMax += Math.min(s[i], wt);
                gsMax += Math.min(g[i] + s[i], wt);
            }
            //System.out.println("TIME " + T + " gm " + goldMax + " sm " + silverMax + " gsm " + gsMax);
            if (a <= goldMax && b <= silverMax && a + b <= gsMax) { // 원하는 양만큼 옮겼을 때. right 검색범위를 이동
                right = T - 1;
                answer = T;
            } else { // 원하는 양 만큼 못옮겼을 때 left 검색범위를 이동
                left = T + 1;
            }
        }
        return answer;
    }
}
