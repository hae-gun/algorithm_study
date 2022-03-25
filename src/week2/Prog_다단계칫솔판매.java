package week2;

import common.Print;

import java.util.HashMap;
import java.util.Map;

public class Prog_다단계칫솔판매 {
    public static void main(String[] args) {
        Print.answer(Solution77486.solution(
                new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[]{"young", "john", "tod", "emily", "mary"},
                new int[]{12, 4, 2, 5, 10}),
                new int[]{360, 958, 108, 0, 450, 18, 180, 1080});
        Print.answer(Solution77486.solution(
                new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[]{"sam", "emily", "jaimie", "edward"},
                new int[]{2, 3, 5, 4}),
                new int[]{0, 110, 378, 180, 270, 450, 0, 0});
    }
}

class Solution77486 {
    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        SellerNode[] nodes = new SellerNode[enroll.length];
        Map<String, Integer> indexMap = new HashMap<>();
        int[] answer = new int[enroll.length];
        for(int i=0; i<enroll.length; i++){
            nodes[i] = new SellerNode(enroll[i]);
            indexMap.put(enroll[i], i);
        }

        for(int i=0; i<referral.length; i++){
            nodes[i].parent = find(nodes, referral[i]);
        }

        for(int i=0; i<seller.length; i++){
            String name = seller[i];
            int amt = amount[i]*100;
            nodes[indexMap.get(name)].addProfit(amt);
        }

        for(int i=0; i<enroll.length; i++){
            answer[i] = nodes[indexMap.get(enroll[i])].amt;
        }

        return answer;
    }

    public static SellerNode find(SellerNode[] nodes, String name){
        for(SellerNode node : nodes){
            if(name.equals(node.name)){
                return node;
            }
        }
        return null;
    }

}
class SellerNode {
    String name;
    SellerNode parent;
    int amt;
    public SellerNode(String name){
        this.name = name;
    }

    public void addProfit(int amt){
        int profitAmt = amt / 10;
        this.amt += amt - profitAmt;
        if(this.parent != null && profitAmt >= 1){
            this.parent.addProfit(profitAmt);
        }
    }


    @Override
    public String toString(){
        String s = name + "["+amt+"] : ( P: ";
        if(parent == null){
            s += "Center )";
        }else{
            s += parent.name + ")";
        }
        return  s;
    }
}