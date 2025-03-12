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
        //这里为什么要用到等于，是因为有一个人一个占一个自行车的情况
        while (l<=r){
            //本题与求最多可以派出多少支团队的区别在于本题需要将所有人都安排到自行车上，而求最多可以派出多少支团队是如果能力不足需要舍弃这个人
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

//标准答案
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        int m = sc.nextInt();
//        int n = sc.nextInt();
//
//        int[] weights = new int[n];
//        for (int i = 0; i < n; i++) {
//            weights[i] = sc.nextInt();
//        }
//
//        Arrays.sort(weights);
//
//        int count = 0;
//
//        int i = 0;
//        int j = n - 1;
//
//        while (i <= j) {
//            if (weights[i] + weights[j] <= m) i++;
//            j--;
//            count++;
//        }
//
//        System.out.println(count);
//    }
//}
