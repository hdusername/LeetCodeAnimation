import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 在星球争霸篮球赛对抗赛中，最大的宇宙战队希望每个人都能拿到 MVP，MVP 的条件是单场最高分得分获得者。
 * 可以并列所以宇宙战队决定在比赛中尽可能让更多队员上场，并且让所有得分的选手得分都相同，然而比赛过程中的每一分钟的得分都只能由某一个人包揽。
 *
 * 输入描述：
 * 输入第一行为一个数字 t ，表示为有得分的分钟数
 * 1 ≤ t ≤ 50
 * 第二行为 t 个数字，代表每一分钟的得分 p
 * 1 ≤ p ≤ 50
 *
 * 输出描述：
 * 输出有得分的队员都是 MVP 时，最少得 MVP 得分。
 *
 * 样例：
 * 输入
 * 9
 * 5 2 1 5 2 1 5 2 1
 *
 * 输出
 * 6
 *
 * 解释：
 * 样例解释 一共 4 人得分，分别都是 6 分 5 + 1 ， 5 + 1 ， 5 + 1 ， 2 + 2 + 2
 */

public class MVP争夺战 {
    /**
     * 此题属于k个相等子集的问题，使用回溯法求解
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int sum = 0;

        LinkedList<Integer> linkedList = new LinkedList<>();
        for(int i=0; i<t; i++){
            int score = scanner.nextInt();
            linkedList.add(score);
            sum+=score;

        }

        //降序排列
        //这个排序很重要，除了因为可以判断subTeamSum<linkedList.get(0)这种，还可以在回溯时，把大数字先占上桶，然后会减少递归的次数
        linkedList.sort((a, b)-> b-a);

        while(t>0){
            if(getTeam(linkedList, t, sum)){
                //从大到小遍历，保证了分成最多队伍时返回
                System.out.print(sum/t);
                return;
            }
            t--;
        }
        //如果不能分成多个队伍，那么就只能组成一个队伍
        System.out.print(sum);
    }

    private static boolean getTeam(LinkedList<Integer> linkedList, int t, int sum) {
        //不能正好分成t个队伍
        if(sum%t!=0){
            return false;
        }
        //每个队伍得分都相同，都是这个分数
        int subTeamSum = sum/t;
        //要注意队伍总得分不能小于最大得分，比如100，1，1，分成两个队伍，显然是无法分配的
        if(subTeamSum<linkedList.get(0)){
            return false;
        }
        //去掉队伍得分等于单个得分的情况，这样在回溯时可以少进行比较
        while (linkedList.size() !=0 && linkedList.peekFirst()==subTeamSum){

            linkedList.pollFirst();
            //队伍数量也相应减少
            t--;

        }
        //创建桶，存放各个队伍的分数
        int[] budgets = new int[t];
        return partition(linkedList, 0, budgets, subTeamSum);
    }

    private static boolean partition(LinkedList<Integer> linkedList, int idx, int[] budgets, int subTeamSum) {
        //递归方法先判断出口
        if(idx==linkedList.size()){
            return true;
        }

        int select = linkedList.get(idx);

        for(int i=0; i<budgets.length; i++){
            if(i>0 && budgets[i-1]==budgets[i]){
                //避免重复判断，因为在回溯后，如果budgets[i-1]==budgets[i]，那么select放在哪个桶里结果都是一样的
                continue;
            }
            if(budgets[i]+select<=subTeamSum){
                budgets[i]+=select;
                if( partition(linkedList, idx+1, budgets, subTeamSum)){
                    return true;
                }else {
                    budgets[i]-=select;
                }

            }
        }
        return false;
    }
}

//标准答案
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        int t = sc.nextInt();
//
//        Integer[] nums = new Integer[t];
//        int sum = 0; // 总分
//
//        for (int i = 0; i < t; i++) {
//            nums[i] = sc.nextInt();
//            sum += nums[i];
//        }
//
//        // 降序
//        Arrays.sort(nums, (a, b) -> b - a);
//
//        // 平分人数k，k越大，每个人得分越少
//        for (int k = t; k >= 1; k--) {
//            // 总分可以平分为 k 份
//            if (sum % k == 0) {
//                int subSum = sum / k;
//
//                // k个桶（每个桶记录每个队员拿到的分数，每个队员都需要拿 subSum 分，不能多也不能少
//                int[] buckets = new int[k];
//
//                // 若 nums 所有元素都可以放到 k 个桶，且每个桶都能装满
//                if (partition(0, nums, buckets, subSum)) {
//                    System.out.println(subSum);
//                    return;
//                }
//            }
//        }
//    }
//
//    public static boolean partition(int index, Integer[] nums, int[] buckets, int subSum) {
//        if (index == nums.length) return true;
//
//        int select = nums[index];
//
//        for (int i = 0; i < buckets.length; i++) {
//            if (i > 0 && buckets[i] == buckets[i - 1]) continue;
//
//            if (select + buckets[i] <= subSum) {
//                buckets[i] += select;
//                if (partition(index + 1, nums, buckets, subSum)) return true;
//                buckets[i] -= select;
//            }
//        }
//
//        return false;
//    }
//}
