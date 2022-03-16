package week1;

public class Prog_43162 {
    // https://programmers.co.kr/learn/courses/30/lessons/43162
    public static void main(String[] args) {
        System.out.println(Solution43162.solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}) == 2);
        System.out.println(Solution43162.solution(3, new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}) == 1);
    }
}

class Solution43162{
    static boolean[] visited;
    static int answer = 0;
    static int[][] graph;
    public static int solution(int n, int[][] computers) {
        visited =  new boolean[n];
        graph = new int[n][n];
        answer = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i==j)  continue;
                if(computers[i][j] == 1){
                    graph[i][j] = 1;
                }
            }
        }
        for(int i=0; i<visited.length; i++){
            if (!visited[i]) {
                dfs(i);
                answer++;
            }
        }
        return answer;
    }

    public static void dfs(int idx){
        visited[idx] = true;
        for(int i=0; i<visited.length; i++){
            if(!visited[i] && graph[idx][i] == 1){
                dfs(i);
            }
        }
    }
}
