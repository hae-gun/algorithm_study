package datastructure.sort;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = new int[]{9,7,6,4,5,8,1,2,3,0};
        // 2번째 인덱스 부터 값을 잡고 현인덱스 ~ 0 까지 현재값보다 작은 값이 나올때까지 배열을 옆으로 밀어줌.
        // 인덱스가 0에 도달하면 잡고있는 값이 가장 작은거임
        // 시간복잡도 O(n^2) -> 1, 2, 3 ... n-2, n-1 Full Scan
        // 공간복잡도 O(n) 배열하나.
        for(int i=1; i<arr.length; i++){
            int key = arr[i], j = i-1;
            System.out.println("KEY INDEX " + i + ", key = " + key);
            while(j>=0 && key < arr[j]){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }

    }
}
