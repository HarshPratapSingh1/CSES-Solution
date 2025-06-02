package cses;
import java.util.*;
import java.io.*;
public class twoSets {
	public static void main(String[]args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long sum = 1L * n * (n + 1) / 2;

        if (sum % 2 != 0) {
            System.out.println("NO");
            return;
        }

        boolean[] used = new boolean[n + 1];
        long target = sum / 2;

        for (int i = n; i >= 1; i--) {
            if (i <= target) {
                used[i] = true;
                target -= i;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("YES\n");

        int count1 = 0;
        for (int i = 1; i <= n; i++) if (used[i]) count1++;
        sb.append(count1).append('\n');
        for (int i = 1; i <= n; i++) if (used[i]) sb.append(i).append(' ');
        sb.append('\n');

        sb.append(n - count1).append('\n');
        for (int i = 1; i <= n; i++) if (!used[i]) sb.append(i).append(' ');
        sb.append('\n');

        System.out.print(sb);
    }
}