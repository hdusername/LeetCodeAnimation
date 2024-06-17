import java.util.Scanner;

/**
 * 均衡串定义：字符串中只包含两种字符，且这两种字符的个数相同。
 * 给定一个均衡字符串，请给出可分割成新的均衡子串的最大个数。
 * 约定：字符串中只包含大写的 X 和 Y 两种字符。
 *
 * 输入描述：
 * 输入一个均衡串。
 * 字符串的长度：[2， 10000]。
 * 给定的字符串均为均衡字符串
 *
 * 输出描述：
 * 输出可分割成新的均衡子串的最大个数。
 *
 * 备注：
 * 分割后的子串，是原字符串的连续子串
 */
public class 分割均衡字符串 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();

        int ans = 0;

        int xNum = 0;
        int yNum = 0;
        for(int i =0;i<str.length();i++){
            char sinStr = str.charAt(i);
            if(sinStr=='X'){
                xNum++;
            }else{

                yNum++;
            }
            if(xNum==yNum){
                ans++;
            }
        }
        System.out.println(ans);
    }
}
