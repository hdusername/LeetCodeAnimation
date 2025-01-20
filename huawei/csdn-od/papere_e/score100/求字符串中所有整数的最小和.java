import java.util.Scanner;

/**
 * 输入字符串s，输出s中包含所有整数的最小和。
 * 说明：
 * 字符串s，只包含 a-z A-Z ± ；
 * 合法的整数包括
 * 1） 正整数 一个或者多个0-9组成，如 0 2 3 002 102
 * 2）负整数 负号 – 开头，数字部分由一个或者多个0-9组成，如 -0 -012 -23 -00023
 *
 * 输入描述：
 * 包含数字的字符串
 *
 * 输出描述：
 * 所有整数的最小和
 */
public class 求字符串中所有整数的最小和 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        long ans = 0;
        //这样修改是为了输出字符串以负数结尾的最后一个负数，不会改变结果
        s=s+"-";
        boolean minusFlag = false;
        StringBuffer minusBuffer = new StringBuffer();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c=='-'){
                minusFlag =true;
                //兼容两个负数挨在一起的情况
                if(minusBuffer.length()>0){
                    ans-=Long.parseLong(minusBuffer.toString());
                }
                minusBuffer.setLength(0);
            } else if (minusFlag && Character.isDigit(c)) {
                minusBuffer.append(c);
            } else if (minusFlag && !Character.isDigit(c)) {
                if(minusBuffer.length()>0){
                    ans-=Long.parseLong(minusBuffer.toString());
                }
                minusBuffer.setLength(0);
                minusFlag = false;
            } else if(!minusFlag && Character.isDigit(c)){
                ans += Long.parseLong(c+"");
            }
        }

        System.out.println(ans);
    }
}
