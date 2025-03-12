import java.util.Arrays;
import java.util.Scanner;

/**
 * 用数组代表每个人的能力，一个比赛活动要求参赛团队的最低能力值为N，每个团队可以由1人或者2人组成，且1个人只能参加1个团队，计算出最多可以派出多少只符合要求的团队。
 *
 * 输入描述：
 * 第一行代表总人数，范围1-500000
 * 第二行数组代表每个人的能力
 *      数组大小，范围1-500000
 *      元素取值，范围1-500000
 * 第三行数值为团队要求的最低能力值，范围1-500000
 *
 * 输出描述：
 * 最多可以派出的团队数量
 *
 * 用例输入：
 * 5
 * 3 1 5 7 9
 * 8
 *
 * 输出：
 * 3
 *
 * 解释：
 * 说明 3、5组成一队   1、7一队  9自己一队  输出3
 */
public class 求最多可以派出多少支团队 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] teamPower = new int[n];
        for(int i=0; i<n; i++){
            teamPower[i]= scanner.nextInt();
        }
        int minPower = scanner.nextInt();

        //升序排列
        Arrays.sort(teamPower);
        int ans = 0;

        int l = 0;
        int r = n-1;
        //这里为什么要用到等于，是因为有一个人一个占一个队伍的情况
        while (l<=r){
            if(teamPower[r]>=minPower){
                //最大值比需要的最小能力值大，一人就可以组成团队，满足题意
                ans++;
                r--;
                continue;
            }
            //走到这里说明需要两人组队，两人组队l!=r，注意这里l!=r是关键，否则会有案例不通过
            if(l!=r && teamPower[l]+teamPower[r]>=minPower){
                ans++;
                r--;
                l++;

            }else {
                //本题和题目租车骑绿岛的区别就在这里，如果能力不足，就要舍弃这个人，租车骑绿岛每个人都要骑上自行车
                //说明比需要的最小能力值小，l++
                l++;
            }

        }

        System.out.println(ans);

    }
}

//标准答案
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class Main {
//  public static void main(String[] args) {
//    Scanner sc = new Scanner(System.in);
//
//    int n = Integer.parseInt(sc.nextLine());
//
//    int[] capacities =
//        Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//
//    int minCap = Integer.parseInt(sc.nextLine());
//
//    System.out.println(getResult(n, capacities, minCap));
//  }
//
//  public static int getResult(int n, int[] capacities, int minCap) {
//    // 升序
//    Arrays.sort(capacities);
//
//    int l = 0;
//    int r = n - 1;
//
//    // 记录题解
//    int ans = 0;
//
//    // 单人组队
//    while (r >= l && capacities[r] >= minCap) {
//      r--;
//      ans++;
//    }
//
//    // 双人组队
//    while (l < r) {
//      int sum = capacities[l] + capacities[r];
//
//      // 如果两个人的能力值之和>=minCap，则组队
//      if (sum >= minCap) {
//        ans++;
//        l++;
//        r--;
//      } else {
//        // 否则将能力低的人剔除，换下一个能力更高的人
//        l++;
//      }
//    }
//
//    return ans;
//  }
//}