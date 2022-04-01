package week3;

import common.Print;

import java.util.Arrays;

public class Prog_아이템줍기 {
    public static void main(String[] args) {
        //[1,1,7,4],[3,2,5,5],[4,3,6,9],[2,6,8,8]]	1	3	7	8	17
        //[{1,1,8,4},{2,2,4,9},{3,6,9,8},{6,3,7,7}]	9	7	6	1	11
        //[[1,1,5,7]]	1	1	4	7	9
        //[[2,1,7,5],[6,4,10,10]]	3	1	7	10	15
        //[[2,2,5,5],[1,3,6,4],[3,1,4,6]]	1	4	6	3	10
        Print.answer(Solution87694.solution(new int[][]{{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}}, 1,3,7,8),17);
        Print.answer(Solution87694.solution(new int[][]{{1,1,8,4},{2,2,4,9},{3,6,9,8},{6,3,7,7}}, 9,7,6,1),11);
    }
}
// 1: 직선 2: 코너 3: 지나간 위치 4: 목표
class Solution87694{
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        int [][] map = new int[102][102];

        for(int[] rec  :rectangle){
            drawLine(map, rec);
        }

        map[characterY*2][characterX*2] = 3;
        map[itemY*2][itemX*2] = 4;

        for(int dir1=0; dir1 < 4; dir1++){
            int cx = characterX * 2;
            int cy = characterY * 2;
            int distance = 0;
            cx += dx[dir1];
            cy += dy[dir1];
            int tempx;
            int tempy;
            if(cx >= 0 && cx < 102 && cy >= 0 && cy < 102){
                if(map[cy][cx] != 1 && map[cy][cx] != 2) continue;
                // 현 위치에서 4방향 탐색해서 진행
                while(map[cy][cx] != 4){
                    System.out.println(cx + "," + cy);
                    map[cy][cx] = 3;
                    for(int i=map.length-1; i>=0; i--){
                        int[] line = map[i];
                        for(int j=0; j<line.length; j++){
                            if(line[j]==0){
                                System.out.print(" ");
                            }else if(line[j]<0){
                                System.out.print("I");
                            }else{
                                System.out.print(line[j]);
                            }
                        }
                        System.out.println();
                    }
                    tempx = cx;
                    tempy = cy;
                    for(int dir=0; dir<4; dir++){
                        int nx = cx + dx[dir];
                        int ny = cy + dy[dir];
                        if(nx >= 0 && nx < 102 && ny >= 0 && ny < 102){
                            if(map[ny][nx] != 1 && map[ny][nx] != 2) continue;
                            distance++;
                            cx = nx;
                            cy = ny;
                        }
                    }
                if(tempx == cx && tempy == cy) break;
                }
            }
            answer = answer == 0 ? distance : Math.min(answer, distance);
        }


        for(int i=map.length-1; i>=0; i--){
            int[] line = map[i];
            for(int j=0; j<line.length; j++){
                if(line[j]==0){
                    System.out.print(" ");
                }else if(line[j]<0){
                    System.out.print("I");
                }else{
                    System.out.print(line[j]);
                }
            }
            System.out.println();
        }

        return answer;
    }

    public static void drawLine(int[][] map, int[] rectangle){
        int sx = rectangle[0]*2;
        int sy = rectangle[1]*2;
        int ex = rectangle[2]*2;
        int ey = rectangle[3]*2;

        for(int i=sx; i<=ex; i++){
            map[ey][i] += 1;
            map[sy][i] += 1;
        }

        for(int i=sy; i<=ey; i++){
            map[i][sx] += 1;
            map[i][ex] += 1;
        }

        // 안쪽 칠하기.
        for(int i=sx+1; i<=ex-1; i++){
            for(int j=sy+1; j <= ey-1; j++){
                map[j][i] = -99;
            }
        }
    }
}
