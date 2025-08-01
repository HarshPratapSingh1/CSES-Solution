package cses.IntroductoryProblems;
import java.math.BigInteger;
import java.util.*;
public class missingNumber {
	public static void main(String[]args) {
		 Scanner scan = new Scanner(System.in);
	        
	        
	        int t = scan.nextInt();
	        
	     
	        int arr[] = new int[t-1];
	        
	     
	        BigInteger sum = BigInteger.ZERO;
	        
	        
	        for (int i = 0; i < t-1; i++) {
	            arr[i] = scan.nextInt();
	            sum = sum.add(BigInteger.valueOf(arr[i]));
	        }
	        
	        BigInteger AcSum = BigInteger.valueOf(t).multiply(BigInteger.valueOf(t + 1)).divide(BigInteger.valueOf(2));
	        
	      
	        BigInteger dif = AcSum.subtract(sum);
	        
	      
	        System.out.println(dif);
	}
}
