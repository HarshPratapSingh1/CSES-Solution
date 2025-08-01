package cses.IntroductoryProblems;
import java.util.*;
public class weirdAlgo {
	public static void main(String[]args) {
		Scanner scan=new Scanner(System.in);
		long n=scan.nextLong();
		ArrayList<Long>l=new ArrayList<>();
		l.add(n);
		while(n!=1) {
			if(n%2==0) {
				n/=2;
			}
			else if(n%2!=0) {
				n*=3;
				n++;
			}
			l.add(n);
		}
		for(Long it:l) {
			System.out.print(it+" ");
		}
	}
}
