//package cses;
import java.util.*;
public class grayCode {
	public static void main(String[]args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		ArrayList<String> res = graycode(n);
        for (String code : res) {
            System.out.println(code);
        }
	}
	static ArrayList<String> graycode(int n) {
        
        if (n == 1) {
            ArrayList<String> base = new ArrayList<>();
            base.add("0");
            base.add("1");
            return base;
        }
      
        ArrayList<String> prevGrayCode = graycode(n - 1);
          
        ArrayList<String> reversedPrevGrayCode = new ArrayList<>(prevGrayCode);
        Collections.reverse(reversedPrevGrayCode);
      
        int prevSize = prevGrayCode.size();
        int index = 0;
        while (index < prevSize) {
        	String appendedZero = "0" + prevGrayCode.get(index);
       
            prevGrayCode.set(index, "1" + reversedPrevGrayCode.get(index));
            prevGrayCode.add(appendedZero);
            index++;
        }
        return prevGrayCode;
    }
}
