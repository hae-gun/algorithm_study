package datastructure.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        // 버블정렬.. 인덱스 0 부터 다음 인덱스를 비교하여 정렬
        // 시간복잡도 n^2, 공간복잡도 N
        int[] arr = new int[]{1,5,9,7,8,6,4,3,2,0,10,1,5,6};

        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void bubbleSort(int[] arr) {

        for(int i=0; i<arr.length-1; i++){
            for(int j=i+1; j<arr.length; j++){
                if(arr[j] < arr[i]){
                    swap(arr, i, j);
                }
            }
        }

    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
