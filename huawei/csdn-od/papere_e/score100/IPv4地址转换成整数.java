import java.text.Format;
import java.util.Scanner;

/**
 * 存在一种虚拟IPv4地址，由4小节组成，每节的范围为0~255，以#号间隔，虚拟IPv4地址可以转换为一个32位的整数，例如：
 * 128#0#255#255，转换为32位整数的结果为2147549183（0x8000FFFF）
 * 1#0#0#0，转换为32位整数的结果为16777216（0x01000000）
 *
 * 现以字符串形式给出一个虚拟IPv4地址，限制第1小节的范围为1~128，即每一节范围分别为(1~128)#(0~255)#(0~255)#(0~255)，要求每个IPv4地址只能对应到唯一的整数上。
 * 如果是非法IPv4，返回invalid IP
 *
 * 输入描述：
 * 输入一行，虚拟IPv4地址格式字符串
 *
 * 输出描述：
 * 输出一行，按照要求输出整型或者特定字符
 *
 * 备注：
 * 输入不能确保是合法的IPv4地址，需要对非法IPv4（空串，含有IP地址中不存在的字符，非合法的#分十进制，十进制整数不在合法区间内）进行识别，返回特定错误
 */
public class IPv4地址转换成整数 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();



        try{
            String[] split = s.split("#");

            Integer s0 = Integer.parseInt(split[0]);
            Integer s1 = Integer.parseInt(split[1]);
            Integer s2 = Integer.parseInt(split[2]);
            Integer s3 = Integer.parseInt(split[3]);
            if(!(split.length==4 && s0>=1&&s0<=128 && s1>=0&&s1<=255 && s2>=0&&s2<=255&& s3>=0&&s3<=255)){
                throw  new Exception("");
            }

            if((s0!=0&& split[0].startsWith("0")) || (s1!=0&& split[1].startsWith("0")) || (s2!=0&& split[2].startsWith("0")) || (s3!=0&& split[3].startsWith("0")) ){
                throw  new Exception("");
            }

            String hexStr = String.format("%2s", Integer.toHexString(s0)).replace(" ","0") +
                    String.format("%2s", Integer.toHexString(s1)).replace(" ","0") +
                    String.format("%2s", Integer.toHexString(s2)).replace(" ","0")+
                    String.format("%2s", Integer.toHexString(s3)).replace(" ","0");

            long i = Long.parseLong(hexStr, 16);

            System.out.println(i);

        }catch (Exception e){
            System.out.println("invalid IP");
        }
    }
}
