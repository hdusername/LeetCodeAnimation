import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 */
public class Solution904 {

    public static void main(String[] args) {
        totalFruit(new int[]{0,1,2,2});
    }


    public static int totalFruit(int[] fruits) {
        //这个是自己写的版本
        int left = 0;
        //int right = 0;

        int length = fruits.length;
        int ans = 0;
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();

        for(int right =0; right < length; right++){
            map.put(fruits[right],map.getOrDefault(fruits[right],0)+1);

            if(map.size()>2){
                while (map.keySet().size()>2){
                    map.put(fruits[left],map.get(fruits[left])-1);
                    if(map.get(fruits[left])==0){
                        map.remove(fruits[left]);
                    }
                    left++;
                }
            }
            ans = Math.max(right-left+1, ans);
        }


        return ans;

        //这个是官网的版本
//        int n = fruits.length;
//        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
//
//        int left = 0, ans = 0;
//        for (int right = 0; right < n; ++right) {
//            //这里给不同种类的水果增加计数
//            cnt.put(fruits[right], cnt.getOrDefault(fruits[right], 0) + 1);
//            //增加到超过2种就删除最前面的一种水果
//            while (cnt.size() > 2) {
//                cnt.put(fruits[left], cnt.get(fruits[left]) - 1);
//                if (cnt.get(fruits[left]) == 0) {
//                    cnt.remove(fruits[left]);
//                }
//                ++left;
//            }
//            //到这里只剩下了两种水果，最后减去最前加一就是两种种类的水果数量
//            ans = Math.max(ans, right - left + 1);
//        }
//        return ans;

    }
}
