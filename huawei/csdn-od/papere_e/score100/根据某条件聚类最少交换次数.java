import java.util.Arrays;
import java.util.Scanner;

/**
 * 给出数字 K，请输出所有结果小于 K 的整数组合到一起的最少交换次数。
 * 组合一起是指满足条件的数字相邻，不要求相邻后在数组中的位置。
 *
 * 数据范围：
 * -100 ≤ K ≤ 100
 * -100 ≤ 数组中数值 ≤ 100
 *
 * 输入描述：
 * 第一行输入数组：1 3 1 4 0
 * 第二行输入 K 数值：2
 *
 * 输出描述：
 * 第一行输出最少交换次数：1
 */
public class 根据某条件聚类最少交换次数 {
    /**
     * 这个题目求的是所有小于k的数值组合在一起最少需要交换多少次，所以需要首先找到有多少数字小于k，以便确定窗口大小
     * 经过交换后最终得到的窗口内一定都是符合条件的数字
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arrays = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(scanner.nextLine());
        int minChangeNum = Integer.MAX_VALUE;

        long corrNum = Arrays.stream(arrays).filter(a -> a < k).count();

        for (int i = 0; i <= arrays.length - corrNum; i++) {
            int count = 0;
            for (int j = i; j < i + corrNum; j++) {
                //统计窗口区间内有多少大于等于k的，有多少大于等于k的数字就要交换几次
                if (arrays[j] >= k) {
                    count++;
                }
            }
            //得到最小的交换次数
            minChangeNum = Math.min(minChangeNum, count);
        }
        System.out.println(minChangeNum);


//
//                Scanner sc = new Scanner(System.in);
//
//                int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//                int k = sc.nextInt();
//
//                // window_len：所有小于k的元素个数
//                int window_len = 0;
//                for (int num : nums) {
//                    if (num < k) {
//                        window_len++;
//                    }
//                }
//
//                int minSwap = Integer.MAX_VALUE;
//
//                // 保证滑窗内都是小于k的元素, 所需的交换次数
//                int swap = 0;
//
//                for (int r = 0; r < nums.length; r++) {
//                    // r 是滑窗的右边界, nums[r] 是滑窗新加入的元素
//                    if (nums[r] >= k) {
//                        // 如果nums[r] >= k, 则需要被交换, 交换次数+1
//                        swap++;
//                    }
//
//                    // 当滑窗长度为 window_len + 1 时, 则滑窗左边界为 l = r - window_len
//                    // 为了保证滑窗左边界为 window_len, 则此时左边界应该被丢弃, 即 nums[r - window_len] 应该被丢弃
//                    // 如果 nums[r - window_len] >= k, 则滑窗内交换次数-1
//                    if (r >= window_len && nums[r - window_len] >= k) {
//                        swap--;
//                    }
//
//                    // 若 r < window_len - 1, 即使 l == 0, 滑窗长度也达不到 window_len
//                    // 因此只有 r >= window_len - 1 时, 滑窗长度才能到达 window_len
//                    if (r >= window_len - 1) {
//                        minSwap = Math.min(minSwap, swap);
//                    }
//                }
//
//                System.out.println(minSwap);
//
//        }
    }
}
