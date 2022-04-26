package kakao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Kakao_동굴탐험 {
    // https://programmers.co.kr/learn/courses/30/lessons/67260
    public static boolean solution(int n, int[][] path, int[][] order) {
        List<Integer>[] graph = new ArrayList[n];

        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for(int i = 0; i < path.length; i++) {
            graph[path[i][0]].add(path[i][1]);
            graph[path[i][1]].add(path[i][0]);
        }

        return topological_sort(bfs(graph), order);
    }

    public static List<Integer>[] bfs(List<Integer>[] graph) {
        Queue<Integer> q = new LinkedList<>();
        List<Integer>[] directedGraph = new ArrayList[graph.length];
        for(int i = 0; i < directedGraph.length; i++) directedGraph[i] = new ArrayList<>();
        boolean[] v = new boolean[directedGraph.length];

        q.offer(0);
        v[0] = true;

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int i = 0; i < graph[cur].size(); i++) {
                int next = graph[cur].get(i);
                if(v[next]) continue;

                directedGraph[cur].add(next);
                v[next] = true;
                q.offer(next);
            }
        }

        return directedGraph;
    }

    // 위상정렬 이용하기.
    // 전체 Degree 설정 후 order 에 있는 순서쌍을 그래프에 추가.
    // order 순서쌍 그래프 추가시 해당 child 의 degree 증가.
    public static boolean topological_sort(List<Integer>[] graph, int[][] order) {
        Queue<Integer> q = new LinkedList<>();
        int[] inDegree = new int [graph.length];

        for(int i = 0; i < graph.length; i++) {
            for(Integer next : graph[i]) {
                inDegree[next]++;
            }
        }

        for (int i = 0; i < order.length; i++) {
            graph[order[i][0]].add(order[i][1]);
            inDegree[order[i][1]]++;
        }

        for(int i = 0; i < graph.length; i++) {
            if(inDegree[i] == 0) q.offer(i);
        }

        int cnt = 0;
        while(!q.isEmpty()) {
            int cur = q.poll();
            cnt++;
            for(int i = 0; i < graph[cur].size(); i++) {
                int next = graph[cur].get(i);

                if(--inDegree[next] == 0) {
                    q.offer(next);
                }

            }
        }
        // 전체 노드가 모두 큐에 들어왔다면 cnt 의 갯수와 노드의 갯수가 같아짐.
        // 두 수가 다르다는 것 => 전체를 조회하지 못했다는것.
        return cnt == graph.length ? true : false;
    }
}
