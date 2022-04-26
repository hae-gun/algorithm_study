package datastructure.search;

import java.util.Arrays;

public class EratosThenes {
    public static void main(String[] args) {
        int n = 243;

        EratosThenes es = new EratosThenes();
        es.isPrime(n);
    }

    public boolean isPrime(int n){
        int[] arr = new int[n+1];

        Arrays.fill(arr, 1);
        arr[0] = -1;
        arr[1] = -1;
        for(int i=2; i*i <= n; i+=1){
            for(int j=i*i; j<=n; j+=i){
                arr[j] = -1;
            }
        }
        for(int i=1; i<arr.length; i++){
            if(arr[i]== 1)
                System.out.print(i+" ");
        }

        return arr[n] != -1;
    }
}
