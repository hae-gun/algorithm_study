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

class Solution87694{
    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        int [][] map = new int[51][51];

        for(int[] rec  :rectangle){
            drawLine(map, rec);
        }

        for(int i=map.length-1; i>=0; i--){
            int[] line = map[i];
            for(int j=0; j<line.length; j++){
                if(line[j]==0){
                    System.out.print(" ");
                }else{
                    System.out.print(line[j]);
                }
            }
            System.out.println();
        }

        return answer;
    }

    public static void drawLine(int[][] map, int[] rectangle){
        int sx = rectangle[0];
        int sy = rectangle[1];
        int ex = rectangle[2];
        int ey = rectangle[3];

        for(int i=sx; i<=ex; i++){
            map[ey][i] += 1;
            map[sy][i] += 1;
        }

        for(int i=sy; i<=ey; i++){
            map[i][sx] += 1;
            map[i][ex] += 1;
        }
    }
}
