import java.util.Scanner;

/**
 * https://fcqian.blog.csdn.net/article/details/127914382
 *
 * 给定用户密码输入流 input，输入流中字符 '<' 表示退格，可以清除前一个输入的字符，请你编写程序，输出最终得到的密码字符，并判断密码是否满足如下的密码安全要求。
 * 密码安全要求如下：
 * 密码长度 ≥ 8；
 * 密码至少需要包含 1 个大写字母；
 * 密码至少需要包含 1 个小写字母；
 * 密码至少需要包含 1 个数字；
 * 密码至少需要包含 1 个字母和数字以外的非空白特殊字符；
 *
 * 注意空串退格后仍然为空串，且用户输入的字符串不包含 '<' 字符和空白字符。
 */
public class 密码输入检测 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String passwordStr = scanner.nextLine();
        int length = passwordStr.length();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i<length; i++){
            if(passwordStr.charAt(i) !='<'){
                stringBuilder.append(passwordStr.charAt(i));
            }else {
                if(stringBuilder.length()!=0) {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                }
            }
        }

        boolean lengthFlag = false;
        boolean lowFlag = false;
        boolean upperFlag = false;
        boolean num =false;
        boolean specChar =false;
        if(stringBuilder.length()>=8){
            lengthFlag=true;
        }
        char[] charArray = stringBuilder.toString().toCharArray();
        for (char c : charArray) {
            if('a'<=c && c<='z'){
                lowFlag=true;
                continue;
            }
            if('A'<=c && c<='Z'){
                upperFlag=true;
                continue;
            }
            if('0'<=c && c<='9'){
                num=true;
                continue;
            }

            specChar=true;
        }

        if(lengthFlag && lowFlag && upperFlag && num && specChar){
            stringBuilder.append(",true");
        }else {
            stringBuilder.append(",false");
        }
        System.out.println(stringBuilder.toString());
    }
}
