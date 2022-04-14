package datastructure;

import java.util.*;
import java.util.Set;

public class AStarAlgorithm {
    static class Node implements Comparable<Node> {
        int id;
        double f;
        double g;
        double h;
        Node parent;

        public Node(int id) {
            this.id = id;
        }

        public Node(int id, double g) {
            this.id = id;
            this.g = g;
        }

        public void setH(double h) {
            this.h = h;
            calculateF();
        }

        public void calculateF() {
            this.f = this.g + this.h;
        }

        @Override
        public int compareTo(Node o) {
            if (this.f > o.f) return 1;
            else if (this.f < o.f) return -1;
            return 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return id == node.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    ", f=" + f +
                    ", g=" + g +
                    ", h=" + h +
                    ", parent ID:" + (parent == null ? "-" : parent.id) +
                    '}';
        }
    }

    public static void main(String[] args) {
        int startNode = 0;
        int endNode = 6;


        double[][] graph = new double[7][7];

        graph[0][1] = 5.6;
        graph[0][3] = 6.8;
        graph[1][2] = 4.3;
        graph[1][4] = 6.5;
        graph[3][2] = 5.6;
        graph[3][5] = 6.5;
        graph[2][5] = 5.8;
        graph[2][6] = 7.0;
        graph[4][6] = 5.2;
        graph[5][6] = 5.5;
        matching(graph);

        double[][] heuristics = new double[7][7];
        heuristics[1][6] = 12.0d;
        heuristics[3][6] = 10.0d;

        matching(heuristics);
/*
        System.out.println("graph");
        for (double[] line : graph) {
            System.out.println(Arrays.toString(line));
        }
        System.out.println("heuristics");
        for (double[] line : heuristics) {
            System.out.println(Arrays.toString(line));
        }
*/
        List<Integer> nodeList = findAStarDistance(0, 6, graph, heuristics);

        System.out.println(nodeList);

    }

    private static List<Integer> findAStarDistance(int startIdx, int targetIdx, double[][] graph, double[][] heuristics) {
        List<Integer> line = new ArrayList<>();
        Node startNode = new Node(startIdx);
        line.add(startNode.id);

        PriorityQueue<Node> open = new PriorityQueue<>();
        Set<Node> close = new LinkedHashSet<Node>();

        close.add(startNode);
        if (graph[startIdx][targetIdx] != 0) {
            line.add(targetIdx);
            return line;
        }
        // 첫노드에서 연결된 값들은 g = 간선 가중치.
        for (int i = 0; i < graph[startIdx].length; i++) {
            if (graph[startIdx][i] != 0) {
                Node node = new Node(i, graph[startIdx][i]);
                node.parent = startNode;
                node.setH(heuristics[i][targetIdx]);
                open.add(node);
            }
        }
        int test = 0;
        while (test != 5 && !open.isEmpty()) {
            Node cur = open.poll();
            System.out.println("CUR:" + cur);
            System.out.println("BF:" + open);
            System.out.println("CLOSE: " + close);
            for (int i = 0; i < graph[cur.id].length; i++) {
                if (graph[cur.id][i] != 0) {
                    Node node = new Node(i, cur.g + graph[cur.id][i]);
                    node.parent = cur;
                    if (graph[i][targetIdx] != 0) {
                        node.setH(graph[i][targetIdx]);
                    } else {
                        node.setH(graph[cur.id][i]);
                    }
                    List<Node> tempList = new ArrayList<>(close);
                    int idx = findNode(node, tempList);
                    if (idx != -1) {
                        Node prev = tempList.get(idx);
                        if (prev.h > node.h) {
                            close.remove(node);
                            close.add(cur);
                        }
                    } else {
                        open.add(node);
                        close.add(cur);
                    }
                }
            }
            System.out.println("AF:" + open);
            System.out.println("CLOSE: " + close);
            System.out.println("##########################");
            if(close.contains(new Node(6))){
                break;
            }
        }
        System.out.println(close);

        return line;
    }

    private static int findNode(Node cur, List<Node> close) {
        int idx = -1;
        for (int i = 0; i < close.size(); i++) {
            if (close.get(i).equals(cur)) {
                return i;
            }
        }
        return idx;
    }

    public static void matching(double[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (i == j) continue;
                if (graph[i][j] == 0) {
                    graph[i][j] = graph[j][i];
                }
            }
        }
    }
}
