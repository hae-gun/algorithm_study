package datastructure.search;

public class BinarySearch {
    // 바이너리 서치
    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 4, 6, 7, 9, 10};
        int left = 0;
        int right = arr.length - 1;

        BinarySearch bs = new BinarySearch();
        int idx = bs.findIdx(arr, 0, arr.length - 1, 2);

        System.out.println(bs.findSqrt(25));
    }

    private int findIdx(int[] arr, int start, int end, int target) {
        int mid = (start + end) / 2;
        while (arr[mid] != target) {
            if (arr[mid] > target) {
                end = mid - 1;
            } else if (arr[mid] < target) {
                start = mid + 1;
            }
            System.out.println(start + "=" + end);
            if (start > end) return -1;
            mid = (start + end) / 2;
        }
        return mid;
    }

    private int findSqrt(int n){
        int min = 0;
        int max = n;
        while(min <= max){
            int mid = (min + max)/2;
            if(mid*mid == n){
                return mid;
            }
            if(mid*mid > n){
                max = mid-1;
            }else{
                min = mid+1;
            }
        }
        return -1;
    }

}
