package week1;

import common.Print;

import java.math.BigDecimal;
import java.util.Arrays;

// 문제풀이 핵심
// 1. 단방향 그래프 사용.
// 2. 자신이 이긴 번호 갯수 + 자신이 진 번호 갯수 = N-1 이여야 순위를 확실하게 알수 있다.
// graph[i][j] => i > j i가 j를 이긴경우 (i < j 는 i가 j한테 지는 경우)
// 그래프 경로 탐색은 플로이드-와샬 알로리즘으로 모든 경로를 확인.
public class Prog_49191 {
    //https://programmers.co.kr/learn/courses/30/lessons/49191
    public static void main(String[] args) {
        Print.answer(Solution49191.solution(5, new int[][]{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}}) == 2);
    }
}

class Solution49191{
    public static int solution(int n, int[][] results) {
        int answer = 0;

        int[][] graph = new int[n][n];

        for(int[] match : results){
            graph[match[0]-1][match[1]-1] = 1;
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i==j) {
                    graph[i][j] = 1000;
                    continue;
                }
                if(graph[i][j] == 0){
                    graph[i][j] = 1000;
                }
            }
        }

        floyd(graph);

        int[] countAll = new int[n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(graph[i][j] != 1000)
                    countAll[i] +=1;
            }
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(graph[j][i] != 1000)
                    countAll[i] +=1;
            }
        }
        for(int line : countAll){
            if(n-1 == line) answer++;
        }
        System.out.println("");
        return answer;
    }

    public static void floyd(int[][] graph) {
        int[][] FW = graph;
        int n = graph.length;
        for(int k =0; k< n; k++) {        //거쳐가는 정점
            for(int i = 0; i<n; i++) {    //시작 정점
                for(int j = 0; j<n; j++) {    //도착 정점
                    if(FW[i][j] > FW[i][k]+ FW[k][j]) {
                        FW[i][j] = FW[i][k] + FW[k][j];
                    }
                }
            }
        }
    }
}
