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
                //说明比需要的最小能力值小，l++
                l++;
            }

        }

        System.out.println(ans);

    }
}
