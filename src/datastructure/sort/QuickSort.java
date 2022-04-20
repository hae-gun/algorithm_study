package datastructure.sort;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuickSort {
    public static void main(String[] args) {
        // pivot 을 이용.
        // 투포인터 이용
        // pivot 보다 작은것은 left로 큰것은 right로 옮김.
        // 투포인터 교차시 해당지점 -> pivot 위치
        int[] arr = new int[]{9,3,2,1,4,5,6,7,0,8};
//        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
//        List<Integer> result = quickSort(new ArrayList<Integer>(list));
//        List<Integer> result = quickSort(list);
        System.out.println(Arrays.toString(arr));
        quickSort(arr, 0, arr.length-1);
    }

    private static void quickSort(int[] arr, int low, int high) {

        if(low >= high) return;
        int pivot = partition(arr, low, high);
        quickSort(arr, low, pivot-1);
        quickSort(arr, pivot+1, high);
    }

    private static int partition(int[] arr, int left, int right) {
        int mid = (left+right)/2;
        int pivot = arr[mid];
        int st = left;
        int end = right;
        System.out.println(st + "~"+end);
        while(left <= right){
            while(arr[left] < pivot){
                left++;
            }
            while(arr[right] > pivot){
                right--;
            }
            if(left <= right){ // 교환후 인덱스 증가 하기  -> 왜?
                swap(arr, left, right);
                System.out.printf("SWAP %d and %d\n", left, right);
                System.out.println(Arrays.toString(arr));
                left++; right--;
            }
        }
        return left;
    }

    private static void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }

    public static List<Integer> quickSort(List<Integer> list) {
        System.out.println(list);
        if (list.size() <= 1) return list;
        int pivot = list.get(list.size() / 2);

        List<Integer> lesserArr = new LinkedList<>();
        List<Integer> equalArr = new LinkedList<>();
        List<Integer> greaterArr = new LinkedList<>();

        for (int num : list) {
            if (num < pivot) lesserArr.add(num);
            else if (num > pivot) greaterArr.add(num);
            else equalArr.add(num);
        }

        return Stream.of(quickSort(lesserArr), equalArr, quickSort(greaterArr))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
