import java.util.Scanner;
import java.util.StringJoiner;


/**
 * 给定一个非空字符串S，其被N个‘-’分隔成N+1的子串，给定正整数K，要求除第一个子串外，其余的子串每K个字符组成新的子串，并用‘-’分隔。
 *  对于新组成的每一个子串，如果它含有的小写字母比大写字母多，则将这个子串的所有大写字母转换为小写字母，
 *   反之，如果它含有的大写字母比小写字母多，则将这个子串的所有小写字母转换为大写字母；大小写字母的数量相等时，不做转换。
 *
 *   输入描述：
 *   输入为两行，第一行为参数K，第二行为字符串S。
 *
 *   输出描述:
 *   输出转换后的字符串。
 */
public class 字符串分割 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = Integer.parseInt(scanner.nextLine());

        String s = scanner.nextLine();

        String[] ss = s.split("-");

        String firstStr = ss[0];

        StringJoiner stringJoiner = new StringJoiner("-");
        stringJoiner.add(firstStr);
        StringBuffer stringBuffer = new StringBuffer();;
        int tmpk=0;
        for(int i=firstStr.length();i<s.length();i++){

            if(s.charAt(i)!='-'){
                tmpk++;
                stringBuffer.append(s.charAt(i));
            }
            if(tmpk==k){
                stringJoiner.add(convert(stringBuffer.toString()));
                stringBuffer.setLength(0);
                tmpk=0;
            }

        }
        if(stringBuffer.length()>0){
            stringJoiner.add(convert(stringBuffer.toString()));
        }
        System.out.println(stringJoiner);
    }

    public static String convert(String str) {
        String answer;
        int upper = 0;
        int lower = 0;
        for(int i = 0;i<str.length();i++){
            if('a'<=str.charAt(i) && str.charAt(i) <='z'){
                lower++;
            } else if ('A' <= str.charAt(i) && str.charAt(i) <= 'Z') {
                upper++;
            }
        }
        if(lower>upper){
            answer=str.toLowerCase();
        } else if (upper>lower) {
            answer=str.toUpperCase();
        }else{
            answer = str;
        }
        return answer;
    }
}
