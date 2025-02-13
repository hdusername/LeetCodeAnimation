import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 任务编排服务负责对任务进行组合调度。
 * 参与编排的任务有两种类型，其中一种执行时长为taskA，另一种执行时长为taskB。
 * 任务一旦开始执行不能被打断，且任务可连续执行。
 * 服务每次可以编排num个任务。
 * 请编写一个方法，生成每次编排后的任务所有可能的总执行时长。
 *
 * 输入描述：
 * 第1行输入分别为
 * 第1种任务执行时长taskA
 * 第2种任务执行时长taskB
 * 这次要编排的任务个数num
 * 以逗号分隔。
 *
 * 输出描述：
 * 数组形式返回所有总执行时时长，需要按从小到大排列。
 *
 * 备注：
 * 注：每种任务的数量都大于本次可以编排的任务数量
 * 0 < taskA
 * 0 < taskB
 * 0 ≤ num ≤ 100000
 *
 * 用例输入：
 * 1,2,3
 *
 * 输出：
 * [3, 4, 5, 6]
 *
 * 解释：
 * 可以执行 3 次 taskA，得到结果 3；执行 2 次 taskA和 1 次 taskB，得到结果 4 。以此类推，得到最终结果。
 */
public class 任务总执行时长 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] s = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int taskATime = s[0];
        int taskBTime = s[1];
        int num = s[2];

        if(num==0){
            System.out.println("[]");
            return;
        }
        HashSet<Integer> ans = new HashSet<>();
        for(int i=0; i<=num; i++){
            int taskANum = i;
            int taskBNum = num-i;

            ans.add(taskATime*taskANum+taskBTime*taskBNum);

        }
        ArrayList<Integer> outList = new ArrayList<>(ans);
        outList.sort((a, b)->a-b);

        System.out.println(outList);
    }
}
