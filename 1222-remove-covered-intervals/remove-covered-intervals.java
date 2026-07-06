class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));
        
        int count = 0;
        int end = -1;
        
        for (int[] interval : intervals) {
            if (interval[1] <= end) {
                count++;
            } else {
                end = interval[1];
            }
        }
        
        return intervals.length - count;
    }
}