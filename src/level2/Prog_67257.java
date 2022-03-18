package level2;

import common.Print;

import java.util.*;
// 연산자가 3개밖에 주어지지 않는다. 6가지의 경우에서 연산하면됨.
// 처음에 실패한 이유. int 형으로 계산하여 결과 달라짐. long 형으로 변환하니 완료.
// ** 자료형 확인 잘하자. 문제에서 solution 메서드 조차도 long 으로 주어졌다. **
public class Prog_67257 {
    public static void main(String[] args) {
        //Print.answer(Solution67257.solution("100-200*300-500+20") == 60420);
        Print.answer(Solution67257.solution("1-2-3*4+1") == 20);
    }
}
class Solution67257{
    static List<String> allList = new ArrayList <>();
    public static long solution(String expression) {
        long answer = 0;
        String[] numbers = expression.split("[+*-]");

        // 연산자 우선순위 배열 리스트 0:+ 1:- 2:*
        bfs("");

        long max = 0;

        for(String s : allList){

            LinkedList<String> numQueue = new LinkedList<>(Arrays.asList(numbers));
            LinkedList<String> operationQueue = new LinkedList<>();
            String[] words = expression.split("");
            for(int i=0; i<words.length; i++){
                if("+".equals(words[i]) || "*".equals(words[i]) || "-".equals(words[i])){
                    operationQueue.add(words[i]);
                }
            }
            Stack<String> operation = new Stack<String>();
            operation.push(numQueue.poll());
            while(!operationQueue.isEmpty()){
                if(!operationQueue.isEmpty()){
                    operation.push(operationQueue.poll());
                }
                if(!numQueue.isEmpty()){
                    operation.push(numQueue.poll());
                }
            }
            int idx = 0;
            while(idx < 3){
                String operator = "" + s.charAt(idx);
                System.out.println(operator);
                System.out.println("BF " + operation);
                int operIdx = operation.indexOf(operator);
                if(operIdx == -1){
                    idx++;
                    continue;
                }
                long prev = Long.valueOf(operation.get(operIdx-1));
                long next = Long.valueOf(operation.get(operIdx+1));
                operation.remove(operIdx);
                operation.add(operIdx, calcu(prev, next, operator));
                operation.remove(operIdx-1);
                operation.remove(operIdx);
                System.out.println("AF " + operation);
                if(!operation.contains(operator)){
                    idx++;
                }
            }
            max = Math.max(max, Math.abs(Integer.valueOf(operation.get(0))));
        }
        return max;
    }

    private static String calcu(long prev, long next, String s) {
        System.out.println(prev + ", " + next);
        if("+".equals(s)){
            return String.valueOf(prev + next);
        }else if("*".equals(s)){
            return String.valueOf(prev * next);
        }else{
            return String.valueOf(prev - next);
        }
    }
    static String[] oper = {"+","-","*"};
    private static void bfs(String list) {
        if(list.length() == 3){
            allList.add(list);
            return;
        }

        for(int i=0; i<3; i++){
            String s = oper[i];
            if(list.contains(s)) continue;
            bfs(list + s);
        }
    }


}
