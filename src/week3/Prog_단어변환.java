package week3;

import common.Print;

import java.util.Arrays;
import java.util.List;

public class Prog_단어변환 {
    public static void main(String[] args) {
        //"hit"	"cog"	["hot", "dot", "dog", "lot", "log", "cog"]	4
        //"hit"	"cog"	["hot", "dot", "dog", "lot", "log"]	0
        Print.answer(Solution43163.solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"}), 4);
        Print.answer(Solution43163.solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log"}), 0);
        Print.answer(Solution43163.solution("pine", "bono", new String[]{"pone", "bone", "cine", "bino", "bono"}), 3);

    }
}

class Solution43163 {

    static int answer = Integer.MAX_VALUE;
    public static int solution(String begin, String target, String[] words) {

        List<String> list = Arrays.asList(words);

        if (!list.contains(target)) return 0;
        boolean[] used = new boolean[words.length];

        dfs(0, begin, target, words, used);

        return answer;
    }

    private static void dfs(int dept, String begin, String target, String[] words, boolean[] used) {
        if (begin.equals(target)) {
            //System.out.println(dept + ", " + Arrays.toString(used));
            answer = Math.min(answer, dept);
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (!begin.equals(words[i]) && !used[i]) {
                if (matchOneDiff(begin, words[i])) {
                    used[i] = true;
                    dfs(dept + 1, words[i], target, words, used);
                    used[i] = false;
                }
            }
        }
    }

    public static boolean matchOneDiff(String a, String b) {
        if (a.length() != b.length()) return false;
        int diffCnt = 0;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diffCnt++;
        }
        return diffCnt == 1;
    }

}
