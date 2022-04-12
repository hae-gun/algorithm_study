package week3;

import common.Print;

import java.util.LinkedList;
import java.util.Queue;

public class Prog_경주로건설 {
    public static void main(String[] args) {
        //{{0,0,0},{0,0,0},{0,0,0}}	900
        //{{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}}	3800
        //{{0,0,1,0},{0,0,0,0},{0,1,0,1},{1,0,0,0}}	2100
        //{{0,0,0,0,0,0},{0,1,1,1,1,0},{0,0,1,0,0,0},{1,0,0,1,0,1},{0,1,0,0,0,1},{0,0,0,0,0,0}}	3200
        Solution67259 s = new Solution67259();
        Print.answer(s.solution(new int[][]{{0,0,0},{0,0,0},{0,0,0}}),900);
        Print.answer(s.solution(new int[][]{{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}}),3800);
    }
}
class Solution67259 {

    int min = Integer.MAX_VALUE;
    int[] dx = {0, -1, 0, 1};
    int[] dy = {1, 0, -1, 0};
    int n;
    boolean[][] visited;

    public int solution(int[][] board) {
        n = board.length;
        visited = new boolean[n][n];
        bfs(board);
        return min;
    }

    public void bfs(int[][] board) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0, -1));
        visited[0][0] = true;

        while(!q.isEmpty()) {
            Node node = q.poll();

            if(node.x == n - 1 && node.y == n - 1) {
                min = Math.min(min, node.cost);
            }

            for(int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx >= 0 && ny >= 0 && nx < n && ny < n && board[nx][ny] != 1) {
                    int newCost;
                    if(node.dir == -1 || node.dir == i) { //직진
                        newCost = node.cost + 100;
                    } else { //코너
                        newCost = node.cost + 600;
                    }

                    //처음 방문하거나 이전에 방문했을 때의 cost보다 작거나 같은 비용이면
                    if(visited[nx][ny] == false || board[nx][ny] >= newCost) {
                        visited[nx][ny] = true;
                        board[nx][ny] = newCost; //값을 갱신해주고
                        q.add(new Node(nx, ny, newCost, i)); //해당 지점으로 이동한다.
                    }
                }
            }
        }
    }

    class Node{
        int x;
        int y;
        int cost;
        int dir;

        public Node(int x, int y, int cost, int dir) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.dir = dir;
        }
    }
}
