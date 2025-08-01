package cses.IntroductoryProblems;
import java.io.*;
import java.util.*;
public class tasksAndDeadlint {
	public static void main(String[]args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        
		
		int arr[][]=new int[n][2];
		for(int i=0;i<n;i++) {
			String[] input = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(input[0]); 
            arr[i][1] = Integer.parseInt(input[1]); 
		}
		
		Arrays.sort(arr,(x,y)->Integer.compare(x[0], y[0]));
		
		long reward=0;
		long val=0;
		for(int i=0;i<n;i++) {
			val+=arr[i][0];
			reward+= (arr[i][1]-val);
		}
		System.out.println(reward);
	}
}
