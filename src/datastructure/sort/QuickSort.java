package datastructure.sort;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuickSort {

    public static void main(String[] args) {
        // pivot 을 이용.
        // 투포인터 이용
        // pivot 보다 작은것은 left 로 큰것은 right 로 옮김.
        // -> left 는 pivot 보다 작은것이 나올때 까지 왼쪽에서 인덱스 증가,
        //    right 는 pivot 보다 큰것이 나올떄 까지 오른쪽에서 인덱스 감소.
        //    찾은 left 와 right 의 위치를 변경
        //    투포인터 교차시 (left 인덱스가 right 보다 커지면 ) 해당지점과 pivot 의 위치를 변경.
        // 교환후 인덱스 증가시키기.
        // left ~ mid 구간과 mid+1 right 구간으로 나눠 재귀실행.
        // 시간복잡도 BEST : NlogN , 평균 : NlogN, Worst : N^2
        // 공간복잡도 : N 배열 한곳에서 이동
        // 순환 호출의 깊이
        // 퀵소트가 최적일 때 : 나눠지는 배열이 반으로 딱 나눠질때.
        // 레코드의 개수 n이 2의 거듭제곱이라고 가정(n=2^k)했을 때,
        // n=2^3의 경우, 2^3 -> 2^2 -> 2^1 -> 2^0 순으로 줄어들어 순환 호출의 깊이가 3임을 알 수 있다.
        // 이것을 일반화하면 n=2^k의 경우, k(k=log₂n)임을 알 수 있다.
        // k=log₂n
        // 각 순환 호출 단계의 비교 연산
        // 각 순환 호출에서는 전체 리스트의 대부분의 레코드를 비교해야 하므로 평균 n번 정도의 비교가 이루어진다.
        // 평균 n번
        // 순환 호출의 깊이 * 각 순환 호출 단계의 비교 연산 = nlog₂n
        // 최악의 경우 : 나눠지는 배열이 한쪽에 치우칠때. 불균형으로 배열이 분해될때. -> 이미 정렬된 배열을 돌때 가장 최악이다.
        int[] arr = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 15, 11, 18, 20};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low >= high) return;
        int partition = partition(arr, low, high);
        quickSort(arr, low, partition - 1);
        quickSort(arr, partition, high);
    }

    private static int partition(int[] arr, int left, int right) {
        int mid = (left + right) / 2;
        int pivot = arr[mid];
        int st = left;
        int end = right;
        //System.out.println("구간: " + st + "~" + end + " pivot " + pivot);
        while (left <= right) {
            while (arr[left] < pivot) {
                left++;
            }
            while (arr[right] > pivot) {
                right--;
            }
            if (left > right) {
                break;
            }
            swap(arr, left, right);
            left++;
            right--;
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
