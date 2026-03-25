package cses.IntroductoryProblems;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class towerOfHanoi {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        System.out.println((1<<n) - 1);
        solve(n,1,3);
        scan.close();
    }

    public static void solve(int n, int start, int target){
        if(n == 1){
            pm(start,target);
            return;
        }

        solve(n-1, start, 6 - (start + target));
        pm(start, target);
        solve(n-1, 6 - (start + target), target);

    }
    public static void pm(int start, int target){
        System.out.println(start+" "+target);
    }
}