import java.util.Scanner;
import java.util.HashMap;
/**
 * 给定 [a-z]，26个英文字母小写字符串组成的字符串 A 和 B，其中 A 可能存在重复字母，B 不会存在重复字母，现从字符串 A 中按规则挑选一些字母，可以组成字符串B。
 * 挑选规则如下：
 * 同一个位置的字母只能挑选一次
 * 被挑选字母的相对先后顺序不能被改变
 * 求最多可以同时从 A 中挑选多少组能组成 B 的字符串。
 *
 * 输入描述：
 * 输入为 2 行，第 1 行输入字符串 A，第 2 行输入字符串 B，行首行尾没有多余空格，其中：
 * A、B 均由 [a-z] 26个英文小写字母组成
 * 0 < A.length < 100，A 中可能包含重复字母
 * 0 < B.length < 10，B 中不会出现重复字母
 *
 * 输出描述：
 * 输出 1 行，包含 1 个数字，表示最多可以同时从 A 中挑选多少组能组成 B 的字符串
 * 行末没有多余空格
 *
 * 备注：
 * 无需验证输入格式和输入数据合法性
 *
 * 用例输入：
 * badc
 * bac
 *
 * 输出：
 * 1
 *
 * 解释：
 * 从字符串A（"badc"）中可以按字母相对先后顺序取出字符串B（"bac"）
 */
public class 最多提取子串数目 {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        String a = sc.next();
        String b = sc.next();

        // indexes对象记录字符串b中每个字符的索引
        HashMap<Character, Integer> indexes = new HashMap<>();
        for (int i = 0; i < b.length(); i++) {
            indexes.put(b.charAt(i), i); // B不会存在重复字母
        }

        // count对象用于记录遍历字符串a每个字符串过程中，统计到的符合顺序要求的字符串b中字符出现次数
        int[] count = new int[b.length()];
        for (int i = 0; i < a.length(); i++) {
            char c = a.charAt(i);

            if (indexes.containsKey(c)) {
                int idx = indexes.get(c);
                // 下面判断逻辑请看图解
                if (idx == 0 || count[idx] < count[idx - 1]) {
                    count[idx]++;
                }
            }
        }

        System.out.println(count[count.length - 1]);
    }



}
