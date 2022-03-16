package week1;

import java.util.Objects;
/*
        * 문제 유형: 단순 구현, 문자열 다루기
        * 체감 난이도: ** (별 2 개)
        * 풀이 1:
        * 문자열을 중 시간 부분을 잘라내어 밀리초로 변환
        * 매초 비교하려면 시간이 너무 오래걸림.
        * 시간순으로 데이터가 끝난 순간 부터 1초내에 처리량을 비교 (무조건 1개는 포함되므로 항상 처리량이 존재함)
        * 한 데이터가 끝났을 때 다른 데이터가 이 데이터 처리와 겹치는 경우는 아래같이 3가지 경우가 존재함.
        * 1. 처리중인 데이터의 종료시간이 끝난 데이터 시간 ~ +1 초 사이에 존재할때.
        * 2. 처리중인 데이터의 시작시간이 끝난 시간보다 작고, 종료시간이 끝난 시간+1 초 사이에 존재할때.
        * 3. 처리중인 데이터의 시작시간이 끝난 시간보다 작고, 종료시간이 끝난 시간+1 초 보다 클때.
        *
        * 주의할 점:
        * 문제를 잘 읽을것.. 주어진 시간은 끝나는 시간.
        * 시작시간은 주어진 시간 - 처리량을 해줘야 함 (처음에 반대로 해서 해결을 못함)
        *
        * 문법 팁:
        * 자바 객체 생성자를 통해 가독성 좋게 만들수 있다.
        * 기타
        ex) ~ 내장함수를 썼을 때 시간이 더 적게 걸렸다. 등등
        *
 */
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
            if (i == 0) offset = data.stMillSec;
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

    public static int countLines(TimeData[] dataList, TimeData curTime) {
        int count = 0;
        long endTime = curTime.endMillSec;
        for (TimeData data : dataList) {
            long t_st = data.stMillSec;
            long t_et = data.endMillSec;
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
    long stMillSec;
    long endMillSec;

    public TimeData(String logData) {
        String[] logs = logData.split(" ");
        String time = logs[1];
        String term = logs[2];
        this.endMillSec = makeStrToMille(time);
        this.stMillSec = minusTerm(term);
    }

    public long makeStrToMille(String time) {
        String[] times = time.split(":");
        long hour = Long.valueOf(times[0]) * 60 * 60 * 1000;
        long min = Long.valueOf(times[1]) * 60 * 1000;
        long second = (long) (Double.valueOf(times[2]) * 1000);
        return (hour + min + second);
    }

    public long minusTerm(String term) {
        return this.endMillSec - (long) (Double.valueOf(term.substring(0, term.indexOf("s"))) * 1000) + 1;
    }

    public void addOffset(long offSet) {
        this.stMillSec -= offSet;
        this.endMillSec -= offSet;
    }

    @Override
    public String toString() {
        return this.stMillSec + " ~ " + this.endMillSec;
    }
}