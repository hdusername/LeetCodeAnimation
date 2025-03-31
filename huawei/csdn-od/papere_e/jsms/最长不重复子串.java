import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
/**
 * 给定一个字符串 s, ，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class 最长不重复子串 {
    public static void main(String[] args) {
        // 处理输入
        Scanner in = new Scanner(System.in);
        String input_str = in.nextLine();


        System.out.println(lengthOfLongestSubstring(input_str));

    }

    //注意看要求是返回最大长度还是最长子串本身
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        // 无重复子串的右侧下标
        int right = 0;
        // 最长的长度
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; i ++) {
            // i 就是左侧下标
            if (i != 0) {
                // 当不是第一个元素时，要把之前的元素删除掉
                set.remove(s.charAt(i - 1));
            }
            while (right < n && !set.contains(s.charAt(right))) {
                // 当不存在重复字符时加入集合中
                set.add(s.charAt(right));
                // 右侧下标前进
                right ++;
            }
            // 遇见重复字符了，比较得到最长的子串长度
            ans = Math.max(ans, right - i);
        }
        return ans;
    }


}
