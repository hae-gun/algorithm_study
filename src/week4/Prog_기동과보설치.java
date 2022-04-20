package week4;

import common.Print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Prog_기동과보설치 {
    public static void main(String[] args) {
        Solution60061 s = new Solution60061();
        Print.answer(s.solution(5, new int[][]{{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}})
                , new int[][]{{1, 0, 0}, {1, 1, 1}, {2, 1, 0}, {2, 2, 1}, {3, 2, 1}, {4, 2, 1}, {5, 0, 0}, {5, 1, 0}});
        Print.answer(s.solution(5, new int[][]{{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1}, {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}})
                , new int[][]{{0, 0, 0}, {0, 1, 1}, {1, 1, 1}, {2, 1, 1}, {3, 1, 1}, {4, 0, 0}});
    }
}

class Solution60061 {
    static boolean[][] poll;
    static boolean[][] bow;

    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        poll = new boolean[n + 1][n + 1];
        bow = new boolean[n + 1][n + 1];
        //x, y는 기둥, 보를 설치 또는 삭제할 교차점의 좌표이며, [가로 좌표, 세로 좌표] 형태입니다.
        //a는 설치 또는 삭제할 구조물의 종류를 나타내며, 0은 기둥, 1은 보를 나타냅니다.
        //b는 구조물을 설치할 지, 혹은 삭제할 지를 나타내며 0은 삭제, 1은 설치를 나타냅니다.

        for (int[] build : build_frame) {
            int x = build[0];
            int y = build[1];
            int type = build[2];
            int cmd = build[3];

            if (type == 0) { // 기둥일때
                if (cmd == 0) {// 삭제
                    if (removeValidation(n, x, y, type)) {
                        poll[y][x] = false;
                    }
                } else { // 설치
                    if (validation(n, x, y, type)) {
                        poll[y][x] = true;
                    }
                }
            } else { // 보일때
                if (cmd == 0) {// 삭제
                    if (removeValidation(n, x, y, type)) {
                        bow[y][x] = false;
                    }
                } else { // 설치
                    if (validation(n, x, y, type)) {
                        bow[y][x] = true;
                    }
                }
            }
        }

        for (int i = poll.length - 1; i >= 0; i--) {
            boolean[] line = poll[i];
            System.out.println(Arrays.toString(line));
        }


        System.out.println("++");
        for (int i = bow.length - 1; i >= 0; i--) {
            boolean[] line = bow[i];
            System.out.println(Arrays.toString(line));
        }
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < poll.length; i++) {
            for (int j = 0; j < poll[i].length; j++) {
                if (poll[i][j]) {
                    list.add(new int[]{j, i, 0});
                }
            }
        }
        for (int i = 0; i < bow.length; i++) {
            for (int j = 0; j < bow[i].length; j++) {
                if (bow[i][j]) {
                    list.add(new int[]{j, i, 1});
                }
            }
        }
        Collections.sort(list, (o1, o2) -> o1[0] - o2[0] > 0 ? o1[0] - o2[0] : o1[1] - o2[1] > 0 ? o1[1] - o2[1] : o1[2] - o1[2]);
        for (int[] item : list) {
            System.out.print(Arrays.toString(item) + ", ");
        }
        return answer;
    }

    private boolean validation(int n, int x, int y, int type) {
        boolean result = false;
        if (type == 0) {
            if (y == 0) {
                return true;
            } else {
                if (poll[y - 1][x]) return true;
            }
            if (x == 0) {
                if (bow[y][x]) {
                    result = true;
                }
            } else if (x == n) {
                if (bow[y][x - 1]) {
                    result = true;
                }
            } else if (x > 0 && x < n) {
                if ((bow[y][x - 1] && !bow[y][x]) || (!bow[y][x - 1] && bow[y][x])) {
                    result = true;
                }
            }
        } else {
            if (x == 0) {
                if (poll[y - 1][x]) {
                    result = true;
                }
            } else if (x == n - 1) {
                if (poll[y - 1][x + 1]) {
                    result = true;
                }
            } else if (x > 0 && x < n) {
                if (poll[y - 1][x + 1] || poll[y - 1][x]) {
                    result = true;
                } else if (bow[y][x - 1] && bow[y][x + 1]) {
                    result = true;
                }
            }
        }
        return result;
    }


    private boolean removeValidation(int n, int x, int y, int type) {
        boolean result = false;
        if (type == 0) { //기둥
            if (x - 1 >= 0 && x <= n) {
                if (bow[y + 1][x - 1] && bow[y + 1][x - 1]) {
                    poll[y][x] = true;
                }
            }
        } else { // 보
            if (x < n - 1 && x > 0) {
                if (bow[y][x - 1] && bow[y][x + 1]) {
                    if (poll[y - 1][x] && poll[y - 1][x + 1]) {
                        result = true;
                    }
                } else if (bow[y][x - 1]) {
                    if (poll[y - 1][x]) {
                        result = true;
                    }
                } else if (bow[y][x + 1]) {
                    if (poll[y - 1][x + 1]) {
                        result = true;
                    }
                }
            } else if (x == n - 1) {
                if (poll[y][x]) {
                    if (poll[y][x + 1]) poll[y][x + 1] = false;
                    result = true;
                }
            } else if (x == 0) {
                if (poll[y][x + 1]) {
                    if (poll[y][x]) poll[y][x] = false;
                    result = true;
                }
            }
        }
        return result;
    }

}