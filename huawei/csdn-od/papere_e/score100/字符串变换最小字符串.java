import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一个字符串s，最多只能进行一次变换，返回变换后能得到的最小字符串（按照字典序进行比较）。
 * 变换规则：交换字符串中任意两个不同位置的字符。
 *
 * 输入描述：
 * 一串小写字母组成的字符串s
 *
 * 输出描述：
 * 按照要求进行变换得到的最小字符串。
 *
 * 备注：
 * s是都是小写字符组成
 * 1 ≤ s.length ≤ 1000
 *
 * 例如：
 * 输入：bcdefa
 * 输出：acdefb
 * 说明：a和b进行位置交换，可以得到最小字符串
 */
public class 字符串变换最小字符串 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        char[] inputChars = s.toCharArray();

        char[] minChars = s.toCharArray();

        //获取最小字符串排序
        Arrays.sort(minChars);

        //依次比较原字符串和最小字符串，如果有不同就交换，得到的就是最小的字符串
        for(int i=0;i<inputChars.length;i++){
            if(inputChars[i] != minChars[i]){
                //i位置的最小字符
                char replaceChar = minChars[i];
                //最小字符最晚是在哪个位置出现的
                int replaceIndex = s.lastIndexOf(replaceChar);
                //最小字符是什么
                char firstChar = inputChars[replaceIndex];
                //交换i位置字符和最小字符
                inputChars[replaceIndex] = inputChars[i];
                inputChars[i] = firstChar;
                break;
            }
        }
        System.out.println(new String(inputChars));
    }
}
