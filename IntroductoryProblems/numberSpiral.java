package cses.IntroductoryProblems;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
public class numberSpiral {
	public static void main(String[]args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        int t = Integer.parseInt(reader.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            long y = Long.parseLong(st.nextToken());
            long x = Long.parseLong(st.nextToken());

            long ans;
            if (y > x) {
                ans = (y - 1) * (y - 1);
                if (y % 2 == 0) {
                    ans += (2 * y - x);
                } else {
                    ans += x;
                }
            } else {
                ans = (x - 1) * (x - 1);
                if (x % 2 == 0) {
                    ans += y;
                } else {
                    ans += (2 * x - y);
                }
            }
            output.append(ans).append("\n");
        }
        System.out.print(output);
        }
	
}