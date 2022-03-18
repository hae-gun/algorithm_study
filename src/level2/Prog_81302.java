package level2;
import common.Print;

import java.util.Arrays;

public class Prog_81302 {
    public static void main(String[] args) {
        String[][] map = new String[][]{
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        Print.answer(Solution81302.solution(map), new int[]{1, 0, 1, 1, 1});
    }
}

class Solution81302{
    public static int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for(int i=0; i<places.length; i++){
            //System.out.println(i +"번쨰 방");
            answer[i] = checkMap(places[i]);
        }
        return answer;
    }
    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};

    static int[] xdx = {1,-1,1,-1};
    static int[] xdy = {-1,1,1,-1};
    private static int checkMap(String[] place) {

        String[][] map = makeMap(place);

        for(int i=0; i<map.length; i++){
            for (int j = 0; j < map[i].length; j++) {
                if("P".equals(map[i][j])){
                    //System.out.println(i +", " +j + " is P");
                    for(int dir=0; dir<4; dir++){ // 상하좌우에 P 있나 확인. (있으면 안됨)
                        int nx = i + dx[dir];
                        int ny = j + dy[dir];
                        if(nx >= 0 && nx < map[i].length && ny >= 0 && ny < map.length){
                            if("P".equals(map[nx][ny])) return 0;
                        }
                    }
                    for(int dir=0; dir<4; dir++){ // 대각선 P 있나 확인.
                        int nx = i + xdx[dir];
                        int ny = j + xdy[dir];
                        //System.out.println("nx " + nx + ", ny " + ny);
                        if(nx >= 0 && nx < map[i].length && ny >= 0 && ny < map.length){
                            if("P".equals(map[nx][ny])) { // 대각선 P 있을때 두개 사이 X로 막혀있나 확인.
                                if(!"X".equals(map[nx][j]) || !"X".equals(map[i][ny])){
                                    return 0;
                                }
                            }
                        }
                    }
                }
            }
        }
        //System.out.println("===============");
        return 1;
    }

    private static String[][] makeMap(String[] place) {
        String[][] map = new String[place.length][place[0].length()];
        for(int i=0; i< place.length; i++){
            String[] lines = place[i].split("");
            for(int j=0; j<lines.length; j++){
                map[i][j] = lines[j];
            }
        }

        return map;
    }
}