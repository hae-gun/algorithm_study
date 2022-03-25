package level2;

import common.Print;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Prog_게임맵최단거리 {
    public static void main(String[] args) {
        int[][] map = new int[][]{{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}};
        for (int[] line : map) {
            System.out.println(Arrays.toString(line));
        }

        Print.answer(Solution1844.solution(map), 11);
    }
}

class Solution1844 {
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    public static int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        Queue<RobotNode> q = new LinkedList<>();
        int[][] dist = new int[n][m];
        q.add(new RobotNode(0, 0));

        for(int[] line : dist){
            Arrays.fill(line, -1);
        }
        dist[0][0] = 1;
        while (!q.isEmpty()) {
            RobotNode cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                RobotNode next = new RobotNode(cur.x + dx[dir], cur.y + dy[dir]);
                if (next.x >= 0 && next.x < n && next.y >= 0 && next.y < m) {
                    if(maps[next.x][next.y]==1 && dist[next.x][next.y] == -1){
                        dist[next.x][next.y] = dist[cur.x][cur.y] + 1;
                        q.add(next);
                    }
                }
            }
        }
        return dist[n-1][m-1];
    }
}

class RobotNode {
    int x;
    int y;

    public RobotNode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "RobotNode{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}