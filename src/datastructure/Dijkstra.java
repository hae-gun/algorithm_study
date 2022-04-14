package datastructure;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 다익스트라 알고리즘
// 그리디 + DP 를 이용함.
// (1) 방문하지 않은 노드 중에서 가장 비용이 적은 노드를 선택한다 (Greedy).
// (2) 해당 노드로부터 갈 수 있는 노드들의 비용을 갱신한다 (DP).
public class Dijkstra {
    // 그래프 가중치
    public static void main(String[] args) {
        int[][] graph = new int[6][6];
        for(int[] g : graph){
            Arrays.fill(g, -1);
        }
        // https://sskl660.tistory.com/59 그래프 인용 (양방향 그래프)
        // 노드 - 연결된 노드(가중치)
        // 0 - {1,2}, {2,5}, {3,3}
        // 1 - {0,2}, {2,7}, {5,10}
        // 2 - {1,7}, {0,5}, {3,1}, {4,2}, {5,5}
        // 3 - {0,3}, {2,1}, {4,7}

        // 4 - {2,2}, {3,7}, {5,2}
        // 5 - {1,10},{2,5}, {4,2}
        int[][][] point = new int[][][]{
                {{1,2}, {2,5}, {3,3}}
                ,{{0,2}, {2,7}, {5,10}}
                ,{{1,7}, {0,5}, {3,1}, {4,2}, {5,5}}
                ,{{0,3}, {2,1}, {4,7}}
                ,{{2,2}, {3,7}, {5,2}}
                ,{{1,10},{2,5}, {4,2}}
        };
        for(int i=0; i<point.length; i++){
            for(int j=0; j<point[i].length; j++){
                graph[i][point[i][j][0]] = point[i][j][1];
            }
        }

        for(int[] line : graph){
            System.out.println(Arrays.toString(line));
        }
        int distance = dijstra(0, 5, graph);


    }

    private static int dijstra(int start, int target, int[][] graph) {
        int[] temp = graph[start];
        if(graph[start][target] == -1){
            for(int i=0; i<temp.length; i++){
                Queue<int[]> q = new LinkedList<>();
                if(temp[i]==-1) continue;
                q.add(new int[]{start, i, temp[i]});
                while(!q.isEmpty()){
                    

                }
            }
        }

        return graph[start][target];
    }
}
