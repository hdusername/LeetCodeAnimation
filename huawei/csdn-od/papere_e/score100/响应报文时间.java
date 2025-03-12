import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * IGMP协议中，有一个字段称作最大响应时间 (Max Response Time) ，HOST收到查询报文，解折出 MaxResponsetime 字段后，
 * 需要在 (0，MaxResponseTime] 时间 (s) 内选取随机时间回应一个响应报文，如果在随机时间内收到一个新的查询报文，则会根
 * 据两者时间的大小，选取小的一方刷新回应时间。
 * 最大响应时间有如下计算方式：
 * 当 Max Resp Code < 128, Max Resp Time = Max Resp Code；
 * 当 Max Resp Code ≥ 128,
 *  Max Resp Time = (mant | 0x10) << (exp + 3)；
 *
 *  注: exp最大响应时间的高5~7位: mant 为最大响应时间的低4位。
 *
 *  其中接收到的MaxRespCode最大值为 255，以上出现所有字段均为无符号数。
 *  现在我们认为 HOST收到查询报文时，选取的随机时间必定为最大值，现给出 HOST 收到查询报文个数 C，HOST 收到该报文的时间T，
 *  以及查询报文的最大响应时间字段值 M，请计算出HOST 发送响应报文的时间。
 *
 *  输入描述：
 *  第一行为查询报文个数 C，后续每行分别为 HOST 收到报文时间 T，及最大响应时间M，以空格分割。
 *
 *  输出描述：
 *  HOST 发送响应报文的时间。
 *
 *  例如输入：
 *   0 20
 *   1 10
 *   8 20
 *
 *  输出：11
 *
 *  说明：
 *  收到3个报文， 第0秒收到第1个报文，响应时间为20秒，则要到0+20=20秒响应；
 *   第1秒收到第2个报文，响应时间为10秒，则要到1+10=11秒响应，与上面的报文的响应时间比较获得响应时间最小为11秒；
 *   第8秒收到第3个报文，响应时间为20秒，则要到8+20=28秒响应，与第上面的报文的响应时间比较获得响应时间最小为11秒；
 *   最终得到最小响应报文时间为11秒
 */
public class 响应报文时间 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        ArrayList<Integer> arrayList = new ArrayList<>();

        for(int i =0;i<n;i++){
            //收到报文的时间T
            int t = scanner.nextInt();

            Integer maxRespCode = scanner.nextInt();
            int maxRespMaxTime = maxRespCode;
            if(maxRespCode>=128){
                String binaryStr =Integer.toBinaryString(maxRespCode);
                int str_1_4 = Integer.parseInt(binaryStr.substring(1, 4),2);
                int str_4_8 = Integer.parseInt(binaryStr.substring(4, 8),2);
                maxRespMaxTime = (str_4_8 | 0x10)<<(str_1_4+3);

            }
            arrayList.add(t+maxRespMaxTime);
        }

        Integer min = Collections.min(arrayList);

        System.out.println(min);

    }
}

//标准答案
//import java.util.Scanner;
//
//public class Main {
//  public static void main(String[] args) {
//    Scanner sc = new Scanner(System.in);
//
//    int c = sc.nextInt();
//
//    int[][] messages = new int[c][2];
//    for (int i = 0; i < c; i++) {
//      messages[i][0] = sc.nextInt();
//      messages[i][1] = sc.nextInt();
//    }
//
//    System.out.println(getResult(messages));
//  }
//
//  public static int getResult(int[][] messages) {
//    int ans = Integer.MAX_VALUE;
//
//    for (int[] message : messages) {
//      int respTime = message[0] + getMaxResponseTime(message[1]);
//      ans = Math.min(ans, respTime);
//    }
//
//    return ans;
//  }
//
//  public static int getMaxResponseTime(int m) {
//    if (m >= 128) {
//      StringBuilder bStr = new StringBuilder(Integer.toBinaryString(m));
//
//      int exp = Integer.parseInt(bStr.substring(1, 4), 2);
//      int mant = Integer.parseInt(bStr.substring(4), 2);
//
//      return (mant | 16) << (exp + 3);
//    } else {
//      return m;
//    }
//  }
//}
