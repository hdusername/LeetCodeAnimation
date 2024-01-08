public class Solution844 {

    public static void main(String[] args) {

       backspaceCompare("bxj##tw","bxj###tw");

    }


    /**
     * 给定 s 和 t 两个字符串，当它们分别被输入到空白的文本编辑器后，如果两者相等，返回 true 。# 代表退格字符。
     *
     * 注意：如果对空文本输入退格字符，文本继续为空。
     * @param s
     * @param t
     * @return
     */
    public static boolean backspaceCompare(String s, String t) {
        int skipS = 0;
        int skipT = 0;

        int sLen = s.length() - 1;
        int tLen = t.length() - 1;

        while (sLen >= 0 || tLen >= 0) {
            //以此寻找经过退格之后到达的那一个字符,找到之后就break
            while (sLen >= 0) {

                if (s.charAt(sLen) == '#') {
                    skipS++;
                    //这里--之后到了要删除的那个字母那里，本while循环中的else if中还有--操作
                    //会跳过这个字符
                    sLen--;
                } else if (skipS > 0) {
                    skipS--;
                    sLen--;
                } else {
                    break;
                }
            }

            while (tLen >= 0) {

                if (t.charAt(tLen) == '#') {
                    skipT++;
                    //这里--之后到了要删除的那个字母那里，本while循环中的else if中还有--操作
                    //会跳过这个字符
                    tLen--;
                } else if (skipT > 0) {
                    skipT--;
                    tLen--;
                } else {
                    break;
                }
            }

            //找到符合条件的记录在这里比较
            //这个if比较的是数组不越界的情况
            if (sLen >= 0 && tLen >= 0) {

                if (s.charAt(sLen) != t.charAt(tLen)) {
                    return false;
                }
            } else {

            }

            sLen--;
            tLen--;
        }
        return true;
    }
}
