package test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static void main(String[] args) {
        int[][] map = new int[8][8];
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};

        int x = 0,y = 0;
        map[x][y] = 1;

        map[4][3] = -1;
        map[4][4] = -1;
        map[4][6] = -1;
        map[5][3] = -1;
        map[6][3] = -1;

        map[6][5] = -1;
        map[7][5] = -1;


        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            x = cur[0];
            y = cur[1];
//            if(x==5 && y == 4) break;
            for(int dir=0; dir<4; dir++){
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if(nx >= 0 && nx < map.length && ny >= 0 && ny < map.length){
                    //if(map[nx][ny] !=0) continue;
                    if(map[nx][ny] == 0){
                        map[nx][ny] = map[x][y] + 1;
                        queue.add(new int[]{nx,ny});
                    }
                }
            }
        }
        for(int[] line : map){
            System.out.println(Arrays.toString(line));
        }
    }
}
