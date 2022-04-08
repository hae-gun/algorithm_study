package week3;

import java.util.*;
import java.util.Map.Entry;
public class Prog_베스트엘범 {
}
class Solution42579 {
    public int[] solution(String[] genres, int[] plays) {

        Map<String, Integer> musics = new HashMap<String, Integer>();
        Map<String, Map<Integer, Integer>> musicPlays = new HashMap<>();
        for(int i=0; i<genres.length; i++){
            if(musics.containsKey(genres[i])){
                musics.put(genres[i], musics.get(genres[i]) + plays[i]);
                musicPlays.get(genres[i]).put(i, plays[i]);
            }else{
                musics.put(genres[i], plays[i]);
                Map<Integer, Integer> playMap = new HashMap<>();
                playMap.put(i, plays[i]);
                musicPlays.put(genres[i], playMap);
            }
        }
        List<Map.Entry<String, Integer>> list_entries = new ArrayList<Map.Entry<String, Integer>>(musics.entrySet());

        Collections.sort(list_entries, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> obj1, Map.Entry<String, Integer> obj2)
            {
                return obj2.getValue().compareTo(obj1.getValue());
            }
        });
        List<Integer> answers = new ArrayList<Integer>();

        for(Entry list : list_entries){
            Map<Integer,Integer> musicList = musicPlays.get(list.getKey());
            List<Entry<Integer, Integer>> musicEntrys = new ArrayList<Entry<Integer, Integer>>(musicList.entrySet());

            Collections.sort(musicEntrys, new Comparator<Entry<Integer, Integer>>() {
                public int compare(Entry<Integer, Integer> obj1, Entry<Integer, Integer> obj2)
                {
                    return obj2.getValue().compareTo(obj1.getValue());
                }
            });
            int max = 0;
            for(Entry<Integer,Integer> list2 : musicEntrys){
                answers.add(list2.getKey());
                max++;
                if(max==2) break;
            }
        }

        int[] answer = new int[answers.size()];
        for(int i=0; i<answers.size(); i++){
            answer[i] = answers.get(i);
        }

        return answer;
    }
}