package cses;
import java.util.*;
public class permutation {
	public static void main(String[]args) {
		Scanner scan=new Scanner(System.in);
		
		long n=scan.nextInt();
		
		if(n==3||n==2) {
			System.out.println("NO SOLUTION");
			
		}
		else {
			  StringBuilder even = new StringBuilder();
	            StringBuilder odd = new StringBuilder();

	            // Collect even numbers
	            for (int i = 2; i <= n; i += 2) {
	                even.append(i).append(" ");
	            }

	            // Collect odd numbers
	            for (int i = 1; i <= n; i += 2) {
	                odd.append(i).append(" ");
	            }

	            // Combine and print both sequences
	            System.out.println(even.toString() + odd.toString());
		}
	}
}
