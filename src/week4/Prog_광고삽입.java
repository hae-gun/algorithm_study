package week4;

import common.Print;

import java.sql.SQLOutput;
import java.util.*;

public class Prog_광고삽입 {
    public static void main(String[] args) {
        Solution72414 s = new Solution72414();
        Print.answer(s.solution("02:03:55", "00:14:15", new String[]{"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"}), "01:30:59");
        Print.answer(s.solution("99:59:59", "25:00:00", new String[]{"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"}), "01:00:00");
        Print.answer(s.solution("50:00:00", "50:00:00", new String[]{"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"}), "00:00:00");
    }
}

class Solution72414 {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        Time playTime = new Time(play_time);
        Time advTime = new Time(adv_time);

        List<Log> logList = new ArrayList<Log>();

        if (playTime.equals(advTime)) {
            return "00:00:00";
        }

        for (String log : logs) {
            Log l = new Log(log);
            logList.add(l);
        }
        Collections.sort(logList);
        int count = Integer.MIN_VALUE;
        Map<Integer, Log> map = new HashMap<>();
        for(int i=0; i<logList.size(); i++){
            int cnt = 1;
            AdvLogTime adv = new AdvLogTime(logList.get(i).startTm, advTime);
            //System.out.println(adv);
            for(int j=0; j<logList.size(); j++){
                if(j==i) continue;
                if(logList.get(j).isInTime(adv)){
                    cnt++;
                }
            }
            count = Math.max(count, cnt);
            map.putIfAbsent(count, adv);
        }
        //System.out.println(map);

        return map.get(count).startTm.toString();
    }

    class Time implements Comparable<Time> {
        int hour;
        int min;
        int sec;

        public Time(String strTime) {
            String[] t = strTime.split(":");
            this.hour = Integer.valueOf(t[0]);
            this.min = Integer.valueOf(t[1]);
            this.sec = Integer.valueOf(t[2]);
        }

        public Time(int hour, int min, int sec) {
            this.hour = hour;
            this.min = min;
            this.sec = sec;
        }

        @Override
        public String toString() {
            return padding(hour) + ":" + padding(min) + ":" + padding(sec);
        }

        private String padding(int t) {
            if (t < 10) return "0" + t;
            return "" + t;
        }

        @Override
        public int compareTo(Time o) {
            if (this.equals(o)) {
                return 0;
            }
            if (this.hour > o.hour) {
                return 1;
            } else if (this.hour == o.hour) {
                if (this.min > o.min) {
                    return 1;
                } else if (this.min == o.min) {
                    if (this.sec > o.sec) {
                        return 1;
                    }
                }
            }
            return -1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Time time = (Time) o;
            return hour == time.hour && min == time.min && sec == time.sec;
        }

        @Override
        public int hashCode() {
            return Objects.hash(hour, min, sec);
        }

        public Time add(Time advTime) {
            int h = this.hour + advTime.hour;
            int m = this.min + advTime.min;
            int s = this.sec + advTime.sec;

            if (s >= 60) {
                m++;
                s -= 60;
            }
            if (m >= 60) {
                h++;
                m -= 60;
            }
            return new Time(h, m, s);
        }
    }

    class Log implements Comparable<Log> {
        Time startTm;
        Time endTm;

        public Log(String logString) {
            String[] logs = logString.split("-");
            this.startTm = new Time(logs[0]);
            this.endTm = new Time(logs[1]);
        }

        public Log(Time startTm, Time endTm) {
            this.startTm = startTm;
            this.endTm = endTm;
        }

        @Override
        public int compareTo(Log o) {
            if (this.startTm.compareTo(o.startTm) == 0 && this.endTm.compareTo(o.endTm) == 0) {
                return 0;
            }
            if (this.startTm.compareTo(o.startTm) > 0) {
                return 1;
            } else if (this.startTm.compareTo(o.startTm) == 0) {
                if (this.endTm.compareTo(o.endTm) > 0) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }

        @Override
        public String toString() {
            return startTm +
                    " ~ " + endTm;
        }
        public boolean isInTime(AdvLogTime log){
            if(this.startTm.compareTo(log.startTm) <= 0 && this.endTm.compareTo(log.startTm) >= 0){
                return true;
            }
            if(this.startTm.compareTo(log.endTm) <= 0 && this.endTm.compareTo(log.endTm) >= 0){
                return true;
            }
            if(this.startTm.compareTo(log.startTm) <= 0 && this.endTm.compareTo(log.endTm) >= 0){
                return true;
            }
            return false;
        }

        public boolean isInTime(Time time) {
            if (this.startTm.compareTo(time) <= 0 && this.endTm.compareTo(time) >= 0) {
                return true;
            }
            return false;
        }
    }

    class AdvLogTime extends Log {

        public AdvLogTime(Time startTime, Time advTime) {
            super(startTime, startTime.add(advTime));
        }


    }

}
