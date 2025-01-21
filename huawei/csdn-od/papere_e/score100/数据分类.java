import java.util.HashMap;
import java.util.Scanner;

/**
 * 对一个数据 a 进行分类，分类方法为：此数据 a（四个字节大小）的四个字节相加对一个给定的值 b 取模
 * 如果得到的结果小于一个给定的值 c，则数据 a 为有效类型，其类型为取模的值
 * 如果得到的结果大于或者等于 c，则数据 a 为无效类型
 * 比如一个数据 a = 0x01010101，b = 3，
 * 按照分类方法计算（0x01+0x01+0x01+0x01）% 3 = 1，
 * 所以如果 c = 2，则此 a 为有效类型，其类型为 1，如果 c = 1，则此 a 为无效类型；
 *
 * 又比如一个数据 a = 0x01010103，b = 3，
 * 按照分类方法计算（0x01+0x01+0x01+0x03）% 3 = 0，
 * 所以如果 c = 2，则此 a 为有效类型，其类型为 0，如果c = 0，则此 a 为无效类型。
 *
 * 输入 12 个数据，第一个数据为 c，第二个数据为 b，剩余 10 个数据为需要分类的数据，
 * 请找到有效类型中包含数据最多的类型，并输出该类型含有多少个数据。
 *
 * 输入描述：
 * 输入 12 个数据，用空格分隔，第一个数据为 c，第二个数据为 b，剩余 10 个数据为需要分类的数据。
 *
 * 输出描述：
 * 输出最多数据的有效类型有多少个数据。
 */
public class 数据分类 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int c = scanner.nextInt();
        int b = scanner.nextInt();

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<10; i++){
            int sum = 0;
            int a = scanner.nextInt();
            //int类型就是4个字节长度，正好将每个字节与0xff进行位运算，然后再相加
            while (a>0){
                sum += a & 0xff;
                a= a >>8;
            }
            int result = sum%b;
            if(result<c){
                //放入对应类型，并把数量+1
                map.put(result, map.getOrDefault(result, 0)+1);
            }

        }
        Integer integer = map.values().stream().max(Integer::compare).get();
        System.out.println(integer);
    }
}
