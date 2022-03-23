package week2;

import common.Print;

import java.util.*;
/*
* 풀이법
* 투포인터를 이용.
* 배열을 순회할때 이중 배열로 순회시 시간 복잡도 O(N^2)로 시간 초과 걸림. (모든 부분 배열을 계산하는 경우 N 최대 100,000)
* start, end 인덱스를 두고 배열을 조건에 맞게 순회함.
*
* 문제의 조건
* 모든 보석이 존재해야 한다. -> start ~ N 번째 부분 배열을 보았을 때 모든 종류의 보석이 존재하는 경우.
*
* 풀이
*
* 모든 종류의 보석을 갖는 Set 선언.
* 각 문자열의 갯수를 확인할 Map 을 선언. (보석이름, 현재까지 존재하는 갯수)
*
* 먼저 start 를 0으로 잡고
* 첫 원소부터 큐에 넣는다.
* Map 에 원소를 넣으면서 조건 확인. (키가 있으면 +1, 없으면 1넣기)
* 큐의 가장 첫번째 원소의 Map 값이 2이상일 경우 -> 맨 앞의 원소는 필요 없어도 됨. ex )  A, B, C, A 인 경우 맨처음 A 가 없어도 모든 문자가 포함됨.
* 위의 조건의 경우 큐를 poll 처리함.
*
* q의 의미 -> 부분 배열 상태.
*
* start, end 변경되는 조건 -> 모든 문자열이 존재하고 (Map 크기와 Set 크기가 같은경우), 이전에 계산한 길이보다 현재 계산된 q 사이즈가 작은 경우.
* 아래 소스에서는 end = start + length 로 계산.
*
* */

// https://programmers.co.kr/learn/courses/30/lessons/67258
public class Prog_보석쇼핑 {
    public static void main(String[] args) {
        Print.answer(Solution67258.solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"}), new int[]{3, 7});
    }
}

class Solution67258 {
    static Queue<String> q = new LinkedList<String>();
    static HashSet<String> hs = new HashSet<String>();
    static HashMap<String, Integer> hm = new HashMap<String, Integer>();
    static int startPoint = 0;
    static int length = Integer.MAX_VALUE;

    public static int[] solution(String[] gems) {
        int[] answer;
        for (String g : gems) {
            hs.add(g);
        }
        int start = 0;
        //System.out.println(Arrays.toString(gems));
        for (int i = 0; i < gems.length; i++) {
            hm.put(gems[i],hm.getOrDefault(gems[i],0) + 1);
            q.add(gems[i]);
            while (true) {
                String temp = q.peek();
                if (hm.get(temp) > 1) {
                    hm.put(temp, hm.get(temp) - 1);
                    q.poll();
                    startPoint++;
                } else {
                    break;
                }
            }
            // hm, hs 사이즈가 같다 -> 모든 문자열이 존재한다. , length > q.size() -> 아직 변경된 적 없거나(length 가 MAX_VALUE 일때) or 변경되어도 더 작은 경우가 있는경우
            if (hm.size() == hs.size() && length > q.size()) {
                length = q.size();
                start = startPoint;
            }
        }
        return new int[]{start + 1, start + length};
    }

    // 효율성 실패
    public static int[] solution_not_solve(String[] gems) {
        int[] answer = new int[2];
        System.out.println(Arrays.toString(gems));
        Set<String> set = new HashSet<>();
        for (String gem : gems) {
            set.add(gem);
        }
        List<String> list = new ArrayList<>();

        return answer;
    }

    private static boolean containsAll(Set<String> set, String getStr) {
        for (String gem : set) {
            if (!getStr.contains(gem)) {
                return false;
            }
        }
        return true;
    }
}
