//package cses;
import java.util.*;
import java.math.*;
public class trailingZeroes {
	public static void main(String[]args) {
		Scanner scan = new Scanner(System.in);
        long n = scan.nextLong();
        scan.close();

        long twos = countFactors(n, 2) ;
        long fives = countFactors(n, 5);

        System.out.println(Math.min(twos, fives));
    }

    private static long countFactors(long x, int p) {
        long count = 0;
        while (x > 0) {
            count += x / p;
            x /= p;
        }
        return count;
    }
}