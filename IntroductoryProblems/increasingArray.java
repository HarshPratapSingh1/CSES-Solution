package cses;
import java.util.*;
public class increasingArray {
	public static void main(String[]args) {
		Scanner scan=new Scanner(System.in);
		int n=scan.nextInt();
		int arr[]=new int[n];
		
		for(int i=0;i<n;i++)arr[i]=scan.nextInt();
		long cnt=0;
		for(int i=0;i<n-1;i++) {
			if(arr[i]>arr[i+1]) {
				cnt+=(arr[i]-arr[i+1]);
				arr[i+1]=arr[i];
			}
		}
		System.out.println(cnt);
	}
}
