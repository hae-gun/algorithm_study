package week2;

import common.Print;

import java.util.*;

public class Prog_불량사용자 {
    public static void main(String[] args) {
        // Print.answer(Solution64064.solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "abc1**"}),2);
        // Print.answer(Solution64064.solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"*rodo", "*rodo", "******"}),2);
        Print.answer(Solution64064.solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "*rodo", "******", "******"}), 3);
    }
}

class Solution64064 {
    static Set<Set<String>> result;

    public static int solution(String[] user_id, String[] banned_id) {
        result = new HashSet<>();
        dfs(new LinkedHashSet<String>(), user_id, banned_id);

        System.out.println(result);
        return result.size();
    }

    private static void dfs(Set<String> list, String[] user_id, String[] banned_id) {
        if(list.size() == banned_id.length){
            if(check(list, banned_id)){
                result.add(new LinkedHashSet<>(list));
            }
            return;
        }
        for(String s : user_id){
            if(!list.contains(s)){
                list.add(s);
                dfs(list,user_id, banned_id);
                list.remove(s);
            }
        }
    }

    private static boolean check(Set<String> set, String[] banned_id) {
        List<String> list = new ArrayList<>(set);
        for(int i=0; i<banned_id.length; i++){
            if(!match(list.get(i), banned_id[i])){
                return false;
            }
        }
        return true;
    }


    public static boolean match(String target, String banned_id) {
        if (target.length() != banned_id.length()) return false;
        for (int i = 0; i < banned_id.length(); i++) {
            char c1 = banned_id.charAt(i);
            char c2 = target.charAt(i);
            if (c1 == '*') continue;
            if (c1 != c2) return false;
        }
        return true;
    }
}