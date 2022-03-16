package week1;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Prog_49189 {
    // url : https://programmers.co.kr/learn/courses/30/lessons/49189
    public static void main(String[] args) {
        System.out.println(Solution49189.solution(6, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}) == 3);
    }
}

class Solution49189{
    private static List<List<Integer>> line = new ArrayList<>();
    static boolean[] visited;
    static int[] distance;
    public static int solution(int n, int[][] edge) {
        visited = new boolean[n + 1];
        distance = new int[n + 1];
        int answer = 0;
        for(int i=0; i<=n; i++){
            line.add(new ArrayList<Integer>());
            distance[i] = Integer.MAX_VALUE;
        }
        for(int i=0; i< edge.length; i++){
            line.get(edge[i][0]).add(edge[i][1]);
            line.get(edge[i][1]).add(edge[i][0]);
        }
        answer = bfs();



        return answer;
    }
    public static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;

        int cnt = 0;
        while (true) {
            Queue<Integer> temp = new LinkedList<>();

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int adj : line.get(cur)) {
                    if (!visited[adj]) {
                        temp.add(adj);
                        visited[adj] = true;
                    }
                }
            }

            if (temp.isEmpty()) break;
            queue.addAll(temp);
            System.out.println(temp);
            cnt = temp.size();
        }

        return cnt;
    }
}
