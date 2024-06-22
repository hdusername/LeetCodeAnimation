import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * XX市机场停放了多架飞机，每架飞机都有自己的航班号CA3385，CZ6678，SC6508等，航班号的前2个大写字母（或数字）代表航空公司的缩写，后面4个数字代表航班信息。
 * 但是XX市机场只有一条起飞跑道，调度人员需要安排目前停留在机场的航班有序起飞。
 * 为保障航班的有序起飞，调度员首先按照航空公司的缩写（航班号前2个字母）对所有航班进行排序，同一航空公司的航班再按照航班号的后4个数字进行排序，最终获得安排好的航班的起飞顺序。
 * 请编写一段代码根据输入的航班号信息帮助调度员输出航班的起飞顺序。
 * 说明：
 * 航空公司缩写排序按照从特殊符号$ & *，0~9，A~Z排序；
 *
 * 输入描述：
 * 第一行输入航班信息，多个航班号之间用逗号 "," 分隔，输入的航班号不超过100个。
 * 例如：CA3385,CZ6678,SC6508,DU7523,HK4456,MK0987
 * 备注：
 * 航班号为6为长度，后4位位纯数字，不考虑存在后4位重复的场景
 *
 * 输出描述：
 * CA3385,CZ6678,DU7523,HK4456,MK0987,SC6508
 */
public class 机场航班调度程序 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] split = scanner.nextLine().split(",");

        StringJoiner stringJoiner = new StringJoiner(",");
        Arrays.stream(split).sorted((a,b)->{
            String asubChar = a.substring(0, 2);
            String asubNum = a.substring(2);

            String bsubChar = b.substring(0, 2);
            String bsubNum = b.substring(2);

           if( asubChar.equals(bsubChar)){
               return asubNum.compareTo(bsubNum);
           }else {
               return asubChar.compareTo(bsubChar);
           }
        }).forEach(stringJoiner::add);

        System.out.println(stringJoiner.toString());
    }
}
