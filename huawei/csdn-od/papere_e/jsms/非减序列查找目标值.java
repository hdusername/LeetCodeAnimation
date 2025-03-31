/**
 * 给定一个递增 数组Q和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入
 * 的位置。
 * 示例 1:
 * 输入:nums=[1,3,5,6]，target=5
 * 输出:2
 * 示例 2:
 * 输入:nums=[1,3,5,6]，target=2
 * 输出:1
 * 示例 3:
 * 输入:nums =[1,3,5,6]，target =7
 * 输出:4
 */
public class 非减序列查找目标值 {

    public static void main(String[] args) {
        String s = getResult(new int[]{1,3,5,6}, 7);
        System.out.println(s);
    }

    private static String getResult(int[] ints, int target) {
        for(int i=0; i<ints.length; i++){
            if(ints[i]>target){
                return i+"";
            } else if (ints[i]==target) {
                return i+"";
            }else {
                continue;
            }
        }
        return ints.length+"";
    }
}

//二分法
//class Solution {
//    public int searchInsert(int[] nums, int target) {
//        int n = nums.length;
//        int left = 0, right = n - 1, ans = n;
//        //注意下面的left和right的关系以及加减规则
//        while (left <= right) {
//            int mid = ((right - left) >> 1) + left;
//            if (target <= nums[mid]) {
//                ans = mid;
//                right = mid - 1;
//            } else {
//                left = mid + 1;
//            }
//        }
//        return ans;
//    }
//}