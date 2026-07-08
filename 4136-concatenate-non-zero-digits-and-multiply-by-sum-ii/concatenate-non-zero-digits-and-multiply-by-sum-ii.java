class Solution {
    static final int MOD = 1_000_000_007;
    static long[] pow10 = new long[100005];

    static {
        pow10[0] = 1;
        for (int i = 1; i < pow10.length; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }
    }

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        int[] next = new int[n];
        int[] prev = new int[n];

        int last = -1;
        for (int i = 0; i < n; i++) {
            prev[i] = last;
            if (s.charAt(i) != '0') last = i;
        }

        last = -1;
        for (int i = n - 1; i >= 0; i--) {
            next[i] = last;
            if (s.charAt(i) != '0') last = i;
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '0') cnt++;
        }

        int[] pos = new int[cnt];
        int[] idx = new int[n];

        long[] prefixNum = new long[cnt + 1];
        int[] prefixSum = new int[cnt + 1];

        int k = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '0') {
                pos[k] = i;
                idx[i] = k;

                int d = s.charAt(i) - '0';
                prefixNum[k + 1] = (prefixNum[k] * 10 + d) % MOD;
                prefixSum[k + 1] = prefixSum[k] + d;
                k++;
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int left;
            if (s.charAt(l) != '0')
                left = idx[l];
            else {
                int p = next[l];
                if (p == -1 || p > r) {
                    ans[i] = 0;
                    continue;
                }
                left = idx[p];
            }

            int right;
            if (s.charAt(r) != '0')
                right = idx[r];
            else {
                int p = prev[r];
                if (p == -1 || p < l) {
                    ans[i] = 0;
                    continue;
                }
                right = idx[p];
            }

            int len = right - left + 1;

            long x = (prefixNum[right + 1]
                    - prefixNum[left] * pow10[len] % MOD
                    + MOD) % MOD;

            long sum = prefixSum[right + 1] - prefixSum[left];

            ans[i] = (int) ((x * sum) % MOD);
        }

        return ans;
    }
}