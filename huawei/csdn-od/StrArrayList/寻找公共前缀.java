/**
 * 有数组[flat,flower,flow],取这个数组中字符串前缀相同的最长字符串
 */
public class 寻找公共前缀 {
    public static void main(String[] args) {
        String[] strs = {"flat", "flower", "flow"};
        System.out.println("Longest Common Prefix: " + longestCommonPrefix(strs));
    }


    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // 取第一个字符串作为初始前缀
        String prefix = strs[0];

        // 遍历数组中的每个字符串
        for (int i = 1; i < strs.length; i++) {
            // 更新前缀，直到当前前缀不再是当前字符串的前缀为止
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }


}
