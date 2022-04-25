package other;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SpiralArr {
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int[][] map;// = new int[11][11];

    public static void main(String[] args) {

    }

    public int[][] makeSpiral(int n){
        map = new int[n][n];
        int dirIdx = 0;
        map[0][0] = 1;
        int x = 0;
        int y = 0;
        while (!isOver(x, y)) {
            int nx = x + dir[dirIdx][0];
            int ny = y + dir[dirIdx][1];
            if (nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) {
                dirIdx = (dirIdx + 1) % 4;
                continue;
            }
            if (map[nx][ny] != 0) {
                dirIdx = (dirIdx + 1) % 4;
                continue;
            }
            map[nx][ny] = map[x][y] + 1;
            x = nx;
            y = ny;
        }
        for (int[] line : map) {
            System.out.println(Arrays.stream(line)
                    .mapToObj(v -> getString(v)).collect(Collectors.toList()));
        }
        return map;
    }

    private static String getString(int v) {
        String value = "";
        if (v < 10) {
            value += "00" + v;
        } else if (v < 100) {
            value += "0" + v;
        } else {
            value += v;
        }
        return value;
    }

    private static boolean isOver(int x, int y) {
        for (int d = 0; d < 4; d++) {
            int nx = x + dir[d][0];
            int ny = y + dir[d][1];
            if (nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) {
                continue;
            }
            if (map[nx][ny] == 0) {
                return false;
            }
        }
        return true;
    }
}
