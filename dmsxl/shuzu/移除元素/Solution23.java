public class Solution23 {
    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     * @param args
     */
    public static void main(String[] args) {

        int[] ints = {0, 1, 0, 3, 12};
        moveZeroes(ints);
        System.out.println(ints);
    }
    public static void moveZeroes(int[] nums) {

        //慢指针指向0，快指针指到不是0的数字时与慢指针换位置
        int numsLength = nums.length ;
        int lowPoi = 0;
        for(int fastPoi=0;fastPoi<numsLength;fastPoi++){
            if(nums[fastPoi]!=0){
                nums[lowPoi]=nums[fastPoi];
                lowPoi++;
            }
        }
    }
}
