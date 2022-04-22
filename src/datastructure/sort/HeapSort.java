package datastructure.sort;

import java.util.Arrays;

public class HeapSort {
    // 힙정렬
    // 힙 자료구조를 이용
    // 힙 자료구조 특징
    // 1. 이진트리 사용.
    // [성질]
    //1. 왼쪽 자식 노드 인덱스 = 부모 노드 인덱스 × 2
    //2. 오른쪽 자식 노드 인덱스 = 부모 노드 인덱스 × 2 + 1
    //3. 부모 노드 인덱스 = 자식 노드 인덱스 / 2

    public static void main(String[] args) {
        Heap heap = new Heap(1);
        System.out.println(heap);
        heap.add(2);
        System.out.println(heap);
        heap.add(3);
        System.out.println(heap);
        heap.add(4);
        System.out.println(heap);
        heap.add(5);
        System.out.println(heap);



        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());

        heap.add(5);
        System.out.println(heap);
        heap.add(3);
        System.out.println(heap);
        System.out.println(heap.remove());
        heap.add(2);
        System.out.println(heap);

        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap);
        System.out.println(heap.remove());
        System.out.println(heap);
        heap.add(3);
        System.out.println(heap);
        heap.add(2);
        System.out.println(heap);

    }


}

class Heap{
    private final int EMPTY = Integer.MIN_VALUE;
    private int capacity = 1;
    private int[] heap = new int[capacity];
    private int maxLevel = 0;
    private int lastIdx = 0;

    public Heap(int root){
        heap[0] = root;
    }

    public void add(int number){
        int idx = ++lastIdx;
        if(idx < heap.length) heap[idx] = number;
        else {
            heap = Arrays.copyOf(heap, heap.length * 2);
            heap[lastIdx] = number;
        }
        rearrange();
    }
    public int remove(){
        if(lastIdx<0) {
            lastIdx=0;
            return heap[lastIdx];
        }
        int num = heap[0];
        heap[0] = heap[lastIdx];
        heap[lastIdx] = EMPTY;
        lastIdx--;
        int tmpIdx=0;

        while(heap[tmpIdx] < heap[tmpIdx*2] || heap[tmpIdx] < heap[tmpIdx*2 + 1]){
            int nextIdx = heap[tmpIdx*2] > heap[tmpIdx*2+1] ?   tmpIdx*2 : tmpIdx*2+1;
            if(heap[nextIdx] == EMPTY) break; // 다음 큰 노드가 EMPTY 라면 해당 노드가 리프노드이다.
            swap(nextIdx, tmpIdx);
            tmpIdx = nextIdx;
        }


        return num;
    }
    private void rearrange(){
        int tmpIdx = lastIdx;
        while(tmpIdx!=0 && heap[tmpIdx] > heap[tmpIdx/2]){
            int temp = heap[tmpIdx];
            heap[tmpIdx] = heap[tmpIdx/2];
            heap[tmpIdx/2] = temp;
            tmpIdx /= 2;
        }
    }
    public int size(){
        return this.lastIdx+1;
    }
    @Override
    public String toString() {
        return "Heap{" + Arrays.toString(heap) + "}";
    }

    private void swap(int a, int b){
        int tmp = heap[a];
        heap[a] = heap[b];
        heap[b] = tmp;
    }
}