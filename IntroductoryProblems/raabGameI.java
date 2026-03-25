// package cses.IntroductoryProblems;

import java.util.*;

public class raabGameI {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t-- > 0) {
            int n = scan.nextInt();
            int a = scan.nextInt();
            int b = scan.nextInt();
 
            
            if(((a+b)>0 && (a==0 || b == 0))|| (a+b) > n){
                System.out.println("NO");
                continue;
            }
            System.out.println("YES");
            for(int i = b + 1; i <= a + b; i++) System.out.print(i+" ");
            for(int i = 1; i <= b; i++) System.out.print(i+" ");
            for(int i = a+b+1 ; i <= n ; i++) System.out.print(i+" ");

            System.out.println();
            for(int i = 1; i <= n; i++) System.out.print(i+" ");
            System.out.println();
        }
    }
}