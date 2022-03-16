package week1;

import java.util.Objects;

public class Prog_17676 {
    // url : https://programmers.co.kr/learn/courses/30/lessons/17676
    public static void main(String[] args) {
        String[] times = new String[]{
                "2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"
        };
        System.out.println(Solution17676.solution(times) == 7);
    }
}
class Solution17676 {
    public static int solution(String[] lines) {
        int answer = 0;
        long offset = 0;
        long endTime = 0;
        TimeData[] datas = new TimeData[lines.length];
        for (int i = 0; i < lines.length; i++) {
            TimeData data = new TimeData(lines[i]);
            if (i == 0) offset = data.stMillScnd;
            data.addOffset(offset);
            datas[i] = data;
        }
        int maxCount = -987654321;
        for (TimeData data : datas) {
            maxCount = Math.max(maxCount, countLines(datas, data));
        }

        //System.out.println(maxCount);
        return maxCount;
    }

    public static int countLines(TimeData[] datas, TimeData curTime) {
        System.out.println("cur:" + curTime);
        int count = 0;
        long endTime = curTime.endMillScnd;
        for (TimeData data : datas) {
            long t_st = data.stMillScnd;
            long t_et = data.endMillScnd;
            if (endTime <= t_st && t_st < endTime + 1000) {
                count++;
            } else if (endTime <= t_et && t_et < endTime + 1000) {
                count++;
            } else if (t_st <= endTime && endTime + 1000 <= t_et) {
                count++;
            }
        }
        return count;
    }
}

class TimeData {
    long stMillScnd;
    long endMillScnd;

    public TimeData(String logData) {
        String[] logs = logData.split(" ");
        String time = logs[1];
        String term = logs[2];
        this.endMillScnd = makeStrToMille(time);
        this.stMillScnd = minusTerm(term);
    }

    public long makeStrToMille(String time) {
        String[] times = time.split(":");
        long hour = Long.valueOf(times[0]) * 60 * 60 * 1000;
        long min = Long.valueOf(times[1]) * 60 * 1000;
        long second = (long) (Double.valueOf(times[2]) * 1000);
        return (hour + min + second);
    }

    public long minusTerm(String term) {
        return this.endMillScnd - (long) (Double.valueOf(term.substring(0, term.indexOf("s"))) * 1000) + 1;
    }

    public void addOffset(long offSet) {
        this.stMillScnd -= offSet;
        this.endMillScnd -= offSet;
    }

    @Override
    public String toString() {
        return this.stMillScnd + " ~ " + this.endMillScnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeData timeData = (TimeData) o;
        return stMillScnd == timeData.stMillScnd && endMillScnd == timeData.endMillScnd;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stMillScnd, endMillScnd);
    }
}