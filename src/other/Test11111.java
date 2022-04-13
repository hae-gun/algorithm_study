package other;

import java.util.stream.Collectors;

import java.util.Objects;
import java.util.*;

public class Test11111 {
    public static void main(String[] args) {
        Solution4321 s = new Solution4321();
        System.out.println(Arrays.toString(s.solution(new String[]{"1", "2", "4", "3", "3", "4", "1", "5"}, new String[]{"read 1 3 1 2", "read 2 6 4 7", "write 4 3 3 5 2", "read 5 2 2 5", "write 6 1 3 3 9", "read 9 1 0 7"})));
        s = new Solution4321();
        System.out.println(Arrays.toString(s.solution(new String[]{"1", "1", "1", "1", "1", "1", "1"}, new String[]{"write 1 12 1 5 8", "read 2 3 0 2", "read 5 5 1 2", "read 7 5 2 5", "write 13 4 0 1 3", "write 19 3 3 5 5", "read 30 4 0 6", "read 32 3 1 5"})));
    }
}

class Solution4321 {
    String[] allArr;
    List<String> result = new ArrayList<>();

    public String[] solution(String[] arr, String[] processes) {
        String[] answer = {};
        allArr = arr;
        List<Process> doProcess = new ArrayList<>();
        List<Process> readyProcess = new ArrayList<>();
        List<Process> allList = new ArrayList<>();
        for (int i = 0; i < processes.length; i++) {
            String s = processes[i];
            allList.add(new Process(s, i));
        }
        Queue<Process> queue = new LinkedList<>();
        Process first = allList.remove(0);
        first.setTime(first.inTime);
        queue.add(first);
        // 읽기 수행중엔 읽기 들어와도 바로 수행.
        // 쓰기 들어오면 대기.
        // 쓰기 수행중엔 모두 대기.
        // 쓰기가 끝나는 시간에 대기열 있어도 쓰기 들어오면 쓰기부터 수행.
        int time = 0;
        Process prev = null;
        while (true) {
            if (queue.peek() != null && queue.peek().isEnd(time)) {
                prev = queue.poll();
                doProcess(prev);
                if (prev.isRead() && getWrite(readyProcess) != null && time == getWrite(readyProcess).inTime) {
                    doProcess(getWriteAndRemove(readyProcess));
                    System.out.println("** " + prev);
                }
                System.out.println("** " + prev);
            }
            if (queue.isEmpty() && readyProcess.size() == 0) {
                break;
            }
            for (int i = 0; i < allList.size(); i++) {
                Process p = allList.get(i);
                if (queue.isEmpty()) {
                    if (readyProcess.size() > 0) {
                        Process pr = readyProcess.remove(0);
                        pr.setTime(time);
                        queue.add(pr);
                    } else {
                        if (allList.size() > 0) {
                            p.setTime(time);
                            queue.add(p);
                            continue;
                        }
                    }
                }
                if (queue.peek().isRead()) {
                    if (time == p.inTime) {
                        if (p.isRead()) {
                            if (!hasWrite(readyProcess)) {
                                p.setTime(time);
                                queue.add(p);
                            } else {
                                readyProcess.add(p);
                            }
                        } else {
                            readyProcess.add(p);
                        }
                        allList.remove(i);
                        break;
                    }
                } else {
                    if (time == p.inTime) {
                        readyProcess.add(p);
                        allList.remove(i);
                        break;
                    }
                }
            }
            if (allList.size() == 0 && readyProcess.size() != 0) {
                Process p = readyProcess.remove(0);
                p.setTime(time);
                queue.add(p);
            }
            System.out.println("======" + time + "=======");
            System.out.println("ALL " + allList);
            System.out.println("Ready " + readyProcess);
            System.out.println("Proc " + queue);
            time++;
        }
        System.out.println("END" + queue);
        if (!queue.isEmpty()) {
            Process p = queue.poll();
            doProcess(p);
        }

        return result.toArray(new String[result.size()]);
    }
    public String[] solution2(String[] arr, String[] processes) {



        return result.toArray(new String[result.size()]);
    }
    private Process getWrite(List<Process> readyProcess) {
        for (int i = 0; i < readyProcess.size(); i++) {
            Process p = readyProcess.get(i);
            if (!p.isRead()) {
                return p;
            }
        }
        return null;
    }

    private Process getWriteAndRemove(List<Process> readyProcess) {
        for (int i = 0; i < readyProcess.size(); i++) {
            Process p = readyProcess.get(i);
            if (!p.isRead()) {
                readyProcess.remove(i);
                return p;
            }
        }
        return null;
    }

    public boolean hasWrite(List<Process> readyProcess) {
        for (Process p : readyProcess) {
            if (!p.isRead()) return true;
        }
        return false;
    }

    public void doProcess(Process proc) {
        if (proc.isRead()) {
            String s = "";
            for (int i = proc.list.get(0); i <= proc.list.get(1); i++) {
                s += allArr[i];
            }
            result.add(s);
        } else {
            for (int i = proc.list.get(0); i <= proc.list.get(1); i++) {
                allArr[i] = String.valueOf(proc.list.get(2));
            }
        }
    }

    public Queue<Process> removeEndProcess(Queue<Process> queue, int time) {
        List<Process> list = new ArrayList<>(queue);
        list = list.stream().filter(v -> v.isEnd(time)).collect(Collectors.toList());
        return new LinkedList<Process>(list);
    }
}

class Process implements Comparator<Process> {
    String index;
    String type;
    int inTime;
    int st;
    int et;
    List<Integer> list = new ArrayList<>();

    public Process(String log, int idx) {
        index = String.valueOf(idx);
        String[] s = log.split(" ");
        this.type = s[0];
        this.inTime = Integer.valueOf(s[1]);
        this.et = Integer.valueOf(s[2]);
        list = new ArrayList<>();
        for (int i = 3; i < s.length; i++) {
            list.add(Integer.valueOf(s[i]));
        }
    }

    public void setTime(int time) {
        this.st = time;
    }

    @Override
    public String toString() {
        return "[" + index + "]" + this.type + ": is in " + inTime + " do " + st + "~" + et + ", " + list;
    }

    public boolean isRead() {
        return "read".equals(this.type);
    }

    @Override
    public int compare(Process o1, Process o2) {
        return o1.inTime - o2.inTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Process process = (Process) o;
        return inTime == process.inTime && type.equals(process.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, inTime);
    }

    public boolean isEnd(int time) {
        return this.st + this.et <= time;
    }
}