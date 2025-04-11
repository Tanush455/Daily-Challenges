class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int inc = 1, dec = 1;  
        int maxInc = 1, maxDec = 1;

        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                inc++;
            } else {
                inc = 1;
            }
            maxInc = Math.max(maxInc, inc);
        }
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                dec++;
            } else {
                dec = 1;
            }
            maxDec = Math.max(maxDec, dec);
        }

        return Math.max(maxInc, maxDec);
    }
}
