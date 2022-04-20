package datastructure.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        // pivot 을 이용.
        // 투포인터 이용
        int[] arr = new int[]{9,7,6,4,5,8,1,2,3,0};
        quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int low, int high) {

        if(low >= high) return;

        int pivotIdx = partition(arr,low, high);

        String[] x = new String[arr.length];
        Arrays.fill(x, "");
        System.out.println(low + "-" + pivotIdx + "-" + high);
        x[low] = "l";
        x[high] = "h";
        if(!"".equals(x[pivotIdx])){
            x[pivotIdx] += "p";
        }else{
            x[pivotIdx] = "p";
        }
        System.out.println(Arrays.toString(x));
        quickSort(arr, low, pivotIdx-1);
        quickSort(arr, pivotIdx+1, high);


    }

    private static int partition(int[] arr, int left, int right) {
        int lo = left;
        int hi = right;
        int pivot = arr[left];

        while(lo < hi){
            while(arr[lo] <= pivot && lo < hi){
                lo++;
            }
            while(arr[hi] > pivot && lo < hi){
                hi--;
            }
            swap(arr, lo, hi);
        }
        swap(arr, lo, left);

        return lo;
    }

    private static void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }
}
