package cses.IntroductoryProblems;
import java.util.*;
public class repetitions {
	public static void main(String[]args) {
		Scanner scan=new Scanner(System.in);
		String s=scan.next();
		
		boolean flag=false;
		int cnt=1;
		int max=1;
		for(int i=0;i<s.length()-1;i++) {
			if(s.charAt(i)==s.charAt(i+1))flag=true;
			else if(s.charAt(i)!=s.charAt(i+1)) {
				
				flag=false;
				cnt=1;
			}
			if(flag==true) {
				cnt++;
			}
			max=Math.max(max, cnt);
		}
		System.out.println(max);
	}
}
