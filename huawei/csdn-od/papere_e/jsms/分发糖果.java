import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 *
 * 你需要按照以下要求，给这些孩子分发糖果：
 *
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：ratings = [1,0,2]
 * 输出：5
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
 * 示例 2：
 *
 * 输入：ratings = [1,2,2]
 * 输出：4
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
 *
 *
 * 提示：
 *
 * n == ratings.length
 * 1 <= n <= 2 * 104
 * 0 <= ratings[i] <= 2 * 104
 */
public class 分发糖果 {
    public static void main(String[] args) {
//        LinkedList<String> linkedList = new LinkedList<>();
//        linkedList.add("a");
//        linkedList.add("b");
//        linkedList.add("c");


        Deque<String> linkedList = new LinkedList<>();
        linkedList.push("a");
        linkedList.push("b");
        linkedList.push("c");
        for(String s : linkedList){
            System.out.println(s);
        }
    }

    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        int right = 0, ret = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                right++;
            } else {
                right = 1;
            }
            ret += Math.max(left[i], right);
        }
        return ret;
    }


    public int candyTest(int[] ratings) {
        int[] candys = new int[ratings.length];

        for(int i=0;i<ratings.length; i++){
            if(i>0 && ratings[i]>ratings[i-1]){
                candys[i]=candys[i-1]+1;

            }else {
                candys[i]=1;
            }
        }

        int right = 0;
        int candyNum = 0;
        for(int i=ratings.length-1; i>=0; i--){
            if(i<ratings.length-1 && ratings[i]>ratings[i+1]){
                right++;
            }else {
                right=1;
            }

            candyNum += Math.max(right, candys[i]);
        }

        return candyNum;
    }
}
