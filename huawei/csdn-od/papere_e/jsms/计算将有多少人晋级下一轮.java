import java.util.Scanner;
import java.util.*;
import java.util.stream.Stream;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
/**
 *
 */
public class 计算将有多少人晋级下一轮 {

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            String[] tmp2 = in.nextLine().split(" ");
            int[] nums = new int[tmp2.length];
            for (int i = 0; i < tmp2.length; i++) {
                nums[i] = Integer.parseInt(tmp2[i]);
            }
            int n = nums[0];
            int k = nums[1];

            String[] tmp1 = in.nextLine().split(" ");
            int[] score = new int[tmp1.length];
            for (int i = 0; i < tmp1.length; i++) {
                score[i] = Integer.parseInt(tmp1[i]);
            }

            int target_pos = k-1;
            while(target_pos>=0){
                if(score[target_pos]>0){
                    break;
                }
                else{
                    target_pos -= 1;
                }
            }

            int cnt = 0;
            //统计得分大于等于第k名的参赛者的人数
            for(int i=0;i<n;i++){
                if(score[i]>=score[target_pos]){
                    cnt+=1;
                } else {
                    break;
                }
            }
            System.out.println(cnt);
        }

        public int[] split(String input_str){
            String[] tmp2 = input_str.split(" ");
            int[] nums = new int[tmp2.length];
            for (int i = 0; i < tmp2.length; i++) {
                nums[i] = Integer.parseInt(tmp2[i]);
            }
            return nums;
        }


        //chatgpt答案，感觉这个才是对的
    //import java.util.Scanner;
    //
    //public class Main {
    //    public static void main(String[] args) {
    //        Scanner sc = new Scanner(System.in);
    //        // 读取参赛者数量 n 和排名位置 k
    //        int n = sc.nextInt();
    //        int k = sc.nextInt();
    //
    //        // 读取 n 个选手的分数（假设分数已经按非增序排列）
    //        int[] scores = new int[n];
    //        for (int i = 0; i < n; i++) {
    //            scores[i] = sc.nextInt();
    //        }
    //
    //        // 根据规则，第 k 名选手的分数作为晋级门槛
    //        int kthScore = scores[k - 1];
    //        int count = 0;
    //
    //        // 遍历所有选手的分数，统计符合条件的选手数：
    //        // 分数 > 0 且 分数 >= kthScore
    //        for (int i = 0; i < n; i++) {
    //            if (scores[i] >= kthScore && scores[i] > 0) {
    //                count++;
    //            }
    //        }
    //
    //        System.out.println(count);
    //        sc.close();
    //    }
    //}

}
