import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一个正整数数组，设为nums，最大为100个成员，求从第一个成员开始，正好走到数组最后一个成员，所使用的最少步骤数。
 * 要求：
 * 第一步必须从第一元素开始，且1<=第一步的步长<len/2;（len为数组的长度，需要自行解析）。
 * 从第二步开始，只能以所在成员的数字走相应的步数，不能多也不能少, 如果目标不可达返回-1，只输出最少的步骤数量。
 * 只能向数组的尾部走，不能往回走。
 *
 * 输入描述：
 * 由正整数组成的数组，以空格分隔，数组长度小于100，请自行解析数据数量
 *
 * 输出描述：
 * 正整数，表示最少的步数，如果不存在输出-1
 *
 */
public class 找终点 {
    /**
     * 索引其实就是步长
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int halfLen = nums.length / 2;

        int minStep =Integer.MAX_VALUE;
        for(int i=0; i<halfLen; i++){
            int step = nums[i]+i;
            int totalStep = 1;
            while (step<nums.length){
                totalStep++;
                if(step==nums.length-1){
                    minStep = Math.min(minStep, totalStep);
                }
                step = step+nums[step];
            }
        }
        System.out.println(minStep==Integer.MAX_VALUE?-1:minStep);
    }

//这个是标准解法
//        public static void main(String[] args) {
//            Scanner sc = new Scanner(System.in);
//
//            int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//
//            int minStep = Integer.MAX_VALUE;
//
//            // i 表示第一步的步长
//            for (int i = 1; i < nums.length / 2; i++) {
//                // 从索引0位置 跳到 索引i 位置需要一步
//                // 从索引i位置 跳过 索引j 位置需要一步
//                // 即：从索引0位置 跳到 索引j位置 需要两步
//                int j = i + nums[i];
//                int step = 2;
//
//                // 没有跳到最后一个位置就继续跳
//                while (j < nums.length - 1) {
//                    j += nums[j];
//                    step++;
//                }
//
//                // 如果最后可以跳到最后一个位置, 则比较保留到达终点所需的最小步数
//                if (j == nums.length - 1) {
//                    minStep = Math.min(minStep, step);
//                }
//            }
//
//            System.out.println(minStep == Integer.MAX_VALUE ? -1 : minStep);
//        }
//
}
