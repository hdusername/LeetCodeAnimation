import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 相对开音节构成的结构为：辅音 + 元音（aeiou）+ 辅音(r除外) + e。
 * 常见的单词有bike、cake等。
 * 给定一个字符串，以空格为分隔符，反转每个单词中的字母，若单词中包含如数字等其他非字母时不进行反转。
 * 反转后计算其中含有相对开音节结构的子串个数（连续的子串中部分字符可以重复）。
 *
 * 输入描述：
 * 字符串，以空格分割的多个单词，字符串长度<10000，字母只考虑小写
 *
 * 输出描述：
 * 含有相对开音节结构的子串个数，注：个数<10000
 *
 * 用例输入：
 * ekam a ekac
 *
 * 输出：
 * 2
 *
 * 解释：
 * 反转后为 make a cake 其中make、cake为相对开音节子串，返回2。
 */
public class 相对开音节 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strS = scanner.nextLine().split(" ");

        Pattern compile1 = Pattern.compile("[^a-z]");

        Pattern compile2 = Pattern.compile("[^aeiou][aeiou][^aeiour]e");

        for(int i=0; i<strS.length; i++){
            String s = strS[i];
            Matcher matcher = compile1.matcher(s);
            if(matcher.find()){
                //包含了非字符，无需进行反转
            }else {
                //反转字符串
                strS[i] = reverse(s);
            }

        }

        int ans = 0;
        //在反转后的字符串数组中找相对开音节
        for(int i=0; i<strS.length; i++){
            String s = strS[i];
            if(s.length()>=4){
                int j=0;
                while (j<=s.length()-4){
                    String subS = s.substring(j, j+4);
                    //因为切出来的subS也有可能带有非字符，是不符合开音节条件的，只按照compile2的方式是无法准确判断出来的，比如判断bi!e时，compile2会find为true
                    Matcher matcher1 = compile1.matcher(subS);
                    if(!matcher1.find()) {
                        Matcher matcher = compile2.matcher(subS);
                        if (matcher.find()) {
                            ans++;
                        }
                    }
                    j++;

                }
            }
        }

        System.out.println(ans);
    }

    private static String reverse(String s) {
        char[] charArray = s.toCharArray();
        int l = 0;
        int r = s.length()-1;
        while (l<r){
            char lc = charArray[l];
            charArray[l] = charArray[r];
            charArray[r] = lc;
            l++;
            r--;
        }
        return new String(charArray);
    }

}
