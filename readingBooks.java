package cses;
import java.util.*;
import java.io.*;
public class readingBooks {
	public static void main(String[]args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        long[] arr = new long[n];
        long val = 0;

        String[] input = br.readLine().trim().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(input[i]);
            val += arr[i];
        }

		Arrays.sort(arr);
		long max=0;
		if(val-arr[n-1]<arr[n-1]) {
			max=2*arr[n-1];
		}
		
		System.out.println(Math.max(max, val));
	}
	
}
