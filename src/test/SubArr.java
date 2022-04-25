package test;

public class SubArr {
    //-2,1,-3,4,-1,2,1,-5,4

    public static void main(String[] args) {
        int[] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int max = Integer.MIN_VALUE;
        for(int i=0; i<arr.length; i++){
            for(int j=i; j<arr.length; j++){
                int temp = 0;
                for(int k=j; k < arr.length; k++){
                    temp += arr[k];
                    max = Math.max(max, temp);
                    System.out.println(temp);
                }
            }
        }
    }
}
