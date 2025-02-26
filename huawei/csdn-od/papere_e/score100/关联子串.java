import java.util.Scanner;

/**
 * 给定两个字符串str1和str2，如果字符串str1中的字符，经过排列组合后的字符串中，只要有一个字符串是str2的子串，则认为str1是str2的关联子串。
 * 若str1是str2的关联子串，请返回子串在str2的起始位置；
 * 若不是关联子串，则返回-1。
 *
 * 输入描述：
 * 输入两个字符串，分别为题目中描述的str1、str2。
 *
 * 输出描述：
 * 若str1是str2的关联子串，请返回子串在str2的起始位置；
 * 若不是关联子串，则返回-1。
 * 若str2中有多个str1的组合子串，请返回最小的起始位置。
 *
 * 备注：
 * 输入的字符串只包含小写字母；
 * 两个字符串的长度范围[1, 100000]之间；
 */
public class 关联子串 {

    /**
     * 恢复数字序列可以使用数组是否相同来比较数字数量是否满足要求，但是本题不可以，因为本题存的是字母，数字可以是因为数字少，只有10个，比较数量是否相同好进行比较
     * @param args
     */
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String[] strs = scanner.nextLine().split(" ");
//
//        System.out.println(minWindow(strs[1], strs[0]));
//    }
//

    /**
     * 功能：这个算法是通用算法，计算最小覆盖子串的方法，但是这个方法不适合这个题目的求解，因为题意中的意思是两个匹配的字符串长度要一样，而最小覆盖子串的经典解法不适合，因为
     * 这个解法是没有长度要求的
     * @param s
     * @param t
     * @return
     */
//    public static String minWindow(String s, String t) {
//        int sLen = s.length();
//        int tLen = t.length();
//
//        if (sLen == 0 || tLen == 0 || sLen < tLen) {
//            return "";
//        }
//        char[] charArrayS = s.toCharArray();
//        char[] charArrayT = t.toCharArray();
//        //保存s中字符的数量
//        int[] winFreq = new int[128];
//        //保存t中字符的数量
//        int[] tFreq = new int[128];
//        for (char c : charArrayT) {
//            tFreq[c]++;
//        }
//
//        //滑动窗口内部包含多少T中的字符，对应字符频数超过不重复计算
//        int distance = 0;
//        //因为要比较出最小值，这里赋值一个大数字不可能达到的即可，赋值为Integer.MAX_VALUE也可以
//        int minLen = sLen + 1;
//        int begin = 0;
//
//        int left = 0;
//        int right = 0;
//
//        while (right < sLen) {
//            if (tFreq[charArrayS[right]] == 0) {
//                right++;
//                continue;
//            }
//            if (winFreq[charArrayS[right]] < tFreq[charArrayS[right]]) {
//                distance++;
//            }
//            winFreq[charArrayS[right]]++;
//            right++;
//
//            while (distance == tLen) {
//                if (right - left < minLen) {
//                    minLen = right - left;
//                    begin = left;
//                }
//
//                if (tFreq[charArrayS[left]] == 0) {
//                    left++;
//                    continue;
//                }
//
//                if (winFreq[charArrayS[left]] == tFreq[charArrayS[left]]) {
//                    distance--;
//                }
//                winFreq[charArrayS[left]]--;
//                left++;
//            }
//        }
//        if (minLen == sLen + 1) {
//            return "-1";
//        }
//        return begin+"";
//    }


        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            String str1 = sc.next();
            String str2 = sc.next();

            System.out.println(getResult(str1, str2));
        }

        public static int getResult(String str1, String str2) {
            // count用于统计str1中各字符的数量
            int[] count = new int[128];
            for (int i = 0; i < str1.length(); i++) {
                char c = str1.charAt(i);
                count[c]++;
            }

            // total统计str1总共字符个数，可以理解成str1多于str2的字符数量
            int total = str1.length();

            // 初始滑窗，滑窗范围内容，就是一个子串
            for (int i = 0; i < str1.length(); i++) {
                // 滑窗子串新增的字符
                char add = str2.charAt(i);

                // 如果新增字符是str1的字符，即count[add] > 0时，则说明滑窗子串已找到一个目标字符，此时剩余add字符数量count[add]--,剩余目标字符总数total--
                if (count[add]-- > 0) {
                    total--;
                }
            }

            // 如果total == 0，则说明滑窗内所有字符都是str1内的字符，由于限定了滑窗的长度就是str1的长度，因此滑窗内字符和str1完全匹配
            if (total == 0) return 0;

            // 下一个滑窗范围是 i ~ i + str1.length() - 1
            for (int i = 1; i + str1.length() - 1 < str2.length(); i++) {
                // 相较于上一个滑窗失去的字符remove
                char remove = str2.charAt(i - 1);
                // 相较于上一个滑窗新增的字符add
                char add = str2.charAt(i + str1.length() - 1);

                // 本轮滑窗remove字符，在上一轮是被add的字符，我们假设此字符为c，此时可以分两种情况讨论：
                // 1、c是str1的字符，则初始统计时count[c]>0，上一轮滑窗加入c字符，则count[c]--，此轮count[c]是有可能>=0或者<0的，
                //    1.1、如果本轮count[c]>=0，则说明上轮滑窗并没有找到所有的c字符，因此本轮移除的c字符可以还原total数量
                //
                // 1.2、如果本轮count[c]<0，这说明上轮滑窗内的c字符超标了，即c字符是目标字符，但是滑窗内包含的c字符数量超过了str1中c字符的数量，因此本轮移除c字符是超标部分，不会还原total
                // 2、c不是str1的字符，则初始统计时count[c]==0，上一轮滑窗加入c字符，则count[c]--，此轮必然count[c]<0，因此c字符的移除，并不会还原total
                if (count[remove]++ >= 0) {
                    total++;
                }

                if (count[add]-- > 0) {
                    total--;
                }

                if (total == 0) {
                    return i;
                }
            }

            return -1;
        }

}
