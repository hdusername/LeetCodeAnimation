/**
 * 编写一个 函数只 来 查找字符串9 数组中的最长公共前缀,如果不存在公共前缀，返回空字符串示例 1:
 * 输入:strs =["flower","flow","flight"]输出:"f1”
 * 示例 2:
 * 输入:strs =["dog","racecar","car"]
 * 输出:
 * 10 1O
 * 解释:输入不存在公共前缀，
 */
public class 最长公共前缀 {


    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    public String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

}
