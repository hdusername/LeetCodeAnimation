import java.util.Scanner;

/**
 * 给你一个字符串 s，首尾相连成一个环形，请你在环中找出 'o' 字符出现了偶数次最长子字符串的长度。
 * 输入是一个小写字母组成的字符串
 *输出是一个整数
 *
 */
public class 最长子字符串的长度一 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(getResult(sc.nextLine()));
    }

    public static int getResult(String s) {
        int n = s.length();

        // s中'o'的个数
        int zeroCount = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'o') zeroCount++;
        }

        if (zeroCount % 2 == 0) {
            return n;
        } else {
            return n - 1;
        }
    }

}
