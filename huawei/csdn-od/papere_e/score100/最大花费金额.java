import java.util.Arrays;
import java.util.Scanner;

/**
 * 双十一众多商品进行打折销售，小明想购买自己心仪的一些物品，但由于受购买资金限制，所以他决定从众多心仪商品中购买三件，而且想尽可能的花完资金。
 * 现在请你设计一个程序帮助小明计算尽可能花费的最大资金数额。
 *
 * 输入描述：
 * 输入第一行为一维整型数组M，数组长度小于100，数组元素记录单个商品的价格，单个商品价格小于1000。
 * 输入第二行为购买资金的额度R，R小于100000。
 * 输入格式是正确的，无需考虑格式错误的情况。
 *
 * 输出描述：
 * 输出为满足上述条件的最大花费额度。
 * 如果不存在满足上述条件的商品，请返回-1。
 *
 * 输入：
 * 23,26,36,27
 * 78
 *
 * 输出
 * 76
 *
 * 解释
 * 金额23、26和27相加得到76，而且最接近且小于输入金额78。
 */
public class 最大花费金额 {
    //这个逻辑不太对，使用下面的solution方法
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arrays = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int target = Integer.parseInt(scanner.nextLine());

        //排序，方便后续进行指针移动
        Arrays.sort(arrays);

        //定义三个指针
        int start = 0;
        int middle = 1;
        int end = arrays.length-1;

        int result = 0;

        while(start<end){
            middle=start+1;
            while (middle>start && middle<end) {
                int sum = arrays[start] + arrays[middle] + arrays[end];
                if (sum <= target) {
                    if (Math.abs(sum - target) < Math.abs(result - target)) {
                        result = sum;

                    }
                    middle++;

                } else {
                   end--;
                   start--;
                   break;
                }

            }
            start++;
        }
        System.out.print(result==0?-1:result);
    }

    //todo: 应该使用如下代码，这也是滑动串口的解法

//        public static int solution(int[] nums, int target) {
//            // 题目说小明要购买三件，如果商品不足三件直接返回-1
//            if (nums.length < 3) return -1;
//
//            // 数组升序
//            Arrays.sort(nums);
//
//            int ans = -1;
//
//            for (int i = 0; i < nums.length; i++) {
//                int l = i + 1;
//                int r = nums.length - 1;
//
//                while (l < r) {
//                    int sum = nums[i] + nums[l] + nums[r];
//                    if (sum == target) {
//                        return sum;
//                    } else if (sum < target) {
//                        ans = Math.max(ans, sum);
//                        l++;
//                    } else {
//                        r--;
//                    }
//                }
//            }
//
//            return ans;
//        }

}
