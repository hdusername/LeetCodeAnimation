public class Solution26{

    public static void main(String[] args) {

        removeDuplicates(new int[]{1,1,2});

    }


    public static int removeDuplicates(int[] nums) {
        int lowPoi = 0;
        for(int fastPoi = 1; fastPoi<nums.length;fastPoi++){
            if(nums[fastPoi]!=nums[lowPoi]){
                lowPoi++;
                nums[lowPoi] = nums[fastPoi];
            }
        }
        return lowPoi;
    }
}
