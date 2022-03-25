package week2;

import common.Print;

import java.util.*;

public class Prog_표편집 {
    public static void main(String[] args) {
        Print.answer(Solution81303.solution(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"}), "OOOOXOOO");
        Print.answer(Solution81303.solution(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"}), "OOXOXOOO");
//        8	2	["D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"]	"OOXOXOOO"
    }
}

class Node {
    Node prev;
    Node next;
    String status = "O";
    int idx;

    public Node(int idx) {
        this.idx = idx;
    }

    @Override
    public String toString() {
        List<String> connect = new ArrayList<>();
        if (prev != null) {
            connect.add("Prev: " + prev.idx);
        }
        if (next != null) {
            connect.add("Next: " + next.idx);
        }

        return "Node{" + connect
                + " status='" + status + '\'' +
                ", idx=" + idx +
                '}';
    }
}

class Solution81303 {
    // 효율성 테스트 실패 -> 문자열 만드는 로직 String += 연산 -> StringBuilder 사용하여 효율성 통과됨.
    public static String solution(int n, int k, String[] cmds) {
        String answer = "";
        Node[] nodes = new Node[n];
        boolean[] remove = new boolean[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
            if (i != 0) {
                nodes[i - 1].next = nodes[i];
                nodes[i].prev = nodes[i - 1];
            }
        }
        Node curNode = nodes[k];
        Stack<Node> history = new Stack<>();
        for (String cmd : cmds) {
            //System.out.println("BEFORE : " + Arrays.toString(remove));
            //System.out.println(cmd);
            String[] oper = cmd.split(" ");

            switch (oper[0]) {
                case "D":
                    int move = Integer.valueOf(oper[1]);
                    while (curNode.next != null) {
                        curNode = curNode.next;
                        move--;
                        if (move == 0) {
                            break;
                        }
                    }
                    break;
                case "U":
                    move = Integer.valueOf(oper[1]);
                    while (curNode.prev != null) {
                        curNode = curNode.prev;
                        move--;
                        if (move == 0) {
                            break;
                        }
                    }
                    break;
                case "C":
                    history.add(curNode);
                    remove[curNode.idx] = true; // 해당노드 삭제처리
                    Node prev = curNode.prev;
                    Node next = curNode.next;

                    if (prev != null) prev.next = next;
                    if (next != null) next.prev = prev;

                    if (curNode.next == null) curNode = prev; // 맨끝노드일때.
                    else curNode = next; // 나머지 (맨앞노드, 중간노드)
                    /* 위 코드로 깔끔하게 정리.
                    if(curNode.next != null && curNode.prev != null){ // 중간노드인 경우
                        Node prev = curNode.prev;
                        Node next = curNode.next;
                        prev.next = next;
                        next.prev = prev;
                        curNode = next;
                    }else if(curNode.next == null && curNode.prev != null){ // 가장끝노드
                        Node prev = curNode.prev;
                        Node next = curNode.next;
                        prev.next = next;
                        curNode = prev;
                    }else if(curNode.next !=null && curNode.prev == null){ // 가장 처음노드
                        Node prev = curNode.prev;
                        Node next = curNode.next;
                        next.prev = prev;
                        curNode = next;
                    }*/
                    break;
                case "Z":
                    Node backupNode = history.pop();
                    remove[backupNode.idx] = false; // 노드 복구
                    //System.out.println("BACK UP NODE " + backupNode);
                    Node bkPrev = backupNode.prev;
                    Node bkNext = backupNode.next;

                    if (bkPrev != null) {
                        bkPrev.next = backupNode;
                    }
                    if (bkNext != null) {
                        bkNext.prev = backupNode;
                    }

                    break;
            }

            //System.out.println("AFTER : " + Arrays.toString(remove));

        }
        StringBuilder sb = new StringBuilder();
        for (boolean isRemove : remove) {
            if (isRemove) sb.append("X");
            else sb.append("O");
        }
        answer = sb.toString();
        return answer;
    }

    // 시간초과
    public static String solution_notSolve(int n, int k, String[] cmds) {
        String answer = "";
        String[] result = new String[n];
        Arrays.fill(result, "O");
        Stack<Integer> history = new Stack<>();

        System.out.println(Arrays.toString(result));
        String[] temp = Arrays.copyOf(result, result.length);
        temp[k] = "P";
        System.out.println(Arrays.toString(temp));

        for (String cmd : cmds) {
            String[] code = cmd.split(" ");
            if (code.length == 2) {
                System.out.println("BEFORE IDX :" + k + " MOVE :" + cmd);
                int move = Integer.valueOf(code[1]);
                if ("U".equals(code[0])) {
                    while (move != 0) {
                        if (k < 0) {
                            k = 0;
                            break;
                        }
                        if (k >= 0) {
                            if ("X".equals(result[k - 1])) {
                                k -= 1;
                                continue;
                            } else {
                                k -= 1;
                                move -= 1;
                            }
                        }
                        if (k < 0) k = 0;
                    }
                } else if ("D".equals(code[0])) {
                    while (move != 0) {
                        if (k == n) {
                            k -= 1;
                            break;
                        }
                        if (k < n) {
                            if (!"X".equals(result[k + 1])) {
                                k += 1;
                                move--;
                            } else {
                                k += 1;
                            }
                        }
                        if (k >= n) k = n - 1;
                    }
                }
            } else {
                if ("C".equals(code[0])) {
                    System.out.println("DELETE CUR IDX :" + k);
                    result[k] = "X";
                    history.add(k);
                    if (k + 1 < n) {
                        k += 1;
                    } else {
                        k -= 1;
                    }
                    System.out.println("AFTER CUR IDX : " + k);
                } else {
                    if (!history.isEmpty()) {
                        int idx = history.pop();
                        System.out.println("BACK UP LAST DATA IDX:" + idx);
                        result[idx] = "O";
                    }
                }
            }
            System.out.println(Arrays.toString(result));
            temp = Arrays.copyOf(result, result.length);
            temp[k] = "P";
            System.out.println(Arrays.toString(temp));
        }
        for (String s : result) {
            answer += s;
        }
        return answer;
    }
}