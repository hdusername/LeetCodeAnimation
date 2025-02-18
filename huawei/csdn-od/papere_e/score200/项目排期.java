import java.util.Arrays;
import java.util.Scanner;

/**
 * 项目组共有 N 个开发人员，项目经理接到了 M 个独立的需求，每个需求的工作量不同，且每个需求只能由一个开发人员独立完成，不能多人合作。
 * 假定各个需求直接无任何先后依赖关系，请设计算法帮助项目经理进行工作安排，使整个项目能用最少的时间交付。
 *
 * 输入描述：
 * 第一行输入为 M 个需求的工作量，单位为天，用逗号隔开。
 * 例如：
 * X1 X2 X3 ... Xm
 * 表示共有 M 个需求，每个需求的工作量分别为X1天，X2天，...，Xm天。其中：
 * 0 < M < 30
 * 0 < Xm < 200
 *
 * 第二行输入为项目组人员数量N
 * 例如：
 * 5
 * 表示共有5名员工，其中 0 < N < 10
 *
 * 输出描述：
 * 最快完成所有工作的天数
 * 例如：
 * 25
 * 表示最短需要25天完成所有工作
 */
public class 项目排期 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer[] tasks = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

        int workerNum = Integer.parseInt(scanner.nextLine());

        Arrays.sort(tasks, (a,b)->b-a);
        Integer sum = Arrays.stream(tasks).reduce(Integer::sum).get();

        //初始化每个人最多干的天数和最少干的天数
        int minDay = 1;
        int maxDay = sum;
        int ans = sum;
        int[] budgets = new int[workerNum];;
        while (minDay<=maxDay){
            //存储每个人干多少天，尽量要将任务天数平均分配到这个数组中
            budgets = new int[workerNum];
            int middleDay = (minDay+maxDay)>>1;

            if(check(tasks, 0, budgets, middleDay)){
                maxDay = middleDay-1;
                ans = middleDay;

            }else {
                minDay = middleDay+1;
            }
        }



        System.out.println(ans);
    }

    private static boolean check(Integer[] tasks, int idx, int[] budgets, int middleDay){
        if(idx == tasks.length){
            return true;
        }
        int select = tasks[idx];

        for(int i=0; i<budgets.length; i++){
            if(i>0 && budgets[i]==budgets[i-1]){
                continue;
            }
            if(budgets[i]+select<=middleDay){
                budgets[i]+=select;
                if(check(tasks, idx+1, budgets, middleDay)){
                    return true;
                }else {
                    budgets[i]-=select;
                }
            }
        }

        return false;
    }

    //标准答案
//
//        static Integer[] balls;
//        static int n;
//
//        public static void main(String[] args) {
//
//            Scanner sc = new Scanner(System.in);
//
//            balls = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
//            n = Integer.parseInt(sc.nextLine());
//
//            System.out.println(getResult());
//        }
//
//        public static int getResult() {
//            // 这里对balls降序，有利于降低后面回溯操作的复杂度
//            Arrays.sort(balls, (a, b) -> b - a);
//
//            // 二分范围：即每个桶的容量最小，最大值
//            int min = balls[0]; // 桶至少要有max(balls)的容量
//            int max = Arrays.stream(balls).reduce(Integer::sum).get(); // 当只有一个桶时，此时该桶容量要装下所有balls
//
//            // 记录题解
//            int ans = max;
//
//            // 二分找中间值作为桶容量
//            while (min <= max) {
//                int mid = (min + max) >> 1;
//
//                if (check(0, new int[n], mid)) {
//                    // 如果k个mid容量的桶，可以装完所有balls，那么mid容量就是一个可能解，但不一定是最优解，我们应该尝试更小的桶容量
//                    ans = mid;
//                    max = mid - 1;
//                } else {
//                    // 如果k个mid容量的桶，无法装完所有balls，那么说明桶容量取小了，我们应该尝试更大的桶容量
//                    min = mid + 1;
//                }
//            }
//
//            return ans;
//        }
//
//        /**
//         * @param index 当前轮次要被装入的球的索引（balls数组索引）
//         * @param buckets 桶数组，buckets[i]记录的是第 i 个桶已使用的容量
//         * @param limit 每个桶的最大可使用容量
//         * @return k个桶（每个桶容量limit）是否可以装下balls中所有球
//         */
//        public static boolean check(int index, int[] buckets, int limit) {
//            // 如果balls已经取完，则说明k个limit容量的桶，可以装完所有balls
//            if (index == balls.length) return true;
//
//            // select是当前要装的球
//            int selected = balls[index];
//
//            // 遍历桶
//            for (int i = 0; i < buckets.length; i++) {
//                // 剪枝优化
//                if (i > 0 && buckets[i] == buckets[i - 1]) continue;
//
//                // 如果当前桶装了当前选择的球后不超过容量限制，则可以装入
//                if (selected + buckets[i] <= limit) {
//                    buckets[i] += selected;
//                    // 递归装下一个球
//                    if (check(index + 1, buckets, limit)) return true;
//                    // 如果这种策略无法装完所有球，则回溯
//                    buckets[i] -= selected;
//                }
//            }
//
//            return false;
//        }
//
}
