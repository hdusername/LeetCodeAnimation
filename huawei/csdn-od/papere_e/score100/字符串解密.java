import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 给定两个字符串string1和string2。
 * string1是一个被加扰的字符串。
 * string1由小写英文字母（’a’~’z’）和数字字符（’0’~’9’）组成，而加扰字符串由’0’~’9’、’a’~’f’组成。
 *
 * string1里面可能包含0个或多个加扰子串，剩下可能有0个或多个有效子串，这些有效子串被加扰子串隔开。
 * string2是一个参考字符串，仅由小写英文字母（’a’~’z’）组成。
 * 你需要在string1字符串里找到一个有效子串，这个有效子串要同时满足下面两个条件：
 * （1）这个有效子串里不同字母的数量不超过且最接近于string2里不同字母的数量，即小于或等于string2里不同字母的数量的同时且最大。
 * （2）这个有效子串是满足条件（1）里的所有子串（如果有多个的话）里字典序最大的一个。
 *
 * 如果没有找到合适条件的子串的话，请输出”Not Found”
 *
 * 输入描述：
 * input_string1
 * input_string2
 *
 * 输出描述：
 * output_string
 *
 * 用例输入
 * 123admyffc79pt
 * ssyy
 *
 * 输出：
 * pt
 *
 * 解释：
 * 将输入字符串1里的加扰子串“123ad”、“ffc79”去除后得到有效子串序列："my"、"pt"，其中"my"里不同字母的数量为2（有‘m’和'y'两个不同字母），
 * “pt”里不同字母的数量为2（有'p'和't'两个不同字母）；输入字符串2里不同字母的数量为2（有‘s’和'y'两个不同字母）。
 * 可得到最终输出结果为“pt”，其不同字母的数量最接近与“ssyy”里不同字母的数量的同时字典序最大。
 */
public class 字符串解密 {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            String str1 = sc.next();
            String str2 = sc.next();

            System.out.println(getResult(str1, str2));
        }

        public static String getResult(String str1, String str2) {
            String reg = "[0-9a-f]+";

            String[] valids = str1.split(reg);

            int count = getDistinctCount(str2);

            String[] ans = Arrays.stream(valids).filter(valid -> !"".equals(valid) && getDistinctCount(valid) <= count).toArray(String[]::new);

            if(ans.length == 0) return "Not Found";

            Arrays.sort(ans, (a,b)->{
                int c1 = getDistinctCount(a);
                int c2 = getDistinctCount(b);
                return c1 != c2 ? c2 - c1 : b.compareTo(a);
            });

            return ans[0];
        }

        public static int getDistinctCount(String str) {
            HashSet<Character> set = new HashSet<>();
            for (char c : str.toCharArray()) {
                set.add(c);
            }
            return set.size();
        }

}
