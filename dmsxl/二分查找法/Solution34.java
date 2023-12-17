public class Solution34 {

    public static void main(String[] args) {
        Solution34 solution34 = new Solution34();
        solution34.searchRange(new int[]{1,2,3,5,7}, 4);
    }


    int[] searchRange(int[] nums, int target) {
        int leftBorder = getLeftBorder(nums, target);
        int rightBorder = getRightBorder(nums, target);
        // 情况一
        if (leftBorder == -2 || rightBorder == -2) return new int[]{-1, -1};
        // 情况三
        //为什么满足这个条件才是找到了左右边界，因为：while (left <= right) {这个条件，是从left=right后又left+1或right-1，只相差1，是没找到的情况，只有大于1了说明时找到了，被找的数左边一个右边一个，相减大于1
        if (rightBorder - leftBorder > 1) return new int[]{leftBorder + 1, rightBorder - 1};
        // 情况二
        return new int[]{-1, -1};
    }


    /**
     * 功能：获取所要查询的数的右边界，也就是示例中索引为2的位置 示例new int[]{1,2,3,5,7}, 2
     * @param nums
     * @param target
     * @return
     */
    int getRightBorder(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int rightBorder = -2; // 记录一下rightBorder没有被赋值的情况
        while (left <= right) {
            int middle = left + ((right - left) / 2);
            if (nums[middle] > target) {
                right = middle - 1;
            } else { // 寻找右边界，nums[middle] == target的时候middle + 1正好是右边界
                left = middle + 1;
                rightBorder = left;
            }
        }
        return rightBorder;
    }

    /**
     * 功能：获取所要查询的数的左边界，也就是示例中索引为0的位置  示例new int[]{1,2,3,5,7}, 2
     * @param nums
     * @param target
     * @return
     */
    int getLeftBorder(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int leftBorder = -2; // 记录一下leftBorder没有被赋值的情况
        while (left <= right) {
            int middle = left + ((right - left) / 2);
            if (nums[middle] >= target) { // 寻找左边界，nums[middle] == target的时候更新right
                right = middle - 1;
                leftBorder = right;
            } else {
                left = middle + 1;
            }
        }
        return leftBorder;
    }
}
