package week2;

import common.Print;

import java.util.*;

public class Prog_셔틀버스 {
    public static void main(String[] args) {
        Print.answer("09:00".equals(Solution17678.solution(1, 1, 5, new String[]{"08:00", "08:01", "08:02", "08:03"})));
        Print.answer("09:09".equals(Solution17678.solution(2, 10, 2, new String[]{"09:10", "09:09", "08:00"})));
        Print.answer("08:59".equals(Solution17678.solution(2, 1, 2, new String[]{"09:00", "09:00", "09:00", "09:00"})));
        Print.answer("00:00".equals(Solution17678.solution(1, 1, 5,new String[]{"00:01", "00:01", "00:01", "00:01", "00:01"})));
        Print.answer("09:00".equals(Solution17678.solution(1, 1, 1, new String[]{"23:59"})));
        Print.answer("18:00".equals(Solution17678.solution(10, 60, 45, new String[]{"23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"})));
    }
}

class Solution17678 {
    public static String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        Arrays.sort(timetable);
        PriorityQueue<Integer> que = new PriorityQueue<>();
        for (String time : timetable) {
            String[] tarr = time.split(":");
            que.add(Integer.valueOf(tarr[0]) * 60 + Integer.valueOf(tarr[1]));
            //System.out.println(que);
        }
        int time = 540;
        for (int i = 0; i < n; i++) {
            Queue<Integer> thisTime = new LinkedList<>();
            while (!que.isEmpty()) {
                if (que.peek() <= time) { // 출발 시간 이전에 온사람 모두 탑승.
                    thisTime.add(que.poll());
                    if (thisTime.size() >= m) { // 탑승 사이즈가 넘으면 출발
                        break;
                    }
                    continue;
                }
                if (que.peek() > time) { // 모두 탑승하지 않았지만 가장 빨리온 사람이 출발시간보다 뒤에 온경우 다음 시간출발
                    break;
                }
            }
            if (i == n - 1) {//마지막 차에서
                if (thisTime.size() < m) return timeFormat(time); // 인원수 다 안찬경우 -> 막차 시간에 오면됨
                else { // 인원수 다 찬경우 -> 가장 마지막에 온 사람보다 1분 일찍 오면 됨.
                    List<Integer> thisTime2 = new ArrayList<>(thisTime);
                    int last = thisTime2.get(thisTime2.size() - 1);
                    return timeFormat(last - 1);
                }
            }
            time += t;
        }


        return answer;
    }

    public static String timeFormat(int time) {
        String hour = time / 60 < 10 ? "0" + time / 60 : "" + time / 60;
        String min = time % 60 < 10 ? "0" + time % 60 : "" + time % 60;
        return hour + ":" + min;
    }
}