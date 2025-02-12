import java.util.Arrays;
import java.util.Scanner;

/**
 * 部门组织绿岛骑行团建活动。租用公共双人自行车，每辆自行车最多坐两人，最大载重M。
 * 给出部门每个人的体重，请问最多需要租用多少双人自行车。
 *
 * 输入描述：
 * 第一行两个数字 m、n，分别代表自行车限重，部门总人数。
 * 第二行，n 个数字，代表每个人的体重，体重都小于等于自行车限重 m。
 *  0 < m ≤ 200
 *  0 < n ≤ 1000000
 *
 * 输出描述：
 * 最小需要的双人自行车数量。
 *
 * 用例输入：
 * 3 4
 * 3 2 2 1
 *
 * 输出：3
 *
 */
public class 租车骑绿岛 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] input1 = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //自行车限重
        int m = input1[0];
        //部门总人数
        int n = input1[1];
        int[] weights = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(weights);

        int l = 0;
        int r = weights.length-1;

        int ans = 0;
        while (l<=r){
            if(l != r && weights[l]+weights[r]<=m){
                //两个人可以共用一辆车
                l++;
                r--;
                ans++;
            }else {
                //体重大的自己单独用一辆车
                r--;
                ans++;
            }
        }

        System.out.println(ans);


    }
}
