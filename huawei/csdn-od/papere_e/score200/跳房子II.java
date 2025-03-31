import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;
/**
 * 跳房子，也叫跳飞机，是一种世界性的儿童游戏。
 * 游戏参与者需要分多个回合按顺序跳到第1格直到房子的最后一格，然后获得一次选房子的机会，直到所有房子被选完，房子最多的人获胜。
 * 跳房子的过程中，如果有踩线等违规行为，会结束当前回合，甚至可能倒退几步。假设房子的总格数是count，小红每回合可能连续跳的步数都放在数组steps中，
 * 请问数组中是否有一种步数的组合，可以让小红三个回合跳到最后一格?
 * 如果有，请输出索引和最小的步数组合(数据保证索引和最小的步数组合是唯一的)。
 * 注意:数组中的步数可以重复，但数组中的元素不能重复使用。
 *
 * 输入描述
 * 第一行输入为房子总格数count，它是int整数类型
 * 第二行输入为每回合可能连续跳的步数，它是int整数数组类型
 *
 * 输出描述
 * 返回索引和最小的满足要求的步数组合 (顺序保持 steps中原有顺序)
 *
 * 备注
 * -count<=10000-3<=steps.length<=10000- -100000<=steps[i]<=100000
 *
 * 用例输入
 *
 * [1,4,5,2,0,2]
 * 9
 *
 * 输出
 * [4,5,0]
 */
public class 跳房子II {

        static class Step {
            int val;
            int idx;

            public Step(int val, int idx) {
                this.idx = idx;
                this.val = val;
            }
        }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            String tmp = sc.nextLine();
            int[] steps =
                    Arrays.stream(tmp.substring(1, tmp.length() - 1).split(","))
                            .mapToInt(Integer::parseInt)
                            .toArray();

            int count = Integer.parseInt(sc.nextLine());

            System.out.println(getResult(steps, count));
        }

        public static String getResult(int[] steps, int count) {
            int n = steps.length;

            Step[] newSteps = new Step[n];
            for (int i = 0; i < n; i++) {
                newSteps[i] = new Step(steps[i], i);
            }

            //步数相同的索引小的在前面
            Arrays.sort(newSteps, (a, b) -> a.val != b.val ? a.val - b.val : a.idx - b.idx);

            int minStepIdxSum = Integer.MAX_VALUE;
            String ans = "";

            for (int i = 0; i < n; i++) {
                // 剪枝优化，因为newSteps是递增的，如果到某一个值，这个值就比count大了，那么再加上后面两步的步数肯定更不符合要求了，再后面的值肯定也不符合要求
                //直接跳出即可
                if (newSteps[i].val > count && newSteps[i].val > 0 && count > 0) break;

                // 剪枝优化
                if (i > 0 && newSteps[i].val == newSteps[i - 1].val) continue;

                int l = i + 1;
                int r = n - 1;

                while (l < r) {
                    // 剪枝优化，L,R指针指向值的目标和为count - i指针指向的值，而L指针指向的值 必然小于等于 R指针指向的值，
                    // 因此L指针指向的值必然 <= 目标和/2，而R指针指向的值必然 >= 目标和/2
                    int threshold = (count - newSteps[i].val) / 2;
                    if (newSteps[l].val > threshold || newSteps[r].val < threshold) break;

                    int stepValSum = newSteps[i].val + newSteps[l].val + newSteps[r].val;

                    if (stepValSum < count) {
                        l++;
                    } else if (stepValSum > count) {
                        r--;
                    } else {
                        // 剪枝优化
                        while (l < r - 1 && newSteps[r].val == newSteps[r - 1].val) {
                            r--;
                        }

                        int stepIdxSum = newSteps[i].idx + newSteps[l].idx + newSteps[r].idx;
                        if (stepIdxSum < minStepIdxSum) {
                            minStepIdxSum = stepIdxSum;

                            Step[] arr = {newSteps[i], newSteps[l], newSteps[r]};
                            Arrays.sort(arr, (a, b) -> a.idx - b.idx);

                            StringJoiner sj = new StringJoiner(",", "[", "]");
                            for (Step step : arr) sj.add(step.val + "");

                            ans = sj.toString();
                        }

                        // 剪枝优化
                        while (l + 1 < r && newSteps[l].val == newSteps[l + 1].val) {
                            l++;
                        }

                        l++;
                        r--;
                    }
                }
            }
            return ans;
        }

}
