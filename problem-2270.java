class Solution {
    public int waysToSplitArray(int[] nums) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1]; 
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        int ans = 0;
        for (int i = 1; i < n; i++) { 
            if (prefixSum[i] >= prefixSum[n] - prefixSum[i]) {
                ans++;
            }
        }

        return ans;
    }
}
