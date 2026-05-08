import java.util.*;
import java.io.*;

public class NestedRangesCount {
    static FastIO scan;
    static final int MOD = 1_000_000_007;
    static final long LINF = (long) 1e18;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("input.txt"));
        // System.setOut(new PrintStream("output.txt"));
        scan = new FastIO();
        int t = 1;
        // t = scan.nextInt();
        while (t-- > 0) {
            solve();
        }
        scan.close();
    }

    static void solve() throws IOException {
        int n = scan.nextInt();
        long arr[][] = new long[n][3];

        for (int i = 0; i < n; i++) {
            arr[i][0] = scan.nextLong();
            arr[i][1] = scan.nextLong();
            arr[i][2] = i;
        }

        Arrays.sort(arr, (x, y) -> {
            if (x[0] != y[0])
                return Long.compare(x[0], y[0]);
            else
                return Long.compare(y[1], x[1]);
        });
        long rightArr[] = new long[n];

        for (int i = 0; i < n; i++)
            rightArr[i] = arr[i][1];

        Solution sol = new Solution();
        List<List<Integer>> l = sol.countElements(rightArr);

        long[] contains = new long[n];
        long[] contained = new long[n];

        for (int i = 0; i < n; i++) {
            int idx = (int) arr[i][2];
            contains[idx] = l.get(0).get(i);
        }
        for (int i = 0; i < n; i++) {
            int idx = (int) arr[i][2];
            contained[idx] = l.get(1).get(i);
        }
        for (long it : contains)
            scan.p(it + " ");
        scan.pn(" ");
        for (long it : contained)
            scan.p(it + " ");
        scan.pn(" ");
    }

    // ---------------------- FAST I/O ----------------------
    static class FastIO {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final PrintWriter out = new PrintWriter(System.out);

        private int readByte() throws IOException {
            if (ptr >= len) {
                ptr = 0;
                len = in.read(buffer);
                if (len <= 0)
                    return -1;
            }
            return buffer[ptr++];
        }

        public String next() throws IOException {
            int c = readByte();
            while (c >= 0 && c <= 32)
                c = readByte();
            if (c == -1)
                return null;
            StringBuilder sb = new StringBuilder();
            while (c > 32) {
                sb.append((char) c);
                c = readByte();
            }
            return sb.toString();
        }

        public int nextInt() throws IOException {
            int c = readByte();
            while (c >= 0 && c <= 32)
                c = readByte();
            if (c == -1)
                return 0;
            int s = 1;
            if (c == '-') {
                s = -1;
                c = readByte();
            }
            int v = 0;
            while (c > 32) {
                v = v * 10 + (c - '0');
                c = readByte();
            }
            return v * s;
        }

        public long nextLong() throws IOException {
            int c = readByte();
            while (c >= 0 && c <= 32)
                c = readByte();
            if (c == -1)
                return 0;
            int s = 1;
            if (c == '-') {
                s = -1;
                c = readByte();
            }
            long v = 0;
            while (c > 32) {
                v = v * 10 + (c - '0');
                c = readByte();
            }
            return v * s;
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        public void pn(Object o) {
            out.println(o);
        }

        public void p(Object o) {
            out.print(o);
        }

        public void close() {
            out.close();
        }
    }
}

class Solution {
    int[] countSmallerOrEqualRight;
    int[] countLargerOrEqualLeft;

    public List<List<Integer>> countElements(long[] nums) {
        int n = nums.length;
        countSmallerOrEqualRight = new int[n];
        countLargerOrEqualLeft = new int[n];

        long[][] arr = new long[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        mergeSort(arr, 0, n - 1);

        List<Integer> smallerRight = new ArrayList<>();
        List<Integer> largerLeft = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            smallerRight.add(countSmallerOrEqualRight[i]);
            largerLeft.add(countLargerOrEqualLeft[i]);
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(smallerRight);
        result.add(largerLeft);
        return result;
    }

    private void mergeSort(long[][] arr, int left, int right) {
        if (left >= right)
            return;

        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private void merge(long[][] arr, int left, int mid, int right) {
        int j1 = mid + 1;
        for (int i = left; i <= mid; i++) {
            while (j1 <= right && arr[j1][0] <= arr[i][0]) {
                j1++;
            }
            countSmallerOrEqualRight[(int) arr[i][1]] += (j1 - (mid + 1));
        }

        int i1 = left;
        for (int j = mid + 1; j <= right; j++) {
            while (i1 <= mid && arr[i1][0] < arr[j][0]) {
                i1++;
            }
            countLargerOrEqualLeft[(int) arr[j][1]] += (mid - i1 + 1);
        }

        long[][] temp = new long[right - left + 1][2];
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            if (arr[i][0] <= arr[j][0]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];
        }
    }
}