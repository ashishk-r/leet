class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int mod = 1_000_000_007;
        
        int[][] dpSum = new int[n][n];
        int[][] dpCount = new int[n][n];
        
        for (int[] row : dpSum) Arrays.fill(row, -1);
        
        dpSum[n - 1][n - 1] = 0;
        dpCount[n - 1][n - 1] = 1;
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (board.get(i).charAt(j) == 'X' || (i == n - 1 && j == n - 1)) continue;
                
                int val = 0;
                if (board.get(i).charAt(j) != 'E') {
                    val = board.get(i).charAt(j) - '0';
                }
                
                int maxPrevSum = -1;
                int paths = 0;
                
                int[][] dirs = {{0, 1}, {1, 0}, {1, 1}};
                for (int[] dir : dirs) {
                    int ni = i + dir[0];
                    int nj = j + dir[1];
                    
                    if (ni < n && nj < n && dpSum[ni][nj] != -1) {
                        if (dpSum[ni][nj] > maxPrevSum) {
                            maxPrevSum = dpSum[ni][nj];
                            paths = dpCount[ni][nj];
                        } else if (dpSum[ni][nj] == maxPrevSum) {
                            paths = (paths + dpCount[ni][nj]) % mod;
                        }
                    }
                }
                
                if (maxPrevSum != -1) {
                    dpSum[i][j] = maxPrevSum + val;
                    dpCount[i][j] = paths;
                }
            }
        }
        
        return dpSum[0][0] == -1 ? new int[]{0, 0} : new int[]{dpSum[0][0], dpCount[0][0]};
    }
}