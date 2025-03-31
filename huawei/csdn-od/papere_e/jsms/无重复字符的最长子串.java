import java.util.Calendar;
import java.util.HashSet;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class 无重复字符的最长子串 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("aab"));
    }
    public static int lengthOfLongestSubstring(String s) {

        HashSet<Character> hashSet = new HashSet<>();
        int right = 0;
        //最长子串长度
        int max_len = 0;
        //最长子串
        String sub_s = "";
        //for(int right = 0; right<s.length(); right++){
        int temp_len = 0;
        for(int i=0; i<s.length(); i++){
            if(i!=0){
                hashSet.remove(s.charAt(i-1));
            }

            while (right<s.length() && !hashSet.contains(s.charAt(right))){
                hashSet.add(s.charAt(right));
                right++;
            }
            max_len = Math.max(right-i, max_len);

//            if (!hashSet.contains(s.charAt(right))) {
//                temp_len++;
//                max_len = Math.max(max_len, temp_len);
//
//                hashSet.add(s.charAt(right));
//            }else {
////                max_len = Math.max(max_len, temp_len);
////                temp_len=0;
//
//                while (left<right && hashSet.contains(s.charAt(right))){
//                    hashSet.remove(s.charAt(left));
//                    left++;
//                }
//                temp_len=0;
//                right=left-1;
//
//            }
//            right++;

        }
        return max_len;
    }
}
