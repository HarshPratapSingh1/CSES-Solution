package cses.IntroductoryProblems;

import java.util.Scanner;

public class appleDivision {
    static long max;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        long arr[] = new long[n];
        max = Long.MAX_VALUE;

        for(int i = 0 ; i < n ; i++) arr[i] = scan.nextLong();

        solve(0, arr ,0);
        System.out.println(max);

    }
    public static void solve(int idx, long arr[], long sum){
        if(idx == arr.length) {
            max = Math.min(max, sum);
            return;
        }
        solve(idx + 1, arr, Math.abs(sum - arr[idx]));
        solve(idx + 1, arr, sum + arr[idx]);

    }
}
