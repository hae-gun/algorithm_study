package datastructure.sort;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = new int[]{9,7,6,4,5,8,1,2,3,0};
        // 첫 인덱스 부터 가장 작은(또는큰)값을 선택, 0번 인덱스 부터 순서대로 넣기.
        // 2중포문 사용. 시간 복잡도 O(n^2)
        for(int i=0; i<arr.length; i++){
            int idx = i;
            for(int j=i; j<arr.length; j++){
                if(arr[j] < arr[idx]){
                    idx = j;
                }
            }
            int tmp = arr[idx];
            arr[idx] = arr[i];
            arr[i] = tmp;
        }
    }
}
