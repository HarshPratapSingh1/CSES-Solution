package cses;

import java.util.Scanner;

public class bitString {
	static int MOD = (int)1e9 + 7;
	public static void main(String[]args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		
		long ans = help(2, n);
		System.out.println(ans);
		scan.close();
	}
	public static long help (long a, long b) {
		if(b == 0) return 1L;
		
		long ans = help(a,b/2);
		if((b&1)==1) return (ans * ans * a) % MOD;
		else return (ans * ans ) % MOD;
	}
}
