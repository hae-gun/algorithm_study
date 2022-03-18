package level2;

import common.Print;

import java.util.Arrays;

public class Prog_92342 {
    public static void main(String[] args) {
        Print.answer(Solution92342.solution(5, new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0}), new int[]{0, 2, 2, 0, 1, 0, 0, 0, 0, 0, 0});
    }
}

class Solution92342 {
    static int max = 0;
    static int[] answer;
    public static int[] solution(int n, int[] info) {
        answer = new int[info.length];
        boolean[] getThisPoint = new boolean[info.length];

        dfs(n, info, getThisPoint, new int[info.length]);
        System.out.println(max);

        if(allZero(answer)){
            return new int[]{-1};
        }

        return answer;
    }

    private static void dfs(int n, int[] info, boolean[] getThisPoint, int[] myPoint) {
        if(n == 0){
            //System.out.println(calcPoint(getThisPoint));
            if(calcPoint(getThisPoint) > getPoint(info, getThisPoint)){
                max = Math.max(max, calcPoint(getThisPoint) - getPoint(info, getThisPoint));
                if(max == calcPoint(getThisPoint) - getPoint(info, getThisPoint)){
                    System.out.println("내점수 : " + calcPoint(getThisPoint) + ", "+ Arrays.toString(myPoint));
                    if(betterOne(answer, myPoint)){
                        answer = myPoint;
                    }
                }
            }
            return;
        }else if(n < 0){
            return;
        }

        for(int i=0; i<info.length; i++){
            if(!getThisPoint[i]){
                getThisPoint[i] = true;
                int[] otherPoint = Arrays.copyOf(myPoint, myPoint.length);
                otherPoint[i] = info[i]+1;
                dfs(n - (info[i]+1), info, getThisPoint, otherPoint);
                getThisPoint[i] = false;
            }
        }
    }

    private static boolean betterOne(int[] answer, int[] myPoint) {
        for(int i=answer.length-1; i>=0; i--){
            if(myPoint[i] > answer[i]) return true;
        }
        return false;
    }

    private static int calcPoint(boolean[] getThisPoint) {
        int answer = 0;
        for(int i=0; i< getThisPoint.length; i++){
            if(getThisPoint[i]){
                answer += (10-i);
            }
        }
        return answer;
    }

    private static int getPoint(int[] other, boolean[] myGet){
        boolean[] otherPoint = new boolean[other.length];
        for(int i=0; i<other.length; i++){
            if(other[i]!=0 && !myGet[i]) {
                otherPoint[i] = true;
            }
        }
        return calcPoint (otherPoint);
    }

    private static int myMaxNum(int[] myPoint){
        int result = 0;
        for(int i=0; i<myPoint.length; i++){
            result += (10-i) * myPoint[i];
        }
        return result;
    }

    private static boolean allZero(int[] myPoint){
        for(int n : myPoint){
            if(n!=0){
                return false;
            }
        }
        return true;
    }
}