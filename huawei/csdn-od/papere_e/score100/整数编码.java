import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * 实现一种整数编码方法，使得待编码的数字越小，编码后所占用的字节数越小。
 * 编码规则如下:
 * 编码时7位一组，每个字节的低7位用于存储待编码数字的补码
 * 字节的最高位表示后续是否还有字节，置1表示后面还有更多的字节，置0表示当前字节为最后一个字节。
 * 采用小端序编码，低位和低字节放在低地址上。
 * 编码结果按16进制数的字符格式输出，小写字母需转换为大写字母
 *
 * 输入描述：
 * 输入的为一个字符串表示的非负整数
 *
 * 输出描述：
 * 输出一个字符串，表示整数编码的16进制码流
 *
 * 备注：
 * 待编码的数字取值范围为[0，1<<64 - 1]
 */
public class 整数编码 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        BigInteger bigInteger = new BigInteger(s);
        //把输入的十进制数改为2进制
//        s = Long.toBinaryString(Long.parseLong(s));
        //这样写可以防止s过长变为2进制时失败
        s = bigInteger.toString(2);
        StringBuilder stringBuilder = new StringBuilder();
        while (s.length()>0){
            //因为是小端序，所以从后面遍历到的值放在前面组成新的数字
            if(s.length()>7){
                //这个逻辑中第8位一定是1，因为后面还有值
                String remainStr= s.substring(0, s.length()-7);
                String curStr= s.substring(s.length()-7);
               // String binaryStr = Integer.toBinaryString(Integer.parseInt("1" + curStr));
                //int curInt = Integer.parseInt("1" + curStr,2);
                String hexString = Integer.toHexString(Integer.parseInt("1" + curStr, 2));
                if(hexString.length()==1){
                    //只有一位16进制数的需要增加前缀0
                    hexString="0" + hexString;
                }
                stringBuilder.append(hexString.toUpperCase());

                s = remainStr;

            }else {

                //int curInt = Integer.parseInt(s,2);

                //这个逻辑中第8位一定是0，因为后面没有值了，等于7的逻辑也在这里面
                String curStr = String.format("%8s", s).replace(" ", "0");


                String hexString = Integer.toHexString(Integer.parseInt(curStr, 2));
                if(hexString.length()==1){
                    //只有一位16进制数的需要增加前缀0
                    hexString="0" + hexString;
                }
                stringBuilder.append(hexString.toUpperCase());
                s="";
            }
        }
        System.out.println(stringBuilder);
    }
}

//标准答案 字符串解析
//import java.math.BigInteger;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
////        System.out.println(getResult(sc.nextLong()));
//        System.out.println(getResult(sc.nextBigInteger()));
//    }
//
//    //    public static String getResult(long num) {
//    public static String getResult(BigInteger num) {
////        String bin = Long.toBinaryString(num);
//        String bin = num.toString(2);
//
//        StringBuilder ans = new StringBuilder();
//
//        int end = bin.length();
//        while (end - 7 > 0) {
//            ans.append(getHexString("1" + bin.substring(end - 7, end)));
//            end -= 7;
//        }
//
//        if (end >= 0) {
//            ans.append(getHexString(bin.substring(0, end)));
//        }
//
//        return ans.toString();
//    }
//
//    public static String getHexString(String binStr) {
//        String hexStr = Integer.toHexString(Integer.parseInt(binStr, 2));
//        if (hexStr.length() == 1) hexStr = "0" + hexStr;
//        return hexStr.toUpperCase();
//    }
//}

//标准答案2 位运算
//import java.math.BigInteger;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
////        long num = sc.nextLong();
//        BigInteger num = sc.nextBigInteger();
//
//        StringBuilder sb = new StringBuilder();
//
//        do {
//            // 编码时7位一组，每个字节的低7位用于存储待编码数字的补码
////            long lowBit = num & 0b01111111; // 按位与运算, 获取 num 的低7位
//            BigInteger lowBit = num.and(new BigInteger("1111111", 2));
//
//            // lowBit的最高位表示后续是否还有字节，置1表示后面还有更多的字节，置0表示当前字节为最后一个字节。
////            if (num >> 7 > 0) { // num 右移7位后, 若结果大于0, 则说明后面还有字节
////                lowBit |= 0b10000000; // 按位或运算, 将lowBit最高位置1
////            }
//            if (num.shiftRight(7).compareTo(new BigInteger("0")) > 0) {
//                lowBit = lowBit.or(new BigInteger("10000000", 2));
//            }
//
//            // 编码结果按16进制数的字符格式输出，小写字母需转换为大写字母
////            String hex = Long.toHexString(lowBit).toUpperCase();
//            String hex = lowBit.toString(16).toUpperCase();
//
//            if (hex.length() == 1) {
//                hex = "0" + hex;
//            }
//
//            sb.append(hex);
//
//            // 右移运算移除num的低七位
////            num >>= 7;
//            num = num.shiftRight(7);
////        } while (num != 0);
//        } while (num.compareTo(new BigInteger("0")) != 0);
//
//        System.out.println(sb);
//    }
//}
