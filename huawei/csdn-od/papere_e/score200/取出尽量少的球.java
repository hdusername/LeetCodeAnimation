import java.util.Scanner;
import java.util.StringJoiner;
/**
 * 某部门开展FamilyDay开放日活动，其中有个从桶里取球的游戏，游戏规则如下:
 * 有N个容量一样的小桶等距排开，且每个小桶都默认装了数量不等的小球
 * 每个小桶装的小球数量记录在数组bucketBallNums中
 * 游戏开始时，要求所有桶的小球总数不能超过SUM，
 * 如果小球总数超过SUM，则需对所有的小桶统一设置一个容量最大值maxCapacity并需将超过容量最大值的小球拿出来，直至小桶里的小球数量小于maxCapacity;
 * 请您根据输入的数据，计算从每个小桶里拿出的小球数量。
 *
 * 限制规则一:
 * 所有小桶的小球总和小于SUM，则无需设置容量值maxCapacity，并且无需从小桶中拿球出来，返回结果[]
 *
 * 限制规则二:
 * 如果所有小桶的小球总和大于SUM，则需设置容量最大值maxCapacity，并且需以小中拿球出来，返回从每小出的小球教量组成的数组;
 *
 * 输入描述
 * 第一行输入2个正整数，数字之间使用空格隔开，其中第一个数字表示SUM，第二个数字表示bucketBallNums数组长度:
 * 第二行输入N个正整数，数字之间使用空格隔开，表示bucketBallNums的每一项;
 *
 * 输出描述
 * 找到一个maxCapacity，来保证取出尽量少的球，并返回从每个小桶拿出的小球数量组成的数组.
 *
 * 用例输入：
 * 14 7
 * 2 3 2 5 5 1 4
 *
 * 输出：
 * [0, 1, 0, 3, 3, 0, 2]
 *
 * 解释：
 * 小球总数为22，SUM=14，超出范围了，需从小桶取球maxCapacity=1，取出球后，桶里剩余小球总和为7，远小于14maxCapacity=2，取出球后，桶里剩余小球总和为13，
 * maxCapacity=3，取出球后，桶里剩余小球总和为16，大于14因此maxCapacity为2，每个小桶小球数量大于2的都需要拿出来;
 */
public class 取出尽量少的球 {

    //标准答案
        public static int sum, n;
        public static int[] count;

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            sum = sc.nextInt();
            n = sc.nextInt();

            count = new int[n];
            for (int i = 0; i < n; i++) {
                count[i] = sc.nextInt();
            }

            System.out.println(solution());
        }

        public static String solution() {
            int count_max = 0;
            long count_sum = 0;

            for (int i = 0; i < n; i++) {
                count_max = Math.max(count_max, count[i]);
                count_sum += count[i];
            }

            // 所有开发的需求总和小于 SUM，则无需设置限额 maxCapacity，并且无需从开发中分需求出来，返回结果[]
            if (count_sum <= sum) {
                return "[]";
            }

            // 二分范围
            int min = 0;
            int max = count_max;

            // 记录最优解
            int limit = 0;

            // 二分
            while (min <= max) {
                int mid = (max - min) / 2 + min;

                // 限制每个开发mid容量，看减负后剩余需求总和是否小于等于sum
                if (check(mid)) {
                    // 若是，则mid是一个可能解，但不一定是最优解
                    limit = mid;
                    // 继续尝试更大限制，即提高下限
                    min = mid + 1;
                } else {
                    // 若不是，则mid限制下，所有开发剩余需求数量任然大于sum，因此应该尝试更小限制，即压低上限
                    max = mid - 1;
                }
            }

            StringJoiner sj = new StringJoiner(",", "[", "]");

            for (int i = 0; i < n; i++) {
                // 取出超出部分后，各个开发中剩余需求数量
                count[i] -= Math.min(count[i], limit);
                sj.add(count[i] + "");
            }

            return sj.toString();
        }

        public static boolean check(int maxCapacity) {
            long remain = 0;

            for (int i = 0; i < n; i++) {
                // 若开发中需求数量超出 maxCapacity，则只能保留 maxCapacity 个
                remain += Math.min(count[i], maxCapacity);
            }

            return remain <= sum;
        }

}
