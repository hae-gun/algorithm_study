package week2;

import common.Print;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Prog_불량사용자 {
    public static void main(String[] args) {
        Print.answer(Solution64064.solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "abc1**"}),2);
        Print.answer(Solution64064.solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"*rodo", "*rodo", "******"}),2);
        Print.answer(Solution64064.solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "*rodo", "******", "******"}),3);
    }
}
class Solution64064 {
    public static int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> banCount = new HashMap<>();
        for(String s : banned_id){
            map.put(s, new ArrayList<>());
            banCount.put(s, banCount.getOrDefault(s, 0)+1);
        }
        for(String s : map.keySet()){
            for(String s2 : user_id){
                if(match(s2, s)){
                    map.get(s).add(s2);
                }
            }
        }
        

        return answer;
    }


    public static boolean match(String target, String banned_id){
        if(target.length() != banned_id.length()) return false;
        for(int i=0; i<banned_id.length(); i++){
            char c1 = banned_id.charAt(i);
            char c2 = target.charAt(i);
            if(c1 == '*') continue;
            if(c1 != c2) return false;
        }
        return true;
    }
}