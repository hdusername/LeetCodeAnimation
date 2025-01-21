import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一个整型数组，请从该数组中选择3个元素组成最小数字并输出（如果数组长度小于3，则选择数组中所有元素来组成最小数字）。
 *
 * 输入描述：
 * 一行用半角逗号分割的字符串记录的整型数组
 * 0 < 数组长度 ≤ 100
 * 0 < 整数的取值范围 ≤ 10000
 *
 * 输出描述：
 * 由3个元素组成的最小数字，如果数组长度小于3，则选择数组中所有元素来组成最小数字。
 */
public class 数组组成的最小数字 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        int[] split = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        String[] split = scanner.nextLine().split(",");

        StringBuffer stringBuffer = new StringBuffer();
        //先按照自然顺序排序，肯定也是小数组合起来小于大数组合起来的
        split = Arrays.stream(split).mapToInt(Integer::parseInt).sorted().mapToObj(String::valueOf).limit(3).toArray(String[]::new);

        Arrays.stream(split).sorted((a, b)-> {

            if(Integer.parseInt(a+b)>Integer.parseInt(b+a)){
                return Integer.parseInt(a)-Integer.parseInt(b);
            }else {
                return Integer.parseInt(b)-Integer.parseInt(a);
            }
        }).limit(3).forEach(a->stringBuffer.append(a));

        System.out.println(stringBuffer);
    }


//官方解法，比较简单
//        public static void main(String[] args) {
//            Scanner sc = new Scanner(System.in);
//
//            String[] nums = sc.nextLine().split(",");
//            Arrays.sort(nums, (a, b) -> Integer.parseInt(a) - Integer.parseInt(b));
//
//            nums = Arrays.copyOfRange(nums, 0, Math.min(3, nums.length));
//            Arrays.sort(nums, (a, b) -> (a + b).compareTo(b + a));
//
//            for (String num : nums) {
//                System.out.print(num);
//            }
//        }

}
