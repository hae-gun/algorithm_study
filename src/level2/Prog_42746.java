package level2;

import common.Print;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Prog_42746 {
    public static void main(String[] args) {
        Print.answer("9534330" .equals(Solution42746.solution(new int[]{3, 30, 34, 5, 9})));
    }
}
// 아이디어
// 두 문자열을 합친 숫자중 큰 수로 정렬한다.
class Solution42746 {
    // 초기 작성. 버블정렬로 정렬함. -> 시간초과
    /*public static String solution(int[] numbers) {
        String answer = "";
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length - i; j++) {
                if (j + 1 == numbers.length - i) continue;
                String num1 = "" + numbers[j] + numbers[j + 1];
                String num2 = "" + numbers[j + 1] + numbers[j];
                //System.out.println(num1 + " : " + num2);
                if (compare(num1, num2)) {
                    int temp = numbers[j + 1];
                    numbers[j + 1] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
        for (int num : numbers) {
            answer += num;
        }
        if (Integer.valueOf(answer) == 0) {
            return "0";
        }
        return answer;
    }

    public static boolean compare(String num1, String num2) {
        int n1 = Integer.valueOf(num1);
        int n2 = Integer.valueOf(num2);
        return n1 > n2;
    }*/
    // 2번째 정렬. Collections.sort 이용.
    // 정답처리됨.
    // 예외 케이스 ( 숫자가 중복으로 들어 올 수 있다.)
    // 0이 여러개 들어있는 배열의 경우 문자열로 처리되면 000.... 으로 결과가 나와서 실패함.
    // 정렬후 배열 첫번째 값이 0 이면 "0" 으로 출력 시켜 줌.
    public static String solution(int[] numbers) {
        String answer = "";
        List<String> list = new ArrayList<>();
        for(int n : numbers){
            list.add(""+n);
        }

        Collections.sort(list, (o1, o2) -> (o2+o1).compareTo(o1+o2));
        if("0".equals(list.get(0))){
            return "0";
        }
        for(String s : list){
            answer += s;
        }
        return answer;
    }
}