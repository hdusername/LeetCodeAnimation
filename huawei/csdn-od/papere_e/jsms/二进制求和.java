/**
 * 给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。
 *
 *
 *
 * 示例 1：
 *
 * 输入:a = "11", b = "1"
 * 输出："100"
 * 示例 2：
 *
 * 输入：a = "1010", b = "1011"
 * 输出："10101"
 */
public class 二进制求和 {

    public static void main(String[] args) {
       // System.out.println(getResult("1","111"));


        int a = 8;
        int b = 4;
        //等于0的话说明已经找到最大公约数了
        //如果mod=a%b中b等于1时，才得到余数mod为0，说明没有最大公约数，两个数互质
        //如果b=1时余数才等于0，在后面的逻辑中1赋值给了a，所以后面判断a是否等于1返回两个数是否互质
        while (b>0){
            int mod=a%b;
            a=b;
            b=mod;

        }
       System.out.println(a);

    }
    public String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        StringBuilder builder = new StringBuilder();
        //循环相加两个字符串相同长度的低位数部分
        while (i >= 0 && j >= 0) {
            int sum = carry;
            sum += a.charAt(i--) - '0';
            sum += b.charAt(j--) - '0';
            carry = sum / 2;
            builder.append(sum % 2);
        }
        // 如果 a 还没遍历完成（a串比b串长），则继续遍历添加 a 的剩余部分
        while (i >= 0) {
            int sum = carry + a.charAt(i--) - '0';
            carry = sum / 2;
            builder.append(sum % 2);
        }
        // 如果 b 还没遍历完成（b串比a串长），则继续遍历添加 b 的剩余部分
        while (j >= 0) {
            int sum = carry + b.charAt(j--) - '0';
            carry = sum / 2;
            builder.append(sum % 2);
        }
        //如果 carry 不等于0 还有个进位数没加进去，需要补充
        if (carry == 1) {
            builder.append(carry);
        }
        //反转字符串获得正常结果
        return builder.reverse().toString();
}


//    public static String getResult(String a, String b){
//        //return Long.toBinaryString(Long.parseLong(a, 2)+Long.parseLong(b, 2));
//        if(a.length()>b.length()){
//            for(int i=0; i<a.length()-b.length(); i++){
//                b="0"+b;
//            }
//            //b=String.format("%2s", b).replace(" ", "0");
//        }else if(a.length()<b.length()){
//            for(int i=0; i<=b.length()-a.length(); i++){
//                a="0"+a;
//            }
//            //a=String.format("%2s", a).replace(" ", "0");
//        }
//
//        StringBuffer stringBuffer = new StringBuffer();
//        int mod = 0;
//        for(int i=a.length()-1; i>=0; i--){
//            int a_num = a.charAt(i)-'0';
//            int b_num = b.charAt(i)-'0';
//
//            int cur_num = a_num+b_num+mod;
//            if(cur_num>=2){
//                cur_num=cur_num-2;
//                mod = 1;
//            }else {
//                mod=0;
//            }
//
//            stringBuffer.append(cur_num);
//        }
//
//        if(mod!=0){
//            stringBuffer.append(mod);
//        }
//        return stringBuffer.reverse().toString();
//    }
}
