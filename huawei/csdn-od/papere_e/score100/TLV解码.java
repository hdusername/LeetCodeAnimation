import java.util.Scanner;
import java.util.StringJoiner;

/**
 * TLV编码是按 [Tag Length Value] 格式进行编码的，一段码流中的信元用 Tag 标识，Tag 在码流中唯一不重复，Length 表示信元Value的长度，Value 表示信元的值。
 * 码流以某信元的 Tag 开头，Tag 固定占一个字节，Length 固定占两个字节，字节序为小端序
 * 现给定 TLV 格式编码的码流，以及需要解码的信元 Tag，请输出该信元的 Value。
 * 输入码流的 16 进制字符中，不包括小写字母，且要求输出的 16 进制字符串中也不要包含小写字母；码流字符串的最大长度不超过 50000 个字节。
 *
 * 输入描述:
 * 输入的第一行为一个字符串，表示待解码信元的Tag；
 * 输入的第二行为一个字符串，表示待解码的 16 进制码流，字节之间用空格分隔。
 *
 * 输出描述：
 * 输出一个字符串，表示待解码信元以 16 进制表示的 Value。
 */
public class TLV解码 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String tarTag = scanner.nextLine();
        String[] s = scanner.nextLine().split(" ");

        int i = 0;
        while (i<s.length){
            String tag = s[i++];
            String len1 = s[i++];
            String len2 = s[i++];

            //因为是小端序，所以数值是反着的
            int len = Integer.parseInt(len2 + len1, 16);


            if(tag.equals(tarTag)){
                StringJoiner stringJoiner = new StringJoiner(" ");
                for(int j=0;j<len;j++){
                   stringJoiner.add(s[i++]);
               }
                System.out.println(stringJoiner);
                return;
            }else {
                i+=len;
            }
        }

    }
}
