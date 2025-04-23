package oj_24;

// 24 批次计算任务

/**
 * OJ考题代码：批次计算任务
 *
 * @author 命题组
 * @since 2020-05-23
 */
class Solution {
    int batchCalculation(int nCount, int mCount, int[] nums) {
        if (mCount >= nCount) {
            return 10000;
        }

        // 其实很简单，想要得到最长的距离，纠错肯定要一起用，那问题就很简单了
        int ans = 0;
        for (int i = 0; i + mCount <= nums.length; i++) {
            // num[i] 开始，连续纠错 mCount 个
            int begin = i - 1 >= 0 ? nums[i - 1] : 0;
            int end = i + mCount == nums.length ? 10001 : nums[i + mCount];
            ans = Math.max(ans, end - begin - 1);
        }
        return ans;
    }
}


