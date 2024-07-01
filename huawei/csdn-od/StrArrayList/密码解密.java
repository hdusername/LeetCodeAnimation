import java.util.Scanner;

/**
 * 给定一段“密文”字符串 s，其中字符都是经过“密码本”映射的，现需要将“密文”解密并输出。
 * 映射的规则（'a' ~ 'i'）分别用（'1' ~ '9'）表示；（'j' ~ 'z'）分别用（"10*" ~ "26*"）表示。
 * 约束：映射始终唯一。
 *
 * 输入描述：
 * “密文”字符串
 *
 * 输出描述：
 * 明文字符串
 */
public class 密码解密 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String plaintext = decrypt(s);
        System.out.println(plaintext); // 输出解密后的明文
    }

    public static String decrypt(String s) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            // 处理 '10*' 到 '26*' 的情况
            if (i + 2 < s.length() && s.charAt(i + 2) == '*') {
                String code = s.substring(i, i + 2);
                int num = Integer.parseInt(code);
                char letter = (char) ('j' + (num - 10));
                result.append(letter);
                i += 3; // 跳过当前 '10*' 到 '26*' 三个字符
            } else {
                // 处理 '1' 到 '9' 的情况
                char code = s.charAt(i);
                char letter = (char) ('a' + (code - '1'));
                result.append(letter);
                i++; // 处理当前单个字符
            }
        }
        return result.toString();
    }

}
