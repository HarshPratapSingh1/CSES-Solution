package cses;
import java.util.*;
public class palindromReader {
	public static void main(String[]args) {
		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		
		HashMap<Character,Integer> map = new HashMap<>();
		
		for(char c : s.toCharArray()) map.put(c, map.getOrDefault(c, 0)+1);
		
		boolean isPal = false;
		
		int cnt = 0;
		
		for(Map.Entry<Character, Integer> m : map.entrySet()) {
			if(m.getValue() % 2 != 0) {
				if(cnt > 0) {
					isPal = true;
					break;
				} else cnt++;
			}
		}
		
		if(isPal) System.out.println("NO SOLUTION");
		
		else {
			StringBuilder sb = new StringBuilder();
			char AtMid = ' ';
			for(Map.Entry<Character, Integer> m : map.entrySet()) {
				if(m.getValue() % 2 != 0) AtMid = m.getKey();
				
				int toGo = m.getValue() / 2;
				for(int i = 0 ; i < toGo ; i++) sb.append(m.getKey());
			}
			if(AtMid != ' ') System.out.println(sb.toString() + AtMid + sb.reverse().toString());
			else System.out.println(sb.toString() + sb.reverse().toString());
		}
	}
}
