package week3;

import common.Print;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Prog_합승택시요금 {
    public static void main(String[] args) {
        //	4	6	2	new String[]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}}	82
        //7	3	4	1	{{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}}	14
        //6	4	5	6	{{2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}}	18
        Solution72413 s = new Solution72413();
        Print.answer(s.solution(6,4,6,2,new int[][] {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}}) ,82);
        Print.answer(s.solution(7,3,4,1,new int[][] {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}}) ,14);
        Print.answer(s.solution(6,4,5,6,new int[][] {{2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}}) ,18);
    }
}

class Solution72413 {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        ArrayList<Edge>[] edges =  new ArrayList[n+1];

        for(int i = 0; i <= n; i++) edges[i] = new ArrayList<Edge>();
        for(int i = 0; i < fares.length; i++) {
            int n1 = fares[i][0];
            int n2 = fares[i][1];
            int dist = fares[i][2];
            edges[n1].add(new Edge(n2, dist));
            edges[n2].add(new Edge(n1, dist));
        }
        for(int i=1; i<edges.length; i++){
            List<Edge> list  = edges[i];
            System.out.println(i+"라인: " +list);
        }
        // 배운점  -> 다익스트라 알고리즘 : 한 정점에서 다른 정점까지 가는데 드는 최소비용.
        // 경로 계산확인법 -> A, B 따로 가는 가격 , 같이갔다가 흩어지는 가격을 계산하는것이 x
        // i 노드들 중에서 S,A,B로 가는 최소 길이를 갖는 i 노드를 찾는것. (i 노드가 S 일때는 A,B 가 합승하지 않는 경우가 된다.)
        int[] distS = dijkstra(s,n,edges);
        int[] distA = dijkstra(a,n,edges);
        int[] distB = dijkstra(b,n,edges);
        int min  = Integer.MAX_VALUE;
        for(int i=1; i<=n; i++){
            min = Math.min(min, distS[i] + distA[i] + distB[i]);
        }

        return min;
    }

    public int[] dijkstra(int s, int n, ArrayList<Edge>[] edges){
        // 2. set Dists
        int[] dist = new int[n+1];
        for(int i = 0; i <= n; i++) dist[i] = Integer.MAX_VALUE;

        // 3. set PriorityQueue
        PriorityQueue<Info> pq = new PriorityQueue<Info>();

        // 4. offer startNode
        dist[s] = 0;
        pq.offer(new Info(s, dist[s]));

        while(!pq.isEmpty()){
            Info info = pq.poll();
            if(info.dist > dist[info.node]) continue;
            for(Edge adj : edges[info.node]){
                if(adj.dist + dist[info.node] > dist[adj.to]) continue;
                dist[adj.to] = adj.dist + dist[info.node];
                pq.offer(new Info(adj.to, dist[adj.to]));
            }
        }

        return dist;
    }

    class Edge {
        int to;
        int dist;
        public Edge(int to, int dist){
            this.to = to;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "to=" + to +
                    ", dist=" + dist +
                    '}';
        }
    }

    class Info implements Comparable<Info>{
        int node;
        int dist;
        public Info(int node, int dist){
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Info i){
            return this.dist - i.dist;
        }
    }
}