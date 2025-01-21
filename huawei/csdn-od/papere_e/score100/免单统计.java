import java.util.Arrays;
import java.util.Scanner;

/**
 * 华为商城举办了一个促销活动，如果某顾客是某一秒内最早时刻下单的顾客（可能是多个人），则可以获取免单。
 * 请你编程计算有多少顾客可以获取免单。
 *
 * 输入描述：
 * 输入为 n 行数据，每一行表示一位顾客的下单时间以（年-月-日 时-分-秒.毫秒）形式给出。
 * yyyy-MM-dd HH:mm:ss.fff
 *
 * 0 < n < 50000
 * 2000 < yyyy < 2020
 * 0 < MM ≤ 12
 * 0 < dd ≤ 28
 * 0 ≤ HH ≤ 23
 * 0 ≤ mm ≤ 59
 * 0 ≤ ss ≤ 59
 * 0 ≤ fff ≤ 999
 *
 * 所有输入保证合法。
 *
 * 输出描述：
 * 输出一个整数，表示有多少顾客可以获取免单。
 */
public class 免单统计 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        String[] accounts = new String[n];
        for(int i=0; i<n; i++) {
            accounts[i] =  scanner.nextLine();

        }

        //要先进行排序，让秒数差不多的都到一起去，便于后续进行比较
        accounts = Arrays.stream(accounts).sorted().toArray(String[]::new);
        String beforeStr = accounts[0];
        String preStr = accounts[0].substring(0, 19);
        //已经取出来一个做为比较的了，所以数量为1
        int count =1;

        for(int i=1; i<n; i++){
            String tmpStr = accounts[i];
            //只有时间完全相同，或者秒数开始变得不一样了，数量才+1
            //秒数一样的话数量不用变化，因为满足条件!preStr.equals(tmpStr.substring(0, 19))已经加过了
            if(tmpStr.equals(beforeStr) || !preStr.equals(tmpStr.substring(0, 19))){
                count++;
                beforeStr=tmpStr;
                preStr = tmpStr.substring(0, 19);
            }
        }
        System.out.println(count);
    }
}
