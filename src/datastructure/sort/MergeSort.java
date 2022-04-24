package datastructure.sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MergeSort {
    // 병합정렬
    // 배열을 최소한으로 잘라서 정렬함.
    // 재귀로 호출함.
    // 재귀로 호출한 이전의 배열은 정렬되어 있다.
    public static void main(String[] args) {

        int[] arr = new int[]{7,6,4,5,10,8,9,2,1,3};
        int[] arrange = new int[arr.length];
        System.out.println(Arrays.toString(arr));
        mergeSort(arr,arrange, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr,int[] arrange, int start,int end) {
        if(start < end){
            int mid = (start + end) / 2;
            mergeSort(arr, arrange, start, mid);
            mergeSort(arr, arrange, mid+1, end);
            int p = start;
            int q = mid+1;
            int idx = start;

            while(p<=mid || q<=end){
                if(q>end || (p<=mid && arr[q] > arr[p])){
                    arrange[idx++] = arr[p++];
                }else{
                    arrange[idx++] = arr[q++];
                }
            }
            for(int i=start; i<=end; i++){
                arr[i] = arrange[i];
            }
            System.out.println(Arrays.toString(arrange));
        }
    }




}
