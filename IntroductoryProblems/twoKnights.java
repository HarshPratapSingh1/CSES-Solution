package cses.IntroductoryProblems;
import java.util.*;
public class twoKnights {
	public static void main(String[]args) {
		Scanner scan=new Scanner(System.in);
		int k=scan.nextInt();
		
		int n=k;
		for(int i=1;i<=n;i++) {
			long K=(int)Math.pow(i, 2);
			long cal=(K*(K-1))/2-(4*(i-1)*(i-2));
			System.out.println(cal);
		}
	}
}
